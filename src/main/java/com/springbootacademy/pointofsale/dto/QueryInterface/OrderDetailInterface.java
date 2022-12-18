package com.springbootacademy.pointofsale.dto.QueryInterface;

import java.util.ArrayList;
import java.util.Date;

public interface OrderDetailInterface {
     String getCustomerName();
     String getCustomerAddress();
     ArrayList getContactNumbers();
     Date getDate();
     Double getTotal();
}
