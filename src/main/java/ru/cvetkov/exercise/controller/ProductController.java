package ru.cvetkov.exercise.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cvetkov.exercise.models.Price;
import ru.cvetkov.exercise.models.Product;
import ru.cvetkov.exercise.service.PriceService;
import ru.cvetkov.exercise.service.ProductService;

import java.time.LocalDate;
import java.util.*;

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
    public ResponseEntity<List<Product>> getProductsPriceDate(@RequestParam(name = "date") String date) {
        return new ResponseEntity<>(productService.getProductPrices(date), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public Product getProductById(@PathVariable(name = "id") long id) throws ChangeSetPersister.NotFoundException {
       return productService.getById(id);
    }

    //TODO получение статистики по загруженным товарам и ценам
    //@GetMapping(value = "/statistic")
}
