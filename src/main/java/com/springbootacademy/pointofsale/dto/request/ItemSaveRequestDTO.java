package com.springbootacademy.pointofsale.dto.request;

import com.springbootacademy.pointofsale.entity.enums.MeasuringUnitType;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
//@Getter
//@Setter
//@ToString
@Data
//use @Data instead of getter and setter and toStringannotations

public class ItemSaveRequestDTO {

    //No item id here bcz it auto generated from item entity
    private String itemName;
    private MeasuringUnitType measuringUnitType;
    private double balanceQty;
    private double supplierPrice;
    private double sellingPrice;

}
