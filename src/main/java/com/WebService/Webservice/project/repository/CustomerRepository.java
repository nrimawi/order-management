package com.WebService.Webservice.project.repository;

import com.WebService.Webservice.project.entity.Account;
import com.WebService.Webservice.project.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring recognises the repositories by the fact that they extend one of the predefined Repository interfaces
 * Author: Mohammed Kharma
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
