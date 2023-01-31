package ru.cvetkov.exercise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cvetkov.exercise.models.Product;
import ru.cvetkov.exercise.repository.ProductRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void create(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<Product> readAll() {
        return productRepository.findAll();
    }

    @Override
    public Product read(int id) {
        return productRepository.getOne();
    }

    @Override
    public boolean update(Product product, int id) {
        if (productRepository.existsById(id)){
            product.setId(id);
            productRepository.save(product);
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        if (productRepository.existsById(id))
            productRepository.deleteById(id);
            return true;
    }
}
