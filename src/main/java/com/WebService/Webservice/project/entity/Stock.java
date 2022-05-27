package com.WebService.Webservice.project.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
//Generates getters for all fields, a useful toString method, and hashCode and equals implementations that check all non-transient fields

@AllArgsConstructor   //automatically generates a constructor with a parameter for each field in your class
@NoArgsConstructor     // generates a constructor with no parameter

@Entity                 // specifies that the class is an entity and is mapped to a database table

@Table
public class Stock {

    @Id
    @GeneratedValue
    private int id;
    @OneToOne
    @JoinColumn(name = "productId",referencedColumnName = "id")
    private  Product product;
    @NotNull(message = "quantity may not be null")
    @Column
    private int quantity;
    @NotNull(message = "updateAt may not be null")
    @Column(columnDefinition = "DATETIME")
    private Date updateAt;
}
