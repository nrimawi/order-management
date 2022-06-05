package com.WebService.Webservice.project.repository;


import com.WebService.Webservice.project.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
//This interface is for JPA integration with the database

public interface ProductRepository extends JpaRepository<Product,Integer> {

}
