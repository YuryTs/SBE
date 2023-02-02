package ru.cvetkov.exercise.models;

import jakarta.persistence.*;

import java.util.Date;

//@Getter
//@Setter
//@ToString
@Entity
@Table(name = "Prices")
public class Price {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "price")
    private Double price;

    @Column(name = "date")
    private Date date;

    @Column(name = "product_id")
    private Long productId;


    public Double getPrice() {
        return price;
    }

    public Date getDate() {
        return date;
    }

    public Long getProductId() {
        return productId;
    }

}
