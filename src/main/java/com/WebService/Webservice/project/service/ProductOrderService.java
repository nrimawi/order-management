package com.WebService.Webservice.project.service;

import com.WebService.Webservice.project.dto.ProductOrderDto;
import com.WebService.Webservice.project.entity.ProductOrderId;

import java.util.List;


public interface ProductOrderService {
    ProductOrderDto createProductOrder(ProductOrderDto ProductOrderDto);

    List<ProductOrderDto> getAllProductOrders();

    ProductOrderDto getProductOrderById(ProductOrderId id);

    ProductOrderDto updateProductOrder(ProductOrderDto ProductOrderDto, ProductOrderId id);

    void deleteProductOrderById(ProductOrderId id);
}
