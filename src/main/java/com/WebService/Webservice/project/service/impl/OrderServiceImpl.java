package com.WebService.Webservice.project.service.impl;


import com.WebService.Webservice.project.dto.OrderResponse;
import com.WebService.Webservice.project.dto.ProductDto;
import com.WebService.Webservice.project.dto.StockDto;
import com.WebService.Webservice.project.entity.Customer;
import com.WebService.Webservice.project.entity.Order;
import com.WebService.Webservice.project.entity.Product;
import com.WebService.Webservice.project.entity.ProductOrder;
import com.WebService.Webservice.project.exception.ResourceNotFoundException;
import com.WebService.Webservice.project.repository.OrderRepository;
import com.WebService.Webservice.project.repository.ProductOrderRepository;
import com.WebService.Webservice.project.service.OrderService;
import com.WebService.Webservice.project.service.ProductService;
import com.WebService.Webservice.project.service.StockService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service //To enable this class for component scanning
public class OrderServiceImpl implements OrderService {

    private OrderRepository _orderRepository;
    private ProductOrderRepository _productOrderRepository;
    private ProductService _productService;
    private StockService _stockService;
    private ModelMapper mapper;

    public OrderServiceImpl(OrderRepository OrderRepository, ModelMapper mapper, ProductOrderRepository productOrderRepository, ProductService productService, StockService stockService) {
        this._orderRepository = OrderRepository;
        this.mapper = mapper;
        this._productOrderRepository = productOrderRepository;
        this._productService = productService;
        this._stockService = stockService;
    }

    @Override
    public OrderResponse createOrder(int customerId, int productId, int quantity) throws Exception {
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
            Order newOrder = _orderRepository.save(order);

            //create new product_order
            ProductDto productDto = _productService.getProductById(productId);

            ProductOrder productOrder = new ProductOrder(mapper.map(newOrder, Order.class), mapper.map(productDto, Product.class));
            productOrder.setPrice(productDto.getPrice());
            productOrder.setVat(productDto.getVat());
            productOrder.setQuantity(quantity);
            _productOrderRepository.save(productOrder);


            //update stock
            stockDto.setQuantity(stockDto.getQuantity() - quantity);
            stockDto.setUpdateAt(new Date());
            _stockService.updateStock(stockDto, stockDto.getId());

            //Build the response
            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setOrderId(newOrder.getId());
            orderResponse.setOrderedAt(newOrder.getOrderedAt());
            orderResponse.setProductId(productId);
            orderResponse.setCustomer(customerId);
            orderResponse.setQuantity(quantity);
            orderResponse.setVat(productDto.getVat());
            orderResponse.setProduct_price(productDto.getPrice());
            return orderResponse;

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        List<OrderResponse> orderResponses = new ArrayList<>();
        try {
            List<ProductOrder> productOrders = _productOrderRepository.findAll();
            for (int i = 0; i < productOrders.size(); i++) {
                int orderId = productOrders.get(i).getId().getOrder().getId();
                Order order = _orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));

                OrderResponse orderResponse = new OrderResponse();
                orderResponse.setOrderId(order.getId());
                orderResponse.setOrderedAt(order.getOrderedAt());
                orderResponse.setProductId(productOrders.get(i).getId().getProduct().getId());
                orderResponse.setCustomer(order.getCustomer().getId());
                orderResponse.setQuantity(productOrders.get(i).getQuantity());
                orderResponse.setVat(productOrders.get(i).getVat());
                orderResponse.setProduct_price(productOrders.get(i).getPrice());
                orderResponses.add(orderResponse);
            }
            return orderResponses;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public OrderResponse getOrderById(int id) {
        try {
            Order order = _orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order", "id", id));
            int index;
            List<ProductOrder> productOrders = _productOrderRepository.findAll();
            for (index = 0; index < productOrders.size(); index++)
                if (productOrders.get(index).getId().getOrder().getId() == id) break;

            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setOrderId(order.getId());
            orderResponse.setOrderedAt(order.getOrderedAt());
            orderResponse.setProductId(productOrders.get(index).getId().getProduct().getId());
            orderResponse.setCustomer(order.getCustomer().getId());
            orderResponse.setQuantity(productOrders.get(index).getQuantity());
            orderResponse.setVat(productOrders.get(index).getVat());
            orderResponse.setProduct_price(productOrders.get(index).getPrice());
            return orderResponse;


        } catch (Exception e) {
            throw e;
        }
    }

    public boolean cancelOrderById(int id) {


        try {
            Order order = _orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order", "id", id));

            // get the product orders with order_id =id
            List<ProductOrder> productOrders = _productOrderRepository.findAll();
            for (int index = 0; index < productOrders.size(); index++)
                if (productOrders.get(index).getId().getOrder().getId() == id) {

                    //update stock quantity
                    StockDto stockDto = _stockService.getStockByProductId(productOrders.get(index).getId().getProduct().getId());
                    stockDto.setQuantity(stockDto.getQuantity() + productOrders.get(index).getQuantity());
                    stockDto.setUpdateAt(new Date());
                    _stockService.updateStock(stockDto, stockDto.getId());


                    _productOrderRepository.delete(productOrders.get(index));
                    _orderRepository.delete(order);
                }

        } catch (Exception e) {
            throw e;
        }
        return true;
    }
}
