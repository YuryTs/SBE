package ru.cvetkov.exercise.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cvetkov.exercise.models.Product;
import ru.cvetkov.exercise.repository.ProductDAO;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductDAO productDAO;

    @Override
    public void create(Product product) {
        productDAO.save(product);
    }

    @Override
    public List<Product> readAll() {
        return productDAO.findAll();
    }

    @Override
    public Product read(long id) {
        return productDAO.getOne(id);
    }

    @Override
    public boolean update(Product product, long id) {
        if (productDAO.existsById(id)){
            product.setId((long) id);
            productDAO.save(product);
        }
        return false;
    }

    @Override
    public boolean delete(long id) {
        if (productDAO.existsById(id))
            productDAO.deleteById(id);
            return true;
    }
}
