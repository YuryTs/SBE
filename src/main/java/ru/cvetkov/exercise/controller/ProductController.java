package ru.cvetkov.exercise.controller;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.ObjectNotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cvetkov.exercise.models.ProductDto;
import ru.cvetkov.exercise.service.PriceService;
import ru.cvetkov.exercise.service.ProductService;

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
    public ResponseEntity<List<ProductDto>> getProductsPriceDate(@RequestParam(name = "date") String date) {
        return new ResponseEntity<>(productService.getProductPrices(date), HttpStatus.OK);
    }

    @SneakyThrows
    @GetMapping(value = "/{id}")
    public ProductDto getProductById(@PathVariable(name = "id") long id) throws ObjectNotFoundException {
       return productService.getById(id);
    }

    //TODO получение статистики по загруженным товарам и ценам
//    @GetMapping(value = "/statistic")
//    public ResponseEntity<>
}
