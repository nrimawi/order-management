package com.WebService.Webservice.project.repository;


import com.WebService.Webservice.project.entity.Product;
import com.WebService.Webservice.project.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock,Integer> {

}
