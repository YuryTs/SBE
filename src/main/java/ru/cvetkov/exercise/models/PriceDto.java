package ru.cvetkov.exercise.models;

import lombok.Data;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PriceDto priceDto)) return false;
        return Objects.equals(name, priceDto.name) && Objects.equals(price, priceDto.price);
    }

}

