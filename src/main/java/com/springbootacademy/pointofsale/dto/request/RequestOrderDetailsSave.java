package com.springbootacademy.pointofsale.dto.request;

import com.springbootacademy.pointofsale.entity.Item;
import com.springbootacademy.pointofsale.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestOrderDetailsSave {
    private String itemName;
    private double qty;
    private Double amount;
    private int items;
}
