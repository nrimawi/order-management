package com.WebService.Webservice.project.service;

import com.WebService.Webservice.project.dto.OrderDto;

import java.util.List;


public interface OrderService {
    OrderDto createOrder(int productId,int customerId,int Quantity) throws Exception;

    List<OrderDto> getAllOrders();

    OrderDto getOrderById(int id);

    OrderDto updateOrder(OrderDto OrderDto, int id);

    void deleteOrderById(int id);
}
