package com.springbootacademy.pointofsale.dto.request;

import com.springbootacademy.pointofsale.entity.Customer;
import com.springbootacademy.pointofsale.entity.OrderDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestOrderSaveDTO {
    private int customers;
    private Date date;
    private Double total;
    private List<RequestOrderDetailsSave> orderDetails;
}
