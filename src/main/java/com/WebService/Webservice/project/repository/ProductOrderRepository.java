package com.WebService.Webservice.project.repository;


import com.WebService.Webservice.project.entity.ProductOrder;
import com.WebService.Webservice.project.entity.ProductOrderId;
import com.WebService.Webservice.project.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, ProductOrderId> {

}
