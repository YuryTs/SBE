package ru.cvetkov.exercise.models;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

//@Getter
//@Setter
//@ToString
@Entity
@Table(name = "prices")
public class Price {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "price")
    private Double price;

    @DateTimeFormat(fallbackPatterns = "yyyy-mm-dd")
    @Column(name = "date")
    private Date date;

    @Column(name = "product_id")
    private Long productId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "products_id")
//    private Product product;
//
//    public Price(){};

    public Double getPrice() {
        return this.price;
    }

    public Date getDate() {
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

    public void setDate(Date date) {
        this.date = date;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

//    public Product getProduct() {
//        return product;
//    }
//
//    public void setProduct(Product product) {
//        this.product = product;
//    }
}
