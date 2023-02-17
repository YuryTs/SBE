package ru.cvetkov.exercise.controller;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.ObjectNotFoundException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.cvetkov.exercise.models.*;
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


    @GetMapping(value = "/{id}")
    public PriceDto getProductById(@PathVariable(name = "id") long id) throws ObjectNotFoundException {
        return productService.getById(id);
    }

    @GetMapping(value = "/statistic")
    public GeneralProductPriceStatistic getStatistic() throws SbException {
        return priceService.getGeneralStatistic();
    }

    @GetMapping
    public List<PriceDto> getAllByDate(
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return priceService.getListByDate(date);
    }
}
