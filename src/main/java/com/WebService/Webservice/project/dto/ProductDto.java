package com.WebService.Webservice.project.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
@Data
public class ProductDto {
    private int id;
    private String slug;
    private String name;
    private String reference;
    private BigDecimal price;
    private BigDecimal vat;
    private boolean stockable;
}
