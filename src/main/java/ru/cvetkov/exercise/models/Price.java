//package ru.cvetkov.exercise.models;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;
//
//import java.util.Date;
//
////@Getter
////@Setter
////@ToString
//public class PriceOfProduct {
//
//    private Products product;
//
//    @Id
//    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "price")
//    private Float price;
//
//    @Column(name = "date")
//    private Date date;
//
//    @Column(name = "product_id")
//    private Long productId;
//
//
//
//    public Products getProduct() {
//        return product;
//    }
//
//    public void setProduct(Products product) {
//        this.product = product;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Float getPrice() {
//        return price;
//    }
//
//    public void setPrice(Float price) {
//        this.price = price;
//    }
//
//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }
//
//    public Long getProductId() {
//        return productId;
//    }
//
//    public void setProductId(Long productId) {
//        this.productId = productId;
//    }
//}
