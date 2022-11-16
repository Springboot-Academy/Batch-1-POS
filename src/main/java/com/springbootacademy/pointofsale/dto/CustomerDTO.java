package com.springbootacademy.pointofsale.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO {
    private String customerName;
    private String customerAddress;
    private double customerSalary;
    private ArrayList contactNumbers;
    private String nic;
    private boolean activeState;


    public CustomerDTO(String customerName) {
        this.customerName = customerName;
    }
}
