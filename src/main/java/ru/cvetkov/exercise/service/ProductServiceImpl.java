package ru.cvetkov.exercise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cvetkov.exercise.models.Product;
import ru.cvetkov.exercise.repository.ProductDAO;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductDAO productDAO;

    @Autowired
    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public void create(Product product) {
        productDAO.save(product);
    }

    @Override
    public List<Product> getAll() {
        return productDAO.findAll();
    }

    @Override
    public Optional<Product> getById(long id) {
        return productDAO.findById(id);
    }

    @Override
    public boolean update(Product product, long id) {
        if (productDAO.existsById(id)) {
            product.setId(id);
            productDAO.save(product);
            return true;
        }
        return false;
    }


    @Override
    public boolean delete(long id) {
        if (productDAO.existsById(id)) {
            productDAO.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Product saveOrUpdate(Product product) {
        return productDAO.save(product);
    }


}
