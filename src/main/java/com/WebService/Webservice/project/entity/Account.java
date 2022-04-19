package com.WebService.Webservice.project.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
//Generates getters for all fields, a useful toString method, and hashCode and equals implementations that check all non-transient fields

@AllArgsConstructor   //automatically generates a constructor with a parameter for each field in your class
@NoArgsConstructor     // generates a constructor with no parameter

@Entity                 // specifies that the class is an entity and is mapped to a database table

@Table
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private double balance;
    @Column
    private String type;

    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName = "id")
    private  Customer customer;

}
