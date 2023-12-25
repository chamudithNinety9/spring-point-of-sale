package com.springbootacademy.pointofsale.service.impl;

import com.springbootacademy.pointofsale.dto.request.RequestOrderSaveDTO;
import com.springbootacademy.pointofsale.dto.response.ItemGetResponseDTO;
import com.springbootacademy.pointofsale.entity.Order;
import com.springbootacademy.pointofsale.entity.OrderDetails;
import com.springbootacademy.pointofsale.repo.CustomerRepo;
import com.springbootacademy.pointofsale.repo.ItemRepo;
import com.springbootacademy.pointofsale.repo.OrderDetailRepo;
import com.springbootacademy.pointofsale.repo.OrderRepo;
import com.springbootacademy.pointofsale.service.OrderService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional

public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private OrderDetailRepo orderDetailRepo;

    @Override
    @Transactional
    public String addOrder(RequestOrderSaveDTO requestOrderSaveDTO) {
        //send data to db relevant to the Order
        Order order = new Order(
                customerRepo.getById(requestOrderSaveDTO.getCustomers()),
                requestOrderSaveDTO.getDate(),
                requestOrderSaveDTO.getTotal()
        );
        orderRepo.save(order);

        if(orderRepo.existsById(order.getOrderId())){
            List<OrderDetails> orderDetails =
                    modelMapper.map(requestOrderSaveDTO.getOrderDetail(),new TypeToken<List<OrderDetails>>(){}.getType());

            for (int i=0; i < orderDetails.size(); i++){
                orderDetails.get(i).setOrders(order);
                orderDetails.get(i).setItems(itemRepo.getById(requestOrderSaveDTO.getOrderDetail().get(i).getItems()));
            }
            if (orderDetails.size() > 0){
                orderDetailRepo.saveAll(orderDetails);
            }
            return "saved";
        }
        return null;
    }
}
