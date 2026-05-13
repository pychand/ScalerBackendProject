package com.scalers.productserviceebatch.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product  {
    private long id;
    private String name;
    private Category category;
    private double price;
    private String description;
    private String image;

}
