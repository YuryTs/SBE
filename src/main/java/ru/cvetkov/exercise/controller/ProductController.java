package ru.cvetkov.exercise.controller;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import ru.cvetkov.exercise.models.Price;
import ru.cvetkov.exercise.models.Product;
import ru.cvetkov.exercise.service.PriceService;
import ru.cvetkov.exercise.service.ProductService;

import java.util.List;

@RestController
@Slf4j
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    PriceService priceService;

    @GetMapping(value = "/products")
    public ResponseEntity<List<Product>> getAll() {
        final List<Product> products = productService.getAll();
        log.warn("пример создания лога");
        log.info("пример создания лога");
        return !CollectionUtils.isEmpty(products)
                ? new ResponseEntity<>(products, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/prices")
    public ResponseEntity<List<Price>> getPrices() {
        final List<Price> price = priceService.getAll();
        return !CollectionUtils.isEmpty(price)
                ? new ResponseEntity<>(price, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping(value = "/products/id={id}")
    public ResponseEntity<Product> getProductById(@PathVariable long id) {
        Product product = productService.getById(id);
        return product != null
                ? new ResponseEntity<>(product, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


//    @DeleteMapping(value = "/products/{id}")
//    public ResponseEntity<?> delete(@PathVariable(name = "id") long id) {
//        final boolean deleted = productService.delete(id);
//
//        return deleted
//                ? new ResponseEntity<>(HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
//    }
}
