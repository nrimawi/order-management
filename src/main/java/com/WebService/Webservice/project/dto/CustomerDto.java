package com.WebService.Webservice.project.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data //Generates getters for all fields, a useful toString method, and hashCode and equals implementations that check all non-transient fields

public class CustomerDto {
    private Long id;
    private String name;
    private int age;

}
