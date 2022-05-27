package com.WebService.Webservice.project.service;

import com.WebService.Webservice.project.dto.ProductOrderDto;

import java.util.List;


public interface ProductOrderService {
    ProductOrderDto createProductOrder(ProductOrderDto ProductOrderDto);

    List<ProductOrderDto> getAllProductOrders();

    ProductOrderDto getProductOrderById(long id);

    ProductOrderDto updateProductOrder(ProductOrderDto ProductOrderDto, long id);

    void deleteProductOrderById(long id);
}
