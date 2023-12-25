package com.springbootacademy.pointofsale.controller;
import com.springbootacademy.pointofsale.dto.CustomerDTO;
import com.springbootacademy.pointofsale.dto.request.CustomerUpdateDTO;
import com.springbootacademy.pointofsale.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*@RestController - Define this CustomerController is a Controller
    Rest + Controller - Can get a request even from outside (Rest)
    RestAPI/ gives the servlet functionality (this annotation should include in every controller class) */
//@CrossOrigin -  To get request to inside the project
//@CrossOrigin("ABC") - Get requests only from "ABC" origin

@RestController
@CrossOrigin
@RequestMapping("api/v1/customer")

public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/save")
    public String saveCustomer(@RequestBody CustomerDTO customerDTO){
        customerService.saveCustomer(customerDTO);
        return "saved !!!";
    }

    //Update Operation
    @PutMapping("/update")
    public String updateCustomer(@RequestBody CustomerUpdateDTO customerUpdateDTO){
        //As usual sending data from controller to service
        customerService.updateCustomer(customerUpdateDTO); //" customerUpdateDTO " - must send the data that coming from the front end
        return "Updated !!!";
    }

    //SearchById CRUD operation - id is sent by frontend, get customer by his id
    @GetMapping(
            path = "/get-by-id",
            params = "id "
    )
    public CustomerDTO getCustomerById(@RequestParam(value = "id") int customerId){

        CustomerDTO customerDTO = customerService.getCustomerById(customerId);
        return customerDTO;
    }

    //also, can use this
//    public CustomerDTO getCustomerById(int id){
//        System.out.println("hi" + id);
//        return null;
//    }

    //get all customers
    @GetMapping(
            path = "/get-all-customers"
    )
    public List<CustomerDTO> getAllCustomers(){
        List<CustomerDTO> allCustomers = customerService.getAllCustomers();
        return allCustomers;
    }

    @DeleteMapping(path = "delete-customer/{id}") //path variable - id should in {} in the path
    public String deleteCustomer(@PathVariable(value = "id") int customerId){

        String deleted = customerService.deleteCustomer(customerId);
        return deleted;
    }

    //also could use this if same variable is using everywhere, if not use above method
//    public String deleteCustomer(@PathVariable int id){
//
//    }

    @GetMapping(
            path = "get-all-customers-by-active-state/{status}"
    )
    public List<CustomerDTO> getAllCustomersByActiveState(@PathVariable(value = "status") boolean activeState){

        List<CustomerDTO> customerList = customerService.getAllCustomersByActiveState(activeState);

        return customerList;
    }

}
