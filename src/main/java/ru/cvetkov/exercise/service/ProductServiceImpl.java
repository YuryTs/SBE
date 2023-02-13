package ru.cvetkov.exercise.service;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cvetkov.exercise.models.Price;
import ru.cvetkov.exercise.models.Product;
import ru.cvetkov.exercise.models.PriceDto;
import ru.cvetkov.exercise.models.Statistic;
import ru.cvetkov.exercise.repository.ProductDao;
import java.time.LocalDate;
import java.util.ArrayList;
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
    public void create(Product product) {
        productDAO.save(product);
    }

//    @Override
//    public List<PriceDto> getProductPrices(String reqDate) {
//        LocalDate date = LocalDate.parse(reqDate);
//        List<Product> products = productDAO.findAll();
//        List<PriceDto> productDtoList = new ArrayList<>();
//
//        if (products.isEmpty()) {
//            log.warn("Список товаров пуст!");
//            return productDtoList;
//        }
//        for (int i = 0; i < products.size(); i++) {
//            Product product = products.get(i);
//            List<Price> priceList = product.getPrices();
//            priceList.sort(Comparator.comparing(Price::getDate));
//            for (int j = 0; j < priceList.size(); j++) {
//                Price price = priceList.get(j);
//                int result = date.compareTo(price.getDate());
//                if (result < 0 && j == 0) {
//                    break;
//                }else if (result == 0) {
//                    productDtoList.add(new PriceDto(price));
//                    break;
//                } else if (result > 0 && j != priceList.size()-1) {
//                    continue;
//                }else if (result > 0 && j == priceList.size()-1) {
//                    price = priceList.get(j);
//                    productDtoList.add(new PriceDto(price));
//                    break;
//                } else {
//                    price = priceList.get(j - 1);
//                    productDtoList.add(new PriceDto(price));
//                    break;
//                }
//            }
//        }
//
//        log.info("Список товаров состоит из: " + productDtoList.size() + " шт.");
//        return productDtoList;
//    }

    @Override
    public List<Price> getPrices(Product product) {
        return product.getPrices();
    }

//    @Override
//    public List<Statistic> getChangePriceStatisticProduct() {
//        List<Product> products = productDAO.findAll();
//        List<Statistic> statisticList = new ArrayList<>();
//        System.out.println(products);
//        System.out.println(statisticList);
//        if (products.isEmpty()) {
//            log.warn("Список товаров пуст!");
//            return statisticList;
//        }else{
//            List<Price> prices = new ArrayList<>();
//            Integer frequency;
//            String productName;
//            for (Product product : products){
//                frequency = product.getPrices().size();
//                productName = product.getName();
//                Statistic statistic = new Statistic(productName, frequency);
//                statisticList.add(statistic);
//            }
//        }
//        System.out.println(statisticList);
//        return statisticList;
//    }

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
//todo может исключить из кода не использующиеся методы
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