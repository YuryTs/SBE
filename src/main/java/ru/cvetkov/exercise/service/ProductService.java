package ru.cvetkov.exercise.service;

import ru.cvetkov.exercise.models.Product;

import java.util.List;

public interface ProductService {

    void create (Product product);

    List<Product> getAll();

    Product get(long id);

    boolean update(Product product, long id);

    boolean delete(long id);

}
