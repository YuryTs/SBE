package ru.cvetkov.exercise.service;

import org.hibernate.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cvetkov.exercise.models.Price;
import ru.cvetkov.exercise.models.Product;
import ru.cvetkov.exercise.models.PriceDto;
import ru.cvetkov.exercise.repository.ProductDao;
import java.util.Comparator;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

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
        Product product = productDAO.findById(id).orElse(new Product());
        List<Price> pricesList = product.getPrices();
        if (pricesList == null) {
            logger.error("Товара с id = {} не существует", id);
            return null;
        } else {
            pricesList.sort(Comparator.comparing(Price::getDate));
            Price price = pricesList.get(pricesList.size() - 1);
            return new PriceDto(price);
        }
    }
}