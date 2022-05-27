package com.WebService.Webservice.project.service;

import com.WebService.Webservice.project.dto.CustomerDto;

import java.util.List;


public interface CustomerService {
    CustomerDto createCustomer(CustomerDto CustomerDto);

    List<CustomerDto> getAllCustomers();

    CustomerDto getCustomerById(int id);

    CustomerDto updateCustomer(CustomerDto CustomerDto, int id);

    void deleteCustomerById(int id);
}
