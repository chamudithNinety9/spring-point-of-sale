package com.springbootacademy.pointofsale.dto.request;

import com.springbootacademy.pointofsale.entity.Customer;
import com.springbootacademy.pointofsale.entity.OrderDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

//OrderDetails table has data that coming from multiple tables,
// So from controller we should get that data neh(get those above data to one controller)

@AllArgsConstructor
@NoArgsConstructor
@Data

public class RequestOrderSaveDTO {

    private int customers;
    private Date date;
    private Double total;
    //above 3 are unique for order

    private List<RequestOrderDetailsSave> orderDetail;
    //alter data list is coming from RequestOrderDetailsSave
}
