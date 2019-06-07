package com.locale.demo.repo;

import com.locale.demo.domain.Customer;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Parisana
 */
public interface CustomerRepo extends CrudRepository<Customer, Long> {
}
