package com.WebService.Webservice.project.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
@Data
//Generates getters for all fields, a useful toString method, and hashCode and equals implementations that check all non-transient fields
@AllArgsConstructor   //automatically generates a constructor with a parameter for each field in your class
@NoArgsConstructor     // generates a constructor with no parameter

@Entity                 // specifies that the class is an entity and is mapped to a database table

@Table
public class Product  {

    @Id
    @GeneratedValue
    private int id;
    @NotNull(message = "slug may not be null")
    @Column(columnDefinition = "TINYTEXT")
    private String slug;
    @NotNull(message = "name may not be null")
    @Column(columnDefinition = "TINYTEXT")
    private String name;
    @NotNull(message = "reference may not be null")
    @Column(columnDefinition = "TINYTEXT")
    private String reference;
    @NotNull(message = "price may not be null")
    @Column(precision = 10, scale = 2)
    private BigDecimal price;
    @NotNull(message = "slug may not be null")
    @Column(precision = 10, scale = 2)
    private BigDecimal vat;
    @NotNull(message = "stockable may not be null")
    private boolean stockable;

}
