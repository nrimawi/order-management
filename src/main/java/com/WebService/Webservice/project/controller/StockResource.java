package com.WebService.Webservice.project.controller;


import com.WebService.Webservice.project.dto.StockDto;
import com.WebService.Webservice.project.service.StockService;
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

@Api(value = "CRUD REST APIs for Stock resource")
@RestController
@RequestMapping("/api/v1/Stock")
public class StockResource {
    private final Logger log = LoggerFactory.getLogger(StockResource.class);
    private StockService StockService; //the use of interface rather than class is important for loose coupling

    // Constructor based  injection
    public StockResource(StockService StockService) {
        this.StockService = StockService;
    }

    @ApiOperation(value = "Get All Stocks REST API")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server error!"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 200, message = "Successful retrieval",
                    response = StockDto.class, responseContainer = "Response")})
    @GetMapping
    public ResponseEntity getAllStocks() {
        try {
            log.info("Retrieving all Stocks ");
            return ResponseEntity.ok().body(StockService.getAllStocks()); //ResponseEntity represents an HTTP response, including headers, body, and status.

        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @ApiOperation(value = "Get Stock By Id REST API")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server error!"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 200, message = "Successful retrieval",
                    response = StockDto.class, responseContainer = "Response")})
    @GetMapping("/{id}")
    public ResponseEntity getStockById(@PathVariable(name = "id") int id) {

        try {
            log.info("Retrieving Stocks by id ");
            return ResponseEntity.ok(StockService.getStockById(id));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }


    @ApiOperation(value = "Create Stock REST API")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server error!"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 201, message = "Created",
                    response = StockDto.class, responseContainer = "Response")})
    @PostMapping
    public ResponseEntity createStock(@Valid @RequestBody StockDto StockDto) {
        try {
            log.info("Creating Stock");
            return new ResponseEntity<>(StockService.createStock(StockDto), HttpStatus.CREATED);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @ApiOperation(value = "Update Stock REST API")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server error!"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 200, message = "Successful",
                    response = StockDto.class, responseContainer = "Response")})
    @PutMapping("/{id}")
    public ResponseEntity updateStock(@Valid @RequestBody StockDto StockDto
            , @PathVariable(name = "id") int id) {
        try {
            log.info("Updating Stock ");
            return new ResponseEntity<>(StockService.updateStock(StockDto, id), HttpStatus.OK);
        } catch (Exception ex) {
            log.error(ex.getMessage());

            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @ApiOperation(value = "Delete Stock REST API")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server error!"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 200, message = "Successful",
                    response = StockDto.class, responseContainer = "Response")})
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStock(@PathVariable(name = "id") int id) {

        try {
            log.info("Deleting Stock");
            StockService.deleteStockById(id);
//        return ResponseEntity.ok().headers(<add warnings....>).build();
            return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
