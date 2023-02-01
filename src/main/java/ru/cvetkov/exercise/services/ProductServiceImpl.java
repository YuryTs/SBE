package ru.cvetkov.exercise.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cvetkov.exercise.models.Product;
import ru.cvetkov.exercise.repository.ProductRepository;

import java.util.List;

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
    public Product read(long id) {
        return productRepository.getOne(id);
    }

    @Override
    public boolean update(Product product, long id) {
        if (productRepository.existsById(id)){
            product.setId((long) id);
            productRepository.save(product);
        }
        return false;
    }

    @Override
    public boolean delete(long id) {
        if (productRepository.existsById(id))
            productRepository.deleteById(id);
            return true;
    }
}
