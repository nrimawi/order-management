package com.WebService.Webservice.project.service;

import com.WebService.Webservice.project.dto.OrderDto;

import java.util.List;


public interface OrderService {
    OrderDto createOrder(OrderDto OrderDto);

    List<OrderDto> getAllOrders();

    OrderDto getOrderById(long id);

    OrderDto updateOrder(OrderDto OrderDto, long id);

    void deleteOrder00ById(long id);
}
