package com.WebService.Webservice.project.repository;


import com.WebService.Webservice.project.entity.ProductOrder;
import com.WebService.Webservice.project.entity.ProductOrderPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, ProductOrderPK> {

}
