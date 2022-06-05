package com.WebService.Webservice.project.repository;


import com.WebService.Webservice.project.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
//This interface is for JPA integration with the database

public interface StockRepository extends JpaRepository<Stock,Integer> {

}
