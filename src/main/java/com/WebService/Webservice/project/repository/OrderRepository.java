package com.WebService.Webservice.project.repository;


import com.WebService.Webservice.project.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
