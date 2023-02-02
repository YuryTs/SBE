package ru.cvetkov.exercise.service;

import ru.cvetkov.exercise.models.Products;

import java.util.List;

public interface ProductService {

    void create (Products product);

    List<Products> getAll();

    Products get(long id);

    boolean update(Products product, long id);

    boolean delete(long id);

}
