package ru.cvetkov.exercise.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    ProductService productService;

    @Autowired
    PriceService priceService;

    @GetMapping()
    public ResponseEntity<List<Product>> getAll() {
        final List<Product> products = productService.getAll();
//        Product newProduct = new Product(100L, "Mizumi");
//        Product newProduct2 = new Product("NEC");
//        log.info("наименование " + newProduct.getName() + " id " + newProduct.getId());
//        log.info("наименование " + newProduct2.getName() + " id " + newProduct2.getId());
//        products.add(productService.saveOrUpdate(newProduct));
//        products.add(productService.saveOrUpdate(newProduct2));
//        List<Product> products1 = productService.getAll();
        if (products.isEmpty()) {
            log.warn("The Lists of products is empty");
        }
        log.info("Список товаров состоит из: " + products.size() + " шт.");
        return !CollectionUtils.isEmpty(products)
                ? new ResponseEntity<>(products, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping(value = "/{id}")
    public Optional<Product> getProductById(@PathVariable (name = "id") long id) {
        return productService.getById(id);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") long id) {
        final boolean deleted = productService.delete(id);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
