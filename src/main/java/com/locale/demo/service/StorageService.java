package com.locale.demo.service;

import com.locale.demo.domain.FailedBooking;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author Parisana
 */
public interface StorageService {

    CompletableFuture<String> parseAndSave(Long customerId, MultipartFile multipartFile);

    List<FailedBooking> findAllFailedBookingDTO(Long customerId);

}
