package com.locale.demo;

import com.locale.demo.domain.Customer;
import com.locale.demo.repo.BookingDtoRepo;
import com.locale.demo.repo.CustomerRepo;
import com.locale.demo.repo.FailedBookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableAsync
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Component
	public class CustomerInitializer implements ApplicationRunner{

		@Autowired
		private CustomerRepo customerRepo;
		@Autowired
		private FailedBookingRepo failedBookingRepo;
		@Autowired
		private BookingDtoRepo bookingDtoRepo;

		@Override
		public void run(ApplicationArguments args) throws Exception {
			System.out.println("Running application runner!");
			customerRepo.deleteAll();
			bookingDtoRepo.deleteAll();
			failedBookingRepo.deleteAll();
			customerRepo.save(new Customer(null, "XRides"));
		}
	}

}
