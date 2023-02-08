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
    private Double price;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Column(name = "date")
    private LocalDate date;

    @Column(name = "products_id")
    private Long productId;

    public Price(){};

    public Double getPrice() {
        return this.price;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public Long getProductId() {
        return this.productId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

}
