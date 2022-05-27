package com.WebService.Webservice.project.service.impl;


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
    public CustomerDto getCustomerById(int id) {
        Customer Customer = CustomerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
        return mapToDTO(Customer);
    }
    @Override
    public CustomerDto updateCustomer(CustomerDto CustomerDto, int id) {
        // get Customer by id from the database
        Customer Customer = CustomerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
        Customer.setId(CustomerDto.getId());
        Customer.setFirstName(CustomerDto.getFirstName());
        Customer.setLastName(Customer.getLastName());
        Customer.setBornAt(Customer.getBornAt());
        Customer updatedCustomer = CustomerRepository.save(Customer);
        return mapToDTO(updatedCustomer);
    }
    @Override
    public void deleteCustomerById(int id) {
        // get Customer by id from the database
        Customer Customer = CustomerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
        CustomerRepository.delete(Customer);
    }
    // convert Entity into DTO
    private CustomerDto mapToDTO(Customer Customer) {

        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(Customer.getId());
        customerDto.setId(Customer.getId());
        customerDto.setFirstName(Customer.getFirstName());
        customerDto.setLastName(Customer.getLastName());
        customerDto.setBornAt(Customer.getBornAt());

        return customerDto;
    }

    // convert DTO to entity
    private Customer mapToEntity(CustomerDto CustomerDto) {
        Customer Customer = new Customer();
        Customer.setId(CustomerDto.getId());
        Customer.setFirstName(CustomerDto.getFirstName());
        Customer.setLastName(CustomerDto.getLastName());
        return Customer;
    }
}
