package com.WebService.Webservice.project.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
//Generates getters for all fields, a useful toString method, and hashCode and equals implementations that check all non-transient fields
@AllArgsConstructor   //automatically generates a constructor with a parameter for each field in your class
@NoArgsConstructor     // generates a constructor with no parameter
@Entity                 // specifies that the class is an entity and is mapped to a database table

@Table(name = "`order`")
public class Order {
    @Id
    @GeneratedValue
    private int id;
    @NotNull(message = "name may not be null")
    @Column
    private Date orderedAt;
    @ManyToOne
    @JoinColumn(name = "customerId",referencedColumnName = "id")
    private  Customer customer;
}
