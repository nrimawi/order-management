package com.WebService.Webservice.project.controller;


import com.WebService.Webservice.project.dto.ProductDto;
import com.WebService.Webservice.project.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/Product")
public class ProductResource {
    private final Logger log = LoggerFactory.getLogger(ProductResource.class);
    @Autowired
    private ProductService ProductService; //the use of interface rather than class is important for loose coupling

    // Constructor based  injection
    public ProductResource(ProductService ProductService) {
        this.ProductService = ProductService;
    }

    @GetMapping
    public ResponseEntity getAllProducts() {
        try {
            log.info("Retrieving all Products ");
            return ResponseEntity.ok().body(ProductService.getAllProducts()); //ResponseEntity represents an HTTP response, including headers, body, and status.

        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getProductById(@PathVariable(name = "id") int id) {

        try {
            log.info("Retrieving Products by id ");
            return ResponseEntity.ok(ProductService.getProductById(id));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }


    @PostMapping
    public ResponseEntity createProduct(@Valid @RequestBody ProductDto ProductDto) {
        try {
            log.info("Creating Product");
            return new ResponseEntity<>(ProductService.createProduct(ProductDto), HttpStatus.CREATED);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateProduct(@Valid @RequestBody ProductDto ProductDto
            , @PathVariable(name = "id") int id) {
        try {
            log.info("Updating Product ");
            return new ResponseEntity<>(ProductService.updateProduct(ProductDto, id), HttpStatus.OK);
        } catch (Exception ex) {
            log.error(ex.getMessage());

            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(name = "id") int id) {

        try {
            log.info("Deleting Product");
            ProductService.deleteProductById(id);
//        return ResponseEntity.ok().headers(<add warnings....>).build();
            return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
