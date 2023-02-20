package ru.cvetkov.exercise.models;

import lombok.Data;

@Data
public class PriceDto {
    String name;
    Double price;

    public PriceDto(){}

    public PriceDto(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public PriceDto(Price price){
        this.name = price.getProduct().getName();
        this.price = price.getCost();
    }
}

