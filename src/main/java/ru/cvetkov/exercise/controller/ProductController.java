package ru.cvetkov.exercise.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import ru.cvetkov.exercise.models.Product;
import ru.cvetkov.exercise.service.PriceService;
import ru.cvetkov.exercise.service.ProductService;
import ru.cvetkov.exercise.service.ProductServiceImpl;

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
            log.warn("Список товаров пуст!");
        } else {
            log.info("Список товаров состоит из: " + products.size() + " шт.");
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public Optional<Product> getProductById(@PathVariable(name = "id") long id) {
        if (productService.getById(id).isPresent()) {
            return productService.getById(id);
        } else {
            log.error("Товара с id= " + id + " не существует");
            throw
                    new ResourceNotFoundException("Товара с id= " + id + " не существует");
        }
    }

    //TODO получение статистики по загруженным товарам и ценам
    //@GetMapping(value = "/statistic")
}
