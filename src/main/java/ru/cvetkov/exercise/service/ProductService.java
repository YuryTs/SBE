package ru.cvetkov.exercise.service;

import ru.cvetkov.exercise.models.Product;

import java.util.List;

public interface ProductService {

    void create (Product product);

    List<Product> readAll();

    Product read(int id);

    boolean update(Product product, int id);

    boolean delete(int id);
}
