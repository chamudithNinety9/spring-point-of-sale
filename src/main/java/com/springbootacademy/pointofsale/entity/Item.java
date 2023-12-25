package com.springbootacademy.pointofsale.entity;

import com.springbootacademy.pointofsale.entity.enums.MeasuringUnitType;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "item")
@AllArgsConstructor
@NoArgsConstructor
//@Getter
//@Setter
//@ToString
@Data
//Instead of getter, setter and toString we can use " @Data " Annotation

public class Item {

    @Id
    @Column(name = "item_id", length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int itemId;

    @Column(name = "item_name", length = 100, nullable = false)
    private String itemName;

    @Enumerated(EnumType.STRING)
    @Column(name = "measure_type   ", length = 100, nullable = false)
    private MeasuringUnitType measuringUnitType;

    @Column(name = "balance_qty", length = 100, nullable = false)
    private double balanceQty;

    @Column(name = "supplier_price", length = 100, nullable = false)
    private double supplierPrice;

    @Column(name = "selling_price", length = 100, nullable = false)
    private double sellingPrice;

    @Column(name = "active_state", columnDefinition = "TINYINT default 0")
    private boolean active;

    //One item can have in many orderDetails
    //One orderDetail is one row, so in a row there may have one item
    //One to many Mapping (Item is One, OrderDetails is many)
    @OneToMany(mappedBy = "items")
    private Set<OrderDetails> orderDetails;


}
