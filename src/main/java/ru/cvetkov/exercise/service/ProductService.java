package ru.cvetkov.exercise.service;

import org.springframework.data.crossstore.ChangeSetPersister;
import ru.cvetkov.exercise.models.Price;
import ru.cvetkov.exercise.models.Product;
import ru.cvetkov.exercise.models.PriceDto;
import ru.cvetkov.exercise.models.Statistic;

import java.util.List;

public interface ProductService {

    void create (Product product);

    PriceDto getById(long id) throws ChangeSetPersister.NotFoundException;

    boolean update(Product product, long id);

    boolean delete(long id);

    Product saveOrUpdate(Product product);

   List<Price> getPrices(Product product);

}
