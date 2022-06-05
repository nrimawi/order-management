package com.WebService.Webservice.project.dto;
import com.WebService.Webservice.project.entity.ProductOrderPK;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class ProductOrderDto {
    private ProductOrderPK id;
    private int quantity;
    private BigDecimal price;
    private BigDecimal vat;
}
