package com.WebService.Webservice.project.entity;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class ProductOrderId implements Serializable {
    int productId;
    int orderId;
}
