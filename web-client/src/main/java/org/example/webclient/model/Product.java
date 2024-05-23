package org.example.webclient.model;

import lombok.Data;

@Data
public class Product {

    private Long id;
    private String name;
    private String description;
    private double price;
    private int inStock;

}
