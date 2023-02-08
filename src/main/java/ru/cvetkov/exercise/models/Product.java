package ru.cvetkov.exercise.models;


import jakarta.persistence.*;
import org.hibernate.mapping.Collection;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    public Product(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "products_id")
    private List<Price> prices;

    public Product() {
    }

    public Product(String name) {
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Double getPrice() {
        if (this.prices.size() != 0) {
            List<Price> actualPrices = this.prices;
            actualPrices.sort(Comparator.comparing(Price::getDate));
            Price priceFromList = actualPrices.get(actualPrices.size() - 1);
            return priceFromList.getPrice();
        } else {
            return null;
        }
    }

    public List<Price> getPriceList() {
        if (this.prices.size() != 0) {
            List<Price> actualPrices = this.prices;
            System.out.println(actualPrices);
            return actualPrices;
        } else {
            return null;
        }
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }
}


