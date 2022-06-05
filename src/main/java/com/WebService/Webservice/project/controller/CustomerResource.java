package com.WebService.Webservice.project.controller;


import com.WebService.Webservice.project.dto.CustomerDto;
import com.WebService.Webservice.project.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "CRUD REST APIs for Customer resource")
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerResource {
    private final Logger log = LoggerFactory.getLogger(CustomerResource.class);
    private CustomerService CustomerService; //the use of interface rather than class is important for loose coupling

    // Constructor based  injection
    public CustomerResource(CustomerService CustomerService) {
        this.CustomerService = CustomerService;
    }
    @ApiOperation(value = "Get All Customers REST API")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server error!"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 200, message = "Successful retrieval",
                    response = CustomerDto.class, responseContainer = "Response") })
    @GetMapping
    public ResponseEntity getAllCustomers() {
        try {
            log.info("Retrieving all Customers ");
            return ResponseEntity.ok().body(CustomerService.getAllCustomers()); //ResponseEntity represents an HTTP response, including headers, body, and status.

        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @ApiOperation(value = "Get Customer By Id REST API")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server error!"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 200, message = "Successful retrieval",
                    response = CustomerDto.class, responseContainer = "Response") })
    @GetMapping("/{id}")
    public ResponseEntity getCustomerById(@PathVariable(name = "id") int id) {

        try {
            log.info("Retrieving customers by id ");
            return ResponseEntity.ok(CustomerService.getCustomerById(id));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }


    @ApiOperation(value = "Create Customer REST API")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server error!"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 201, message = " Created",
                    response = CustomerDto.class, responseContainer = "Response") })
    @PostMapping
    public ResponseEntity createCustomer(@Valid @RequestBody CustomerDto CustomerDto) {
        try {
            log.info("Creating Customer");
            return new ResponseEntity<>(CustomerService.createCustomer(CustomerDto), HttpStatus.CREATED);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @ApiOperation(value = "Update Customer REST API")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server error!"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 200, message = "Successful",
                    response = CustomerDto.class, responseContainer = "Response") })
    @PutMapping("/{id}")
    public ResponseEntity updateCustomer(@Valid @RequestBody CustomerDto CustomerDto
            , @PathVariable(name = "id") int id) {
        try {
            log.info("Updating customer ");
            return new ResponseEntity<>(CustomerService.updateCustomer(CustomerDto, id), HttpStatus.OK);
        } catch (Exception ex) {
            log.error(ex.getMessage());

            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @ApiOperation(value = "Delete Customer REST API")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server error!"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 200, message = "Successful",
                    response = CustomerDto.class, responseContainer = "Response") })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable(name = "id") int id) {

        try {
            log.info("Deleting Customer");
            CustomerService.deleteCustomerById(id);
//        return ResponseEntity.ok().headers(<add warnings....>).build();
            return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
