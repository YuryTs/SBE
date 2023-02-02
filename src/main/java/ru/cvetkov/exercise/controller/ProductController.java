package ru.cvetkov.exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cvetkov.exercise.models.Products;
import ru.cvetkov.exercise.service.ProductService;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(value = "/products")
    public ResponseEntity<?> create(@RequestBody Products product){
        productService.create(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/products")
    public ResponseEntity<List<Products>> getAll(){
        final List<Products> products = productService.getAll();

        return products != null && !products.isEmpty()
                ? new ResponseEntity<>(products, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/products/{id}")
    public ResponseEntity<Products> get(@PathVariable(name = "id") long id){
        final Products product = productService.get(id);

        return product != null
                ? new ResponseEntity<>(product, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PutMapping(value = "/products/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") long id, @RequestBody Products product){
        final boolean updated = productService.update(product, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/products/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") long id){
        final boolean deleted = productService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
