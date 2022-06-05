package com.WebService.Webservice.project.repository;


import com.WebService.Webservice.project.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
//This interface is for JPA integration with the database

public interface OrderRepository extends JpaRepository<Order, Integer> {


}
