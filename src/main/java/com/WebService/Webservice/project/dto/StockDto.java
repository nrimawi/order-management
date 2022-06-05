package com.WebService.Webservice.project.dto;
import com.WebService.Webservice.project.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
//This class is for Data transferring for stocks object
@Data
public class StockDto {
    private int id;
    private int productId;
    private int quantity;
    private Date updateAt;
}
