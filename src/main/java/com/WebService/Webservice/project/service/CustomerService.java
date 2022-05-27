package com.WebService.Webservice.project.service;

import com.WebService.Webservice.project.dto.CustomerDto;

import java.util.List;


public interface CustomerService {
    CustomerDto createCustomer(CustomerDto CustomerDto);

    List<CustomerDto> getAllCustomers();

    CustomerDto getCustomerById(long id);

    CustomerDto updateCustomer(CustomerDto CustomerDto, long id);

    void deleteCustomerById(long id);
}
