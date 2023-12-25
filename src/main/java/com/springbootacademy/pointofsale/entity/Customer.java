package com.springbootacademy.pointofsale.entity;

//Create customer table's entities

import com.vladmihalcea.hibernate.type.json.JsonType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Set;

//entity class is a example of a pojo - plain old java object (an object which is not extended from any class)

@Entity //Let the container know this is the entity class/ Also called as Model
@Table(name = "customer") //how should appear database's customer table name ("customer")

//to use json in a class
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonType.class)
})


public class Customer {

    @Id //Used to define the primary key in the table
    @Column(name = "customer_id",length = 45) //how should appear database's column name ("customer_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int customerId;

    @Column(name = "customer_name", length = 100, nullable = false) // "nullable" - NOT NULL
    private String customerName;

    @Column(name="customer_address", length = 255)
    private String customerAddress;

    @Column(name="customer_salary", nullable = false)
    private double customerSalary;

    @Type(type = "json")
    @Column(name="contact_numbers", columnDefinition = "json")
    private ArrayList contactNumber;

    @Column(name="nic")
    private String nic;

    @Column(name = "active_state")
    private boolean active;

    //One to many Mapping (Customer is One, Order is many)
    @OneToMany(mappedBy = "customer")
    private Set<Order> orders;

    public Customer() {
    }

    public Customer(int customerId, String customerName, String customerAddress, double customerSalary, ArrayList contactNumber, String nic, boolean active) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerSalary = customerSalary;
        this.contactNumber = contactNumber;
        this.nic = nic;
        this.active = active;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public double getCustomerSalary() {
        return customerSalary;
    }

    public void setCustomerSalary(double customerSalary) {
        this.customerSalary = customerSalary;
    }

    public ArrayList getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(ArrayList contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", customerSalary=" + customerSalary +
                ", contactNumber=" + contactNumber +
                ", nic='" + nic + '\'' +
                ", active=" + active +
                '}';
    }
}