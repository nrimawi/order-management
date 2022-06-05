package com.WebService.Webservice.project.repository;


import com.WebService.Webservice.project.entity.ProductOrder;
import com.WebService.Webservice.project.entity.ProductOrderPK;
import org.springframework.data.jpa.repository.JpaRepository;
//This interface is for JPA integration with the database

public interface ProductOrderRepository extends JpaRepository<ProductOrder, ProductOrderPK> {

}
