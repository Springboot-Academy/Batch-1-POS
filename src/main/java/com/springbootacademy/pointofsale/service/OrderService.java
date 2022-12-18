package com.springbootacademy.pointofsale.service;

import com.springbootacademy.pointofsale.dto.paginated.PaginatedResponseOrderDetails;
import com.springbootacademy.pointofsale.dto.request.RequestOrderSaveDTO;

public interface OrderService {
    String addOrder(RequestOrderSaveDTO requestOrderSaveDTO);

    PaginatedResponseOrderDetails getAllOrderDetails(boolean status, int page, int size);
}
