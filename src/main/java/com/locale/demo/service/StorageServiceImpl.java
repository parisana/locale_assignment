package com.locale.demo.service;

import com.locale.demo.Utils.CSVConvertor;
import com.locale.demo.domain.BookingDTO;
import com.locale.demo.domain.Customer;
import com.locale.demo.domain.FailedBooking;
import com.locale.demo.domain.dto.Result;
import com.locale.demo.exception.CustomerNotFoundException;
import com.locale.demo.repo.BookingDtoRepo;
import com.locale.demo.repo.CustomerRepo;
import com.locale.demo.repo.FailedBookingRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * @author Parisana
 */
@Service
@Slf4j
public class StorageServiceImpl implements StorageService {
    private final CSVConvertor csvConvertor;
    private final BookingDtoRepo bookingDtoRepo;
    private final CustomerRepo customerRepo;
    private final FailedBookingRepo failedBookingRepo;

    @Autowired
    public StorageServiceImpl(CSVConvertor csvConvertor, BookingDtoRepo bookingDtoRepo, CustomerRepo customerRepo, FailedBookingRepo failedBookingRepo) {
        this.csvConvertor = csvConvertor;
        this.bookingDtoRepo = bookingDtoRepo;
        this.customerRepo = customerRepo;
        this.failedBookingRepo = failedBookingRepo;
    }

    @Override
    @Async
    public CompletableFuture<String> parseAndSave(Long customerId, MultipartFile multipartFile) {
        final Path path = Paths.get("/home/pari/Project/localeAi/demo/src/main/resources").resolve(UUID.randomUUID().toString());
        final Customer customer = customerRepo.findById(customerId).orElseThrow(()-> new CustomerNotFoundException("Customer with id: \"" + customerId + "\" not found!"));
        try {
            multipartFile.transferTo(path);
                    final Result<List<BookingDTO>, List<FailedBooking>> result = csvConvertor.toOptionalObjects(new FileReader(path.toFile()), BookingDTO.class, FailedBooking.class);
//                    System.out.println("-------------" + result.getObj().size());
                    final List<BookingDTO> bookingDTOS= Objects.requireNonNull(result, "Result from parsing the CSV cannot be empty!").getObj().parallelStream().peek(bookingDTO -> bookingDTO.setCustomer(customer)).collect(Collectors.toList());
                    final List<FailedBooking> failedBookingList = result.getExceptionObj().stream().peek(failedBookingDTO -> failedBookingDTO.setCustomer(customer)).collect(Collectors.toList());

                    bookingDtoRepo.saveAll(bookingDTOS); // save verified bookings.
                    failedBookingRepo.saveAll(failedBookingList); // save errorneous booking, to know what went wrong.
                    Files.delete(path);
                    return CompletableFuture.completedFuture(String.format("Success count: %s \nFailure count: %s",bookingDTOS.size(), failedBookingList.size()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<FailedBooking> findAllFailedBookingDTO(Long customerId) {
        return failedBookingRepo.findByCustomerIdOrderByBookingId(customerId);
    }
}
