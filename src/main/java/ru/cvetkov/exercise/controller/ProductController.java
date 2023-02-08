package ru.cvetkov.exercise.controller;

import lombok.extern.slf4j.Slf4j;
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

//    @GetMapping()
//    public ResponseEntity<List<Product>> getAllProduct() {
//        final List<Product> products = productService.getAll();
//        if (products.isEmpty()) {
//            log.warn("Список товаров пуст!");
//        } else {
//            log.info("Список товаров состоит из: " + products.size() + " шт.");
//        }
//        return new ResponseEntity<>(products, HttpStatus.OK);
//    }

    @GetMapping()
    public ResponseEntity<List<Product>> getProductsPriceOnDate(@RequestParam(name = "date") String date) {
        LocalDate requestDate = LocalDate.parse(date);
        final List<Product> products = productService.getAll();
        List<Product> newProductList = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            Product eachProduct = products.get(i);
            List<Price> prices = eachProduct.getPriceList();
            prices.sort(Comparator.comparing(Price:: getDate));

            List<Price> newPrice = new ArrayList<>();
            for (int j = 0; j < prices.size(); j++) {
                Price price = prices.get(j);
                int result = requestDate.compareTo(price.getDate());
                if (result == 0){
                    newPrice.add(price);
                    break;
                } else if (result > 0) {
                    continue;
                } else if (j == 0) {
                    price = prices.get(j);
                    newPrice.add(price);
                    break;
                }else {
                    price = prices.get(j-1);
                    newPrice.add(price);
                    break;
                }
            }
            eachProduct.setPrices(newPrice);
            newProductList.add(eachProduct);
        }
        if (products.isEmpty()) {
            log.warn("Список товаров пуст!");
        } else {
            log.info("Список товаров состоит из: " + newProductList.size() + " шт.");
        }
        return new ResponseEntity<>(newProductList, HttpStatus.OK);
    }

//    public Double getPrice() {
//        if (this.prices.size() != 0) {
//            List<Price> actualPrices = this.prices;
//            actualPrices.sort(Comparator.comparing(Price::getDate));
//            Price priceFromList = actualPrices.get(actualPrices.size() - 1);
//            return priceFromList.getPrice();
//        }else{
//            return null;
//        }
//    }

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
