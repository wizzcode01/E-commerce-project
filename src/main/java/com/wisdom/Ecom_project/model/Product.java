package com.wisdom.Ecom_project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    private int proId;
    private String name;
    private String desc;
    private String brandName;
    private int price;
    private String category;
    private int quantity;
    private boolean available;
    private Date releasedDate;


}