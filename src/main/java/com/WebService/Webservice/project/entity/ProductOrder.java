package com.WebService.Webservice.project.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
//Generates getters for all fields, a useful toString method, and hashCode and equals implementations that check all non-transient fields

@AllArgsConstructor   //automatically generates a constructor with a parameter for each field in your class
@NoArgsConstructor     // generates a constructor with no parameter

@Entity                 // specifies that the class is an entity and is mapped to a database table

public class ProductOrder {
    @EmbeddedId
    private  ProductOrderId id;
    @ManyToOne
    @JoinColumn(name = "productId",referencedColumnName = "id",insertable =  false, updatable = false)
    private Order order;
    @ManyToOne
    @JoinColumn(name = "orderId",referencedColumnName = "id",insertable =  false, updatable = false)
    private Product product;
    @NotNull(message = "quantity may not be null")
    @Column
    private int quantity;
    @NotNull(message = "price may not be null")
    @Column(precision = 10, scale = 2)
    private BigDecimal price;
    @NotNull(message = "slug may not be null")
    @Column(precision = 10, scale = 2)
    private BigDecimal vat;

}
