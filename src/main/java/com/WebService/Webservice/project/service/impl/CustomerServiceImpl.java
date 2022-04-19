package com.WebService.Webservice.project.service.impl;


import com.WebService.Webservice.project.dto.CustomerDto;
import com.WebService.Webservice.project.dto.CustomerDto;
import com.WebService.Webservice.project.entity.Customer;
import com.WebService.Webservice.project.exception.ResourceNotFoundException;
import com.WebService.Webservice.project.repository.CustomerRepository;
import com.WebService.Webservice.project.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service //To enable this class for component scanning
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository CustomerRepository;

    public CustomerServiceImpl(CustomerRepository CustomerRepository) {
        this.CustomerRepository = CustomerRepository;
    }

    @Override
    public CustomerDto createCustomer(CustomerDto CustomerDto) {

        // convert DTO to entity
        Customer Customer = mapToEntity(CustomerDto);
        Customer newCustomer = CustomerRepository.save(Customer);

        // convert entity to DTO
        CustomerDto CustomerResponse = mapToDTO(newCustomer);
        return CustomerResponse;
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<Customer> categories = CustomerRepository.findAll();
        return categories.stream().map(Customer -> mapToDTO(Customer)).collect(Collectors.toList());
    }



    @Override
    public CustomerDto getCustomerById(long id) {
        Customer Customer = CustomerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
        return mapToDTO(Customer);
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto CustomerDto, long id) {
        // get Customer by id from the database
        Customer Customer = CustomerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
        Customer.setAge(CustomerDto.getAge());
        Customer.setName(CustomerDto.getName());
        Customer updatedCustomer = CustomerRepository.save(Customer);
        return mapToDTO(updatedCustomer);
    }

    @Override
    public void deleteCustomerById(long id) {
        // get Customer by id from the database
        Customer Customer = CustomerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
        CustomerRepository.delete(Customer);
    }

    // convert Entity into DTO
    private CustomerDto mapToDTO(Customer Customer){
        CustomerDto CustomerDto = new CustomerDto();
        CustomerDto.setId(Customer.getId());
        CustomerDto.setAge(Customer.getAge());
        CustomerDto.setName(Customer.getName());

        return CustomerDto;
    }

    // convert DTO to entity
    private Customer mapToEntity(CustomerDto CustomerDto){
        Customer Customer = new Customer();
        Customer.setName(CustomerDto.getName());
        Customer.setAge(CustomerDto.getAge());
        return Customer;
    }
}
