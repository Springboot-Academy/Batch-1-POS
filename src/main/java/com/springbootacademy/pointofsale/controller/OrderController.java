package com.springbootacademy.pointofsale.controller;

import com.springbootacademy.pointofsale.dto.request.ItemSaveRequestDTO;
import com.springbootacademy.pointofsale.dto.request.RequestOrderSaveDTO;
import com.springbootacademy.pointofsale.service.OrderService;
import com.springbootacademy.pointofsale.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(path = "/save")
    public ResponseEntity<StandardResponse> saveItem(@RequestBody RequestOrderSaveDTO requestOrderSaveDTO) {
        String id = orderService.addOrder(requestOrderSaveDTO);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, id + "item successfully saved", id),
                HttpStatus.CREATED
        );
    }
}
