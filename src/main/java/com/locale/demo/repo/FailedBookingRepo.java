package com.locale.demo.repo;

import com.locale.demo.domain.FailedBooking;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Parisana
 */
public interface FailedBookingRepo extends CrudRepository<FailedBooking, Long> {

    List<FailedBooking> findByCustomerIdOrderByBookingId(Long customerId);

}
