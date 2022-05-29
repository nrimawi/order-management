//package com.WebService.Webservice.project.controller;
//
//
//import com.WebService.Webservice.project.dto.ProductOrderDto;
//import com.WebService.Webservice.project.entity.ProductOrderId;
//import com.WebService.Webservice.project.service.ProductOrderService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//
//
//@RestController
//@RequestMapping("/api/ProductOrder")
//public class ProductOrderResource {
//    private final Logger log = LoggerFactory.getLogger(ProductOrderResource.class);
//    @Autowired
//    private ProductOrderService ProductOrderService; //the use of interface rather than class is important for loose coupling
//
//    // Constructor based  injection
//    public ProductOrderResource(ProductOrderService ProductOrderService) {
//        this.ProductOrderService = ProductOrderService;
//    }
//
//    @GetMapping
//    public ResponseEntity getAllProductOrders() {
//        try {
//            log.info("Retrieving all ProductOrders ");
//            return ResponseEntity.ok().body(ProductOrderService.getAllProductOrders()); //ResponseEntity represents an HTTP response, including headers, body, and status.
//
//        } catch (Exception ex) {
//            log.error(ex.getMessage());
//            return ResponseEntity.badRequest().body(ex.getMessage());
//        }
//    }
//
//    @GetMapping("id")
//    public ResponseEntity getProductOrderById(@RequestParam int orderId, @RequestParam int productId) {
//
//        try {
//            ProductOrderId productOrderId = new ProductOrderId();
//            productOrderId.setOrderId(orderId);
//            productOrderId.setProductId(productId);
//            log.info("Retrieving ProductOrders by id ");
//            return ResponseEntity.ok(ProductOrderService.getProductOrderById(productOrderId));
//        } catch (Exception ex) {
//            log.error(ex.getMessage());
//            return ResponseEntity.badRequest().body(ex.getMessage());
//        }
//    }
//
//
//    @PostMapping
//    public ResponseEntity createProductOrder(@Valid @RequestBody ProductOrderDto ProductOrderDto) {
//        try {
//            log.info("Creating ProductOrder");
//            return new ResponseEntity<>(ProductOrderService.createProductOrder(ProductOrderDto), HttpStatus.CREATED);
//        } catch (Exception ex) {
//            log.error(ex.getMessage());
//            return ResponseEntity.badRequest().body(ex.getMessage());
//        }
//    }
//
//    @PutMapping
//    public ResponseEntity updateProductOrder(@Valid @RequestBody ProductOrderDto ProductOrderDto
//            , @RequestParam int orderId, @RequestParam int productId) {
//        try {
//
//            log.info("Updating ProductOrder ");
//            ProductOrderId productOrderId = new ProductOrderId();
//            productOrderId.setOrderId(orderId);
//            productOrderId.setProductId(productId);
//            return new ResponseEntity<>(ProductOrderService.updateProductOrder(ProductOrderDto, productOrderId), HttpStatus.OK);
//        } catch (Exception ex) {
//            log.error(ex.getMessage());
//
//            return ResponseEntity.badRequest().body(ex.getMessage());
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteProductOrder(@PathVariable(name = "id") int id,@RequestParam int orderId, @RequestParam int productId) {
//
//        try {
//            log.info("Deleting ProductOrder");
//            ProductOrderId productOrderId = new ProductOrderId();
//            productOrderId.setOrderId(orderId);
//            productOrderId.setProductId(productId);
//            ProductOrderService.deleteProductOrderById(productOrderId);
////        return ResponseEntity.ok().headers(<add warnings....>).build();
//            return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
//        } catch (Exception ex) {
//            log.error(ex.getMessage());
//            return ResponseEntity.badRequest().body(ex.getMessage());
//        }
//    }
//}
