package com.WebService.Webservice.project.service;

import com.WebService.Webservice.project.dto.OrderResponse;

import java.util.List;


public interface OrderService {
    OrderResponse createOrder(int productId, int customerId, int Quantity) throws Exception;

    List<OrderResponse> getAllOrders();

    OrderResponse getOrderById(int id);

    public  boolean cancelOrderById(int id);

    }