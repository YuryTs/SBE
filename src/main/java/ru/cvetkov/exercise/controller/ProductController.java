package ru.cvetkov.exercise.controller;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping(value = "/products")
    public ResponseEntity<?> create(@RequestBody Product product) {
        productService.create(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/products")
    public ResponseEntity<List<Product>> getAll() {
        final List<Product> products = productService.getAll();
        log.info("Создание списка товаров");
        return products != null && !products.isEmpty()
                ? new ResponseEntity<>(products, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/prices")
    public ResponseEntity<List<Price>> getPrices() {
        final List<Price> price = priceService.getAll();
        log.info("Создание списка цен");
        return price != null && !price.isEmpty()
                ? new ResponseEntity<>(price, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/products/id={id}")
    public ResponseEntity getProductById(@PathVariable long id) {
        Product product = productService.getById(id);
        return product != null
                ? new ResponseEntity<>(product, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PutMapping(value = "/products/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") long id, @RequestBody Product product) {
        final boolean updated = productService.update(product, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/products/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") long id) {
        final boolean deleted = productService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
