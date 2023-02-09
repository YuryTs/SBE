package ru.cvetkov.exercise.service;

import org.springframework.data.crossstore.ChangeSetPersister;
import ru.cvetkov.exercise.models.Price;
import ru.cvetkov.exercise.models.Product;
import ru.cvetkov.exercise.models.ProductDto;

import java.util.List;

public interface ProductService {

    void create (Product product);

    ProductDto getById(long id) throws ChangeSetPersister.NotFoundException;

    boolean update(Product product, long id);

    boolean delete(long id);

    Product saveOrUpdate(Product product);

   List<ProductDto> getProductPrices(String reqDate);

   List<Price> getPrices(Product product);

}
