package com.WebService.Webservice.project.repository;


import com.WebService.Webservice.project.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {

}
