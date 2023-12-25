package com.springbootacademy.pointofsale.controller;

import com.springbootacademy.pointofsale.dto.request.ItemSaveRequestDTO;
import com.springbootacademy.pointofsale.dto.request.RequestOrderSaveDTO;
import com.springbootacademy.pointofsale.service.OrderService;
import com.springbootacademy.pointofsale.utill.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/order")
@CrossOrigin

public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(
            path = "/save"
    )
    public ResponseEntity<StandardResponse> saveItem(@RequestBody RequestOrderSaveDTO requestOrderSaveDTO) {

        String id = orderService.addOrder(requestOrderSaveDTO);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "success", id),
                HttpStatus.CREATED
        );
    }
}
