package com.WebService.Webservice.project.service.impl;


import com.WebService.Webservice.project.dto.ProductDto;
import com.WebService.Webservice.project.entity.Product;
import com.WebService.Webservice.project.exception.ResourceNotFoundException;
import com.WebService.Webservice.project.repository.ProductRepository;
import com.WebService.Webservice.project.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service //To enable this class for component scanning
public class ProductServiceImpl implements ProductService {

    private ProductRepository ProductRepository;

    public ProductServiceImpl(ProductRepository ProductRepository) {
        this.ProductRepository = ProductRepository;
    }

    @Override
    public ProductDto createProduct(ProductDto ProductDto) {

        // convert DTO to entity
        Product Product = mapToEntity(ProductDto);
        Product newProduct = ProductRepository.save(Product);

        // convert entity to DTO
        ProductDto ProductResponse = mapToDTO(newProduct);
        return ProductResponse;
    }
    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> categories = ProductRepository.findAll();
        return categories.stream().map(Product -> mapToDTO(Product)).collect(Collectors.toList());
    }
    @Override
    public ProductDto getProductById(int id) {
        Product Product = ProductRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        return mapToDTO(Product);
    }
    @Override
    public ProductDto updateProduct(ProductDto ProductDto, int id) {
        // get Product by id from the database
        Product Product = ProductRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        Product.setId(ProductDto.getId());
        Product.setSlug(ProductDto.getSlug());
        Product.setName(ProductDto.getName());
        Product.setReference(ProductDto.getReference());
        Product.setPrice(ProductDto.getPrice());
        Product.setVat(ProductDto.getVat());
        Product.setStockable(ProductDto.isStockable());

        Product updatedProduct = ProductRepository.save(Product);
        return mapToDTO(updatedProduct);
    }
    @Override
    public void deleteProductById(int id) {
        // get Product by id from the database
        Product Product = ProductRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        ProductRepository.delete(Product);
    }
    // convert Entity into DTO
    private ProductDto mapToDTO(Product Product) {

        ProductDto ProductDto = new ProductDto();
        ProductDto.setId(Product.getId());
        ProductDto.setName(Product.getName());
        ProductDto.setPrice(Product.getPrice());
        ProductDto.setStockable(Product.isStockable());
        ProductDto.setVat(Product.getVat());
        ProductDto.setSlug(Product.getSlug());
        ProductDto.setReference(Product.getReference());

        return ProductDto;
    }

    // convert DTO to entity
    private Product mapToEntity(ProductDto ProductDto) {
        Product Product = new Product();
        Product.setId(ProductDto.getId());
        Product.setName(ProductDto.getName());
        Product.setPrice(ProductDto.getPrice());
        Product.setStockable(ProductDto.isStockable());
        Product.setVat(ProductDto.getVat());
        Product.setSlug(ProductDto.getSlug());
        Product.setReference(ProductDto.getReference());

        return Product;
    }
}
