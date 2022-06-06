package com.WebService.Webservice.project.controller;


import com.WebService.Webservice.project.dto.ProductDto;
import com.WebService.Webservice.project.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "CRUD REST APIs for Product resource")
@RestController
@RequestMapping("/api/v1/product")
public class ProductResource {
    private final Logger log = LoggerFactory.getLogger(ProductResource.class);
    private final ProductService ProductService; //the use of interface rather than class is important for loose coupling

    // Constructor based  injection
    public ProductResource(ProductService ProductService) {
        this.ProductService = ProductService;
    }

    @ApiOperation(value = "Get All Products REST API")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server error!"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 200, message = "Successful retrieval",
                    response = ProductDto.class, responseContainer = "Response")})
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

    @ApiOperation(value = "Get Product By Id REST API")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server error!"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 200, message = "Successful retrieval",
                    response = ProductDto.class, responseContainer = "Response")})
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


    @ApiOperation(value = "Create Product REST API")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server error!"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 201, message = "Created",
                    response = ProductDto.class, responseContainer = "Response")})
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

    @ApiOperation(value = "Update Product REST API")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server error!"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 200, message = "Successful",
                    response = ProductDto.class, responseContainer = "Response")})
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

    @ApiOperation(value = "Delete Product REST API")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server error!"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 200, message = "Successful",
                    response = ProductDto.class, responseContainer = "Response")})
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

