package com.WebService.Webservice.project.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CustomerDto {
    private int id;
    private String firstName;
    private String lastName;
    private Date bornAt;
}
