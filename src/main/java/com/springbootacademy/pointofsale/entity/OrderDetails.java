package com.springbootacademy.pointofsale.entity;

import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;

@Entity
@Table(name = "order_details")

//to use json in a class
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonType.class)
})

@AllArgsConstructor
@NoArgsConstructor
@Data

//Order details --> Name, Unit-price, Qty, Balance-qty, amount...
public class OrderDetails {
    @Id
    @Column(name = "order_details_id", length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderDetailsId;

    @Column(name = "item_name", length = 100, nullable = false)
    private String itemName;

    @Column(name = "qty", length = 100, nullable = false)
    private double qty;

    @Column(name = "amount", nullable = false)
    private Double amount;

    //one orderDetail can have one item
    @ManyToOne
    @JoinColumn(name = "item_id",nullable = false)
    private Item items;

    //one orderDetail can have one order
    @ManyToOne
    @JoinColumn(name = "order_id",nullable = false)
    private Order orders;

}
