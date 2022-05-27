package com.WebService.Webservice.project.dto;
import com.WebService.Webservice.project.entity.Order;
import com.WebService.Webservice.project.entity.Product;
import com.WebService.Webservice.project.entity.ProductOrderId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
@Data
public class ProductOrderDto {
    private ProductOrderId id;
    private Order order;
    private Product product;
    private int quantity;
    private BigDecimal price;
    private BigDecimal vat;
}
