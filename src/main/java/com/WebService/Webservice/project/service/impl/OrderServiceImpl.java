package com.WebService.Webservice.project.service.impl;


import com.WebService.Webservice.project.dto.OrderDto;
import com.WebService.Webservice.project.entity.Customer;
import com.WebService.Webservice.project.entity.Order;
import com.WebService.Webservice.project.exception.ResourceNotFoundException;
import com.WebService.Webservice.project.repository.OrderRepository;
import com.WebService.Webservice.project.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service //To enable this class for component scanning
public class OrderServiceImpl implements OrderService {

    private OrderRepository OrderRepository;

    public OrderServiceImpl(OrderRepository OrderRepository) {
        this.OrderRepository = OrderRepository;
    }

    @Override
    public OrderDto createOrder(OrderDto OrderDto) {
        try {
            // convert DTO to entity
            Order Order = mapToEntity(OrderDto);
            Order newOrder = OrderRepository.save(Order);

            // convert entity to DTO
            OrderDto OrderResponse = mapToDTO(newOrder);
            return OrderResponse;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<OrderDto> getAllOrders() {
        try {
            List<Order> categories = OrderRepository.findAll();
            return categories.stream().map(Order -> mapToDTO(Order)).collect(Collectors.toList());
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public OrderDto getOrderById(int id) {
        try {
            Order Order = OrderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order", "id", id));
            return mapToDTO(Order);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public OrderDto updateOrder(OrderDto OrderDto, int id) {
        try {
            // get Order by id from the database
            Order Order = OrderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order", "id", id));
            Order.setId(Order.getId());
            Customer customer = new Customer();
            customer.setId(OrderDto.getCustomerId());
            Order.setCustomer(customer);
            Order.setOrderedAt(OrderDto.getOrderedAt());
            Order updatedOrder = OrderRepository.save(Order);
            return mapToDTO(updatedOrder);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void deleteOrderById(int id) {
        try {
            // get Order by id from the database
            Order Order = OrderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order", "id", id));
            OrderRepository.delete(Order);
        } catch (Exception e) {
            throw e;
        }
    }

    // convert Entity into DTO
    private OrderDto mapToDTO(Order Order) {
        try {
            OrderDto OrderDto = new OrderDto();
            OrderDto.setId(Order.getId());
            OrderDto.setCustomerId(Order.getCustomer().getId());
            Order.setOrderedAt(OrderDto.getOrderedAt());

            return OrderDto;
        } catch (Exception e) {
            throw e;
        }
    }

    // convert DTO to entity
    private Order mapToEntity(OrderDto OrderDto) {
        try {
            Order Order = new Order();
            Order.setId(Order.getId());
            Customer customer = new Customer();
            customer.setId(OrderDto.getCustomerId());
            Order.setCustomer(customer);
            Order.setOrderedAt(OrderDto.getOrderedAt());
            return Order;

        } catch (Exception e) {
            throw e;
        }
    }
}
