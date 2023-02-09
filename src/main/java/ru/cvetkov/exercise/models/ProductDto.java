package ru.cvetkov.exercise.models;

import lombok.Data;

@Data
public class ProductDto {
    String name;
    Double price;

    public ProductDto(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public ProductDto(){}

    public ProductDto(Price price){
        this.name = price.getProduct().getName();
        this.price = price.getPrice();
    }
}

