package com.WebService.Webservice.project.service;

import com.WebService.Webservice.project.dto.ProductDto;

import java.util.List;


public interface ProductService {
    ProductDto createProduct(ProductDto ProductDto);

    List<ProductDto> getAllProducts();

    ProductDto getProductById(int id);

    ProductDto updateProduct(ProductDto ProductDto, int id);

    void deleteProductById(int id);
}
