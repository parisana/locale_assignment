package com.locale.demo.repo;

import com.locale.demo.domain.BookingDTO;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Parisana
 */
public interface BookingDtoRepo extends CrudRepository<BookingDTO, Long> {
}
