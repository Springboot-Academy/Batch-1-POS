package com.springbootacademy.pointofsale.dto;

import com.springbootacademy.pointofsale.entity.enums.MeasuringUnitType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDTO {
    private int itemId;
    private String itemName;
    private MeasuringUnitType measuringUnit;
    private double balanceQty;
    private double supplierPrice;
    private double sellingPrice;
    private boolean activeState;
}
