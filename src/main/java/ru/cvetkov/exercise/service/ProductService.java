package ru.cvetkov.exercise.service;

import ru.cvetkov.exercise.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    void create (Product product);

    List<Product> getAll();

    Optional<Product> getById(long id);

    boolean update(Product product, long id);

    boolean delete(long id);

    Product saveOrUpdate(Product product);


}
