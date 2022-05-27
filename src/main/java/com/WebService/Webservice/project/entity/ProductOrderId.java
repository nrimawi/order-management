package com.WebService.Webservice.project.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ProductOrderId implements Serializable {
    int productId;
    int orderId;
}
