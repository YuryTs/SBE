package ru.cvetkov.exercise.controller;

import jakarta.websocket.server.ServerEndpoint;
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
import java.util.Optional;

@RestController
@RequestMapping("/products")
@Slf4j
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    PriceService priceService;

    @GetMapping()
    public ResponseEntity<List<Product>> getAll() {
        final List<Product> products = productService.getAll();
        if (products.isEmpty()) {
            log.warn("The Lists of products is empty");
        }
        log.info("Список товаров состоит из: " + products.size() + " шт.");
        return !CollectionUtils.isEmpty(products)
                ? new ResponseEntity<>(products, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    @GetMapping()
//    public ResponseEntity<List<Price>> getPrices() {
//        final List<Price> price = priceService.getAll();
//        return !CollectionUtils.isEmpty(price)
//                ? new ResponseEntity<>(price, HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }


//    @GetMapping(value = "/id={id}")
//    public Optional<Product> getProductById(@PathVariable long id) {
//        Product product = productService.getById(id);
//        return Optional.ofNullable(product);
//                != null
//                ? new ResponseEntity<Product>(product, HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

//    }


//    @DeleteMapping(value = "/products/{id}")
//    public ResponseEntity<?> delete(@PathVariable(name = "id") long id) {
//        final boolean deleted = productService.delete(id);
//
//        return deleted
//                ? new ResponseEntity<>(HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
//    }
}
