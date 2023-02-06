package ru.cvetkov.exercise.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import ru.cvetkov.exercise.models.Product;
import ru.cvetkov.exercise.service.PriceService;
import ru.cvetkov.exercise.service.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@Slf4j
public class ProductController {

    ProductService productService;
    PriceService priceService;

    public ProductController(ProductService productService, PriceService priceService) {
        this.productService = productService;
        this.priceService = priceService;
    }

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProduct() {
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
//    public ResponseEntity<List<Product>> getProductsDate(@RequestParam ("date") DateTimeFormat date) {
//        List<Product> products = productService.getAll();
//
//        if (products.isEmpty()) {
//            log.warn("The Lists of products is empty");
//        }
//        log.info("Список товаров состоит из: " + products.size() + " шт.");
//        return new ResponseEntity<>(products, HttpStatus.OK);
//    }


    //TODO: в случае отсутствия товара с id вывести лог error + исключение
    @GetMapping(value = "/{id}")
    public Optional<Product> getProductById(@PathVariable(name = "id") long id) {
        return productService.getById(id);
    }

    //TODO получение статистики по загруженным товарам и ценам
//    @GetMapping(value = "/statistic")
//    public ResponseEntity
}
