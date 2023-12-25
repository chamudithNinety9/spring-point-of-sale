package com.springbootacademy.pointofsale.service.impl;

import com.springbootacademy.pointofsale.dto.CustomerDTO;
import com.springbootacademy.pointofsale.dto.request.CustomerUpdateDTO;
import com.springbootacademy.pointofsale.entity.Customer;
import com.springbootacademy.pointofsale.repo.CustomerRepo;
import com.springbootacademy.pointofsale.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public String saveCustomer(CustomerDTO customerDTO) {


        //if we're getting something from DB, we're getting through "ENTITY", So as we can save to DB
        //also with "ENTITY" only at Service layer to Repo layer
        //So that, converting DTO to ENTITY in here
        Customer customer = new Customer(
                customerDTO.getCustomerId(),
                customerDTO.getCustomerName(),
                customerDTO.getCustomerAddress(),
                customerDTO.getCustomerSalary(),
                customerDTO.getContactNumber(),
                customerDTO.getNic(),
                customerDTO.isActive()
        );
        customerRepo.save(customer);
        return customerDTO.getCustomerName();
    }

    @Override
    public String updateCustomer(CustomerUpdateDTO customerUpdateDTO) {

        //Before update, check if same CustomerId is in the database
        if (customerRepo.existsById(customerUpdateDTO.getCustomerId())){ //customerRepo - Database
            //to create an update,first get customer on database

            /*if we're getting something from DB, we're getting through "ENTITY",
                So as we can update to DB also with "ENTITY" Type only */

            Customer customer = customerRepo.getById(customerUpdateDTO.getCustomerId());
            //now above "customer" object has the relevant all customer details
            //which is already exist customer details by same entity type(danata db eke tyena customer details serma gnnwa)

            //Now "customer" obj has the customer's data,
            //now how can we update ? - set CustomerName (customerUpdateDTO.getCustomerName())
            //customerUpdateDTO.getCustomerName() - frontend data
            customer.setCustomerName(customerUpdateDTO.getCustomerName());
            customer.setCustomerAddress(customerUpdateDTO.getCustomerAddress());
            customer.setCustomerSalary(customerUpdateDTO.getCustomerSalary());

            //Now all u have to do is send repo/database this object "customer"
            customerRepo.save(customer); //use "save" method for both save and update

            return customerUpdateDTO.getCustomerName() + "Updated Successful !!! ";

        }else{
            throw new RuntimeException("No user found !!!");
        }

    }

    @Override
    public CustomerDTO getCustomerById(int customerId) {

        if (customerRepo.existsById(customerId)){
            Customer customer = customerRepo.getById(customerId);

            //as this method it should return "CustomerDTO", but you have an entity "Customer"
            //so convert customer to customerDTO
            CustomerDTO customerDTO = new CustomerDTO(
                    customer.getCustomerId(),
                    customer.getCustomerName(),
                    customer.getCustomerAddress(),
                    customer.getCustomerSalary(),
                    customer.getContactNumber(),
                    customer.getNic(),
                    customer.isActive()
            );

            return customerDTO;
        }else{
            throw new RuntimeException("No Customer");
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {

        List<Customer> getAllCustomers = customerRepo.findAll();
        List<CustomerDTO> customerDTOList = new ArrayList<>();

        //getAllCustomers eke plaweni eka customer object ekata assign wenwa, e widihta serama ekin eka assign wenwa
        for (Customer customer: getAllCustomers ) {
            //Create DTO from above List<CustomerDTO>
            CustomerDTO customerDTO = new CustomerDTO(
                    customer.getCustomerId(),
                    customer.getCustomerName(),
                    customer.getCustomerAddress(),
                    customer.getCustomerSalary(),
                    customer.getContactNumber(),
                    customer.getNic(),
                    customer.isActive()
                    //after adding this, the data stored in customer object came to customerDTO now.
            );

            customerDTOList.add(customerDTO);
        }
        return customerDTOList;
    }

    @Override
    public String deleteCustomer(int customerId) {
        if(customerRepo.existsById(customerId)){
            customerRepo.deleteById(customerId);

            return "Customer Id" + customerId +"Deleted Successfully !!!";
        }else{
            throw new RuntimeException("User Not exists !!!");
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomersByActiveState(boolean activeState) {

        List<Customer> getAllCustomers = customerRepo.findAllByActiveEquals(activeState);
        List<CustomerDTO> customerDTOList = new ArrayList<>();

        //getAllCustomers eke plaweni eka customer object ekata assign wenwa, e widihta serama ekin eka assign wenwa
        for (Customer customer: getAllCustomers ) {
            //Create DTO from above List<CustomerDTO>
            CustomerDTO customerDTO = new CustomerDTO(
                    customer.getCustomerId(),
                    customer.getCustomerName(),
                    customer.getCustomerAddress(),
                    customer.getCustomerSalary(),
                    customer.getContactNumber(),
                    customer.getNic(),
                    customer.isActive()
                    //after adding this, the data stored in customer object came to customerDTO now.
            );

            customerDTOList.add(customerDTO);
        }
        return customerDTOList;
    }


}
