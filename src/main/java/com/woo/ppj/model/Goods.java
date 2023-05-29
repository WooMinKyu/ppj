package com.woo.ppj.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int price;
    private String description;
    private String img;
    private String category;
}
