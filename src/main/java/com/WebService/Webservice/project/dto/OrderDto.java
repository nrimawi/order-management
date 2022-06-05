package com.WebService.Webservice.project.dto;
import lombok.Data;
import java.util.Date;
@Data
//Generates getters for all fields, a useful toString method, and hashCode and equals implementations that check all non-transient fields
//This class is for Data transferring for order object
public class OrderDto {
    private int id;
    private Date orderedAt;
    private int customerId;
}
