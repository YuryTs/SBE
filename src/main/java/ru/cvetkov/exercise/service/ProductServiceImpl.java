package ru.cvetkov.exercise.service;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cvetkov.exercise.models.Price;
import ru.cvetkov.exercise.models.Product;
import ru.cvetkov.exercise.models.PriceDto;
import ru.cvetkov.exercise.repository.ProductDao;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
    private ProductDao productDAO;

    @Autowired
    public ProductServiceImpl(ProductDao productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public Long getCount() {
        return productDAO.count();
    }

    @Override
    public PriceDto getById(long id) throws ObjectNotFoundException {
        Optional<Product> product = productDAO.findById(id);
        if (product.isPresent()) {
            List<Price> pricesList = product.get().getPrices();
            pricesList.sort(Comparator.comparing(Price::getDate));
            Price price = pricesList.get(pricesList.size()-1);
            return new PriceDto(price);
        } else {
            log.error("Товара с id = " + id + " не существует");
           return null;
        }
    }
}