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
        try {
            // convert DTO to entity
            Product Product = mapToEntity(ProductDto);
            Product newProduct = ProductRepository.save(Product);

            // convert entity to DTO
            ProductDto ProductResponse = mapToDTO(newProduct);
            return ProductResponse;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<ProductDto> getAllProducts() {
        try {
            List<Product> categories = ProductRepository.findAll();
            return categories.stream().map(Product -> mapToDTO(Product)).collect(Collectors.toList());
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ProductDto getProductById(int id) {
        try {
            Product Product = ProductRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
            return mapToDTO(Product);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ProductDto updateProduct(ProductDto ProductDto, int id) {
        try {
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
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void deleteProductById(int id) {
        try {
            // get Product by id from the database
            Product Product = ProductRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
            ProductRepository.delete(Product);
        } catch (Exception e) {
            throw e;
        }
    }

    // convert Entity into DTO
    private ProductDto mapToDTO(Product Product) {
        try {
            ProductDto ProductDto = new ProductDto();
            ProductDto.setId(Product.getId());
            ProductDto.setName(Product.getName());
            ProductDto.setPrice(Product.getPrice());
            ProductDto.setStockable(Product.isStockable());
            ProductDto.setVat(Product.getVat());
            ProductDto.setSlug(Product.getSlug());
            ProductDto.setReference(Product.getReference());

            return ProductDto;
        } catch (Exception e) {
            throw e;
        }
    }

    // convert DTO to entity
    private Product mapToEntity(ProductDto ProductDto) {
        try {
            Product Product = new Product();
            Product.setId(ProductDto.getId());
            Product.setName(ProductDto.getName());
            Product.setPrice(ProductDto.getPrice());
            Product.setStockable(ProductDto.isStockable());
            Product.setVat(ProductDto.getVat());
            Product.setSlug(ProductDto.getSlug());
            Product.setReference(ProductDto.getReference());

            return Product;
        } catch (Exception e) {
            throw e;
        }
    }
}
