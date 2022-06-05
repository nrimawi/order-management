package com.WebService.Webservice.project.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
//Generates getters for all fields, a useful toString method, and hashCode and equals implementations that check all non-transient fields

@AllArgsConstructor   //automatically generates a constructor with a parameter for each field in your class
@NoArgsConstructor     // generates a constructor with no parameter

@Entity                 // specifies that the class is an entity and is mapped to a database table

public class ProductOrder {
    @EmbeddedId
    @JsonIgnore
    private ProductOrderPK id;
    @NotNull(message = "quantity may not be null")
    @Column
    private int quantity;
    @NotNull(message = "price may not be null")
    @Column(precision = 10, scale = 2)
    private BigDecimal price;
    @NotNull(message = "slug may not be null")
    @Column(precision = 10, scale = 2)
    private BigDecimal vat;



    public ProductOrder(Order order, Product product) {
        id = new ProductOrderPK();
        id.setOrder(order);
        id.setProduct(product);
    }
}
