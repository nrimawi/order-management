package com.WebService.Webservice.project.controller;


import com.WebService.Webservice.project.dto.OrderDto;
import com.WebService.Webservice.project.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/Order")
public class OrderResource {
    private final Logger log = LoggerFactory.getLogger(OrderResource.class);
    @Autowired
    private OrderService OrderService; //the use of interface rather than class is important for loose coupling

    // Constructor based  injection
    public OrderResource(OrderService OrderService) {
        this.OrderService = OrderService;
    }

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


    @PostMapping
    public ResponseEntity createOrder(@Valid @RequestBody OrderDto OrderDto) {
        try {
            log.info("Creating Order");
            return new ResponseEntity<>(OrderService.createOrder(OrderDto), HttpStatus.CREATED);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateOrder(@Valid @RequestBody OrderDto OrderDto
            , @PathVariable(name = "id") int id) {
        try {
            log.info("Updating Order ");
            return new ResponseEntity<>(OrderService.updateOrder(OrderDto, id), HttpStatus.OK);
        } catch (Exception ex) {
            log.error(ex.getMessage());

            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable(name = "id") int id) {

        try {
            log.info("Deleting Order");
            OrderService.deleteOrderById(id);
//        return ResponseEntity.ok().headers(<add warnings....>).build();
            return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
