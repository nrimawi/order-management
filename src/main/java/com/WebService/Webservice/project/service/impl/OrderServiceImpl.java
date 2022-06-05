package com.WebService.Webservice.project.service.impl;


import com.WebService.Webservice.project.dto.OrderDto;
import com.WebService.Webservice.project.dto.ProductDto;
import com.WebService.Webservice.project.dto.StockDto;
import com.WebService.Webservice.project.entity.*;
import com.WebService.Webservice.project.exception.ResourceNotFoundException;
import com.WebService.Webservice.project.repository.OrderRepository;
import com.WebService.Webservice.project.repository.ProductOrderRepository;
import com.WebService.Webservice.project.service.OrderService;
import com.WebService.Webservice.project.service.ProductService;
import com.WebService.Webservice.project.service.StockService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service //To enable this class for component scanning
public class OrderServiceImpl implements OrderService {

    private OrderRepository OrderRepository;
    private ProductOrderRepository _productOrderRepository;
    private ProductService _productService;
    private StockService _stockService;
    private ModelMapper mapper;

    public OrderServiceImpl(OrderRepository OrderRepository, ModelMapper mapper, ProductOrderRepository productOrderRepository, ProductService productService, StockService stockService) {
        this.OrderRepository = OrderRepository;
        this.mapper = mapper;
        this._productOrderRepository = productOrderRepository;
        this._productService = productService;
        this._stockService = stockService;
    }

    @Override
    public OrderDto createOrder(int customerId, int productId, int quantity) throws Exception {
        try {

            StockDto stockDto = _stockService.getStockByProductId(productId);
            int current_quantity = stockDto.getQuantity();
            if (quantity >= current_quantity)
                throw new Exception("Not Sufficient Quantity for product id =" + productId + "Current quantity=" + current_quantity);

            //create new order
            Order order = new Order();
            order.setOrderedAt(new Date());
            Customer customer = new Customer();
            customer.setId(customerId);
            order.setCustomer(customer);
            Order newOrder = OrderRepository.save(order);

            //create new product_order
            ProductDto productDto = _productService.getProductById(productId);

            ProductOrder productOrder = new ProductOrder(mapper.map(newOrder,Order.class),mapper.map(productDto, Product.class));
            productOrder.setPrice(productDto.getPrice());
            productOrder.setVat(productDto.getVat());
            productOrder.setQuantity(quantity);
            _productOrderRepository.save(productOrder);


            //update stock
            stockDto.setQuantity(stockDto.getQuantity() - quantity);
            stockDto.setUpdateAt(new Date());
            _stockService.updateStock(stockDto, stockDto.getId());

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
            OrderDto OrderDto = mapper.map(Order,OrderDto.class);
            OrderDto.setCustomerId(Order.getCustomer().getId());

            return OrderDto;
        } catch (Exception e) {
            throw e;
        }
    }

    // convert DTO to entity
    private Order mapToEntity(OrderDto OrderDto) {
        try {
            Order Order = mapper.map(OrderDto,Order.class);
            Customer customer = new Customer();
            customer.setId(OrderDto.getCustomerId());
            Order.setCustomer(customer);
            return Order;

        } catch (Exception e) {
            throw e;
        }
    }
}
