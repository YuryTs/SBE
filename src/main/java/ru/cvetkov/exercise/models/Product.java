package ru.cvetkov.exercise.models;


import jakarta.persistence.*;

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

//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product")
//    private List<Price> price;
//
//    public List<Price> getPrice() {
//        return price;
//    }
//
//    public Product(List<Price> price) {
//        this.price = price;
//    }
}
