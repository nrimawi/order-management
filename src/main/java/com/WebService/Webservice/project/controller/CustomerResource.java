package com.WebService.Webservice.project.controller;


import com.WebService.Webservice.project.dto.CustomerDto;
import com.WebService.Webservice.project.exception.BadRequestException;
import com.WebService.Webservice.project.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/customer")
public class CustomerResource {
    private final Logger log = LoggerFactory.getLogger(CustomerResource.class);

    //    @Autowired //@Autowired annotation is used for dependency injection.In spring boot application, all loaded beans are eligible for auto wiring to another bean. The annotation @Autowired in spring boot is used to auto-wire a bean into another bean.
    private CustomerService customerService; //the use of interface rather than class is important for loose coupling

    // Constructor based  injection
    public CustomerResource(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCategories() {
        return ResponseEntity.ok().body(customerService.getAllCustomers()); //ResponseEntity represents an HTTP response, including headers, body, and status.
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }


    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody CustomerDto customerDto) {
        if (customerDto.getId() != null) {
            log.error("Cannot have an ID {}", customerDto);
            throw new BadRequestException(CustomerResource.class.getSimpleName(),
                    "Id");
        }
        return new ResponseEntity<>(customerService.createCustomer(customerDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@Valid @RequestBody CustomerDto customerDto
            , @PathVariable(name = "id") long id) {
        return new ResponseEntity<>(customerService.updateCustomer(customerDto, id), HttpStatus.OK);
    }

    //    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable(name = "id") long id) {
        customerService.deleteCustomerById(id);
//        return ResponseEntity.ok().headers(<add warnings....>).build();
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }
}
