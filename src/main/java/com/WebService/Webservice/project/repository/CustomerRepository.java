package com.WebService.Webservice.project.repository;
import com.WebService.Webservice.project.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
