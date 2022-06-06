package com.WebService.Webservice.project.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class OrderResponse {
    int orderId;
    int productId;
    int customer;
    BigDecimal product_price;
    BigDecimal vat;

    int quantity;
    Date orderedAt;

}
