package com.WebService.Webservice.project.controller;


import com.WebService.Webservice.project.dto.OrderDto;
import com.WebService.Webservice.project.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "CRUD REST APIs for Order resource")
@RestController
@RequestMapping("/api/v1/order")
public class OrderManagement {
    private final Logger log = LoggerFactory.getLogger(OrderManagement.class);
    private OrderService OrderService; //the use of interface rather than class is important for loose coupling

    // Constructor based  injection
    public OrderManagement(OrderService OrderService) {
        this.OrderService = OrderService;
    }

    @ApiOperation(value = "Get All Orders REST API")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server error!"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 200, message = "Successful retrieval",
                    response = OrderDto.class, responseContainer = "Response")})
    @GetMapping
    public ResponseEntity getAllOrders() {
        try {
            log.info("Retrieving all Orders ");
            return ResponseEntity.ok().body(OrderService.getAllOrders()); //ResponseEntity represents an HTTP response, including headers, body, and status.

        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @ApiOperation(value = "Get Order By Id REST API")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server error!"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 200, message = "Successful retrieval",
                    response = OrderDto.class, responseContainer = "Response")})
    @GetMapping("/{id}")
    public ResponseEntity getOrderById(@PathVariable(name = "id") int id) {

        try {
            log.info("Retrieving Orders by id ");
            return ResponseEntity.ok(OrderService.getOrderById(id));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }


    @ApiOperation(value = "Create Order REST API")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server error!"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 201, message = "Created",
                    response = OrderDto.class, responseContainer = "Response")})
    @PostMapping
    public ResponseEntity createOrder(

            @RequestParam(value = "CustomerId", required = true) int customerId,
            @RequestParam(value = "ProductId", required = true) int productId,
            @RequestParam(value = "Quantity", required = true) int quantity
    ) {
        try {
            log.info("Creating Order");
            return new ResponseEntity<>(OrderService.createOrder(customerId, productId, quantity), HttpStatus.CREATED);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }


    @ApiOperation(value = "Cancel Order and update the stock")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server error!"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 200, message = "Successful",
                    response = OrderDto.class, responseContainer = "Response")})
    @PostMapping("/cancel")
    public ResponseEntity cancelOrderById(@RequestParam(value = "OrderId", required = true) int orderId) {

        try {
            log.info("Canceling Order ");
            if (OrderService.cancelOrderById(orderId))
                return new ResponseEntity<>("Canceling order and updating stock has been done successfully", HttpStatus.OK);
            else

                return new ResponseEntity<>("Canceling order failed", HttpStatus.BAD_REQUEST);


        } catch (Exception ex) {
            log.error(ex.getMessage());

            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
