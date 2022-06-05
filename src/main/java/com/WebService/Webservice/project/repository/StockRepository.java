package com.WebService.Webservice.project.repository;


import com.WebService.Webservice.project.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock,Integer> {

}
