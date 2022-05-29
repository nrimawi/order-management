//package com.WebService.Webservice.project.controller;
//
//
//import com.WebService.Webservice.project.dto.StockDto;
//import com.WebService.Webservice.project.service.StockService;
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
//@RequestMapping("/api/Stock")
//public class StockResource {
//    private final Logger log = LoggerFactory.getLogger(StockResource.class);
//    @Autowired
//    private StockService StockService; //the use of interface rather than class is important for loose coupling
//
//    // Constructor based  injection
//    public StockResource(StockService StockService) {
//        this.StockService = StockService;
//    }
//
//    @GetMapping
//    public ResponseEntity getAllStocks() {
//        try {
//            log.info("Retrieving all Stocks ");
//            return ResponseEntity.ok().body(StockService.getAllStocks()); //ResponseEntity represents an HTTP response, including headers, body, and status.
//
//        } catch (Exception ex) {
//            log.error(ex.getMessage());
//            return ResponseEntity.badRequest().body(ex.getMessage());
//        }
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity getStockById(@PathVariable(name = "id") int id) {
//
//        try {
//            log.info("Retrieving Stocks by id ");
//            return ResponseEntity.ok(StockService.getStockById(id));
//        } catch (Exception ex) {
//            log.error(ex.getMessage());
//            return ResponseEntity.badRequest().body(ex.getMessage());
//        }
//    }
//
//
//    @PostMapping
//    public ResponseEntity createStock(@Valid @RequestBody StockDto StockDto) {
//        try {
//            log.info("Creating Stock");
//            return new ResponseEntity<>(StockService.createStock(StockDto), HttpStatus.CREATED);
//        } catch (Exception ex) {
//            log.error(ex.getMessage());
//            return ResponseEntity.badRequest().body(ex.getMessage());
//        }
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity updateStock(@Valid @RequestBody StockDto StockDto
//            , @PathVariable(name = "id") int id) {
//        try {
//            log.info("Updating Stock ");
//            return new ResponseEntity<>(StockService.updateStock(StockDto, id), HttpStatus.OK);
//        } catch (Exception ex) {
//            log.error(ex.getMessage());
//
//            return ResponseEntity.badRequest().body(ex.getMessage());
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteStock(@PathVariable(name = "id") int id) {
//
//        try {
//            log.info("Deleting Stock");
//            StockService.deleteStockById(id);
////        return ResponseEntity.ok().headers(<add warnings....>).build();
//            return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
//        } catch (Exception ex) {
//            log.error(ex.getMessage());
//            return ResponseEntity.badRequest().body(ex.getMessage());
//        }
//    }
//}
