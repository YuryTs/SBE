package ru.cvetkov.exercise.models;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Entity
@Table(name = "prices")
public class Price {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "price")
    private Double cost;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Column(name = "date")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Price(){
    }

    public Double getCost() {
        return this.cost;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCost(Double price) {
        this.cost = price;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}
