package ru.cvetkov.exercise.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.cvetkov.exercise.models.StatisticGroupByDate;
import ru.cvetkov.exercise.models.Price;
import ru.cvetkov.exercise.models.StatisticGroupByProduct;
import ru.cvetkov.exercise.repository.PriceDao;
import java.time.LocalDate;
import java.util.*;

@Slf4j
@Service
public class PriceServiceImpl extends ConvertToListServiceImpl implements PriceService {

    PriceDao priceDao;

    public PriceServiceImpl(PriceDao priceDAO) {
        this.priceDao = priceDAO;
    }

    @Override
    public List<Price> getAll() {
        return priceDao.findAll();
    }

    @Override
    public List<Price> getListByDate(LocalDate date) {
        return priceDao.getListByDate(date);
    }

    @Override
    public List<StatisticGroupByProduct> getCountPriceProduct() {
        List<Object[]> allObjects = priceDao.getCountPriceProduct();
        int sizeAllObject = allObjects.size();
        if (sizeAllObject != 0) {
            log.info(String.valueOf(allObjects.size()));
            List<StatisticGroupByProduct> statisticGroupByProductList = new ArrayList<>();
            for (Object[] objects : allObjects) {
                String name = (String) convertToList(objects).get(0);
                Long frequency = (Long) convertToList(objects).get(1);
                statisticGroupByProductList.add(new StatisticGroupByProduct(name, frequency));
            }
            return statisticGroupByProductList;
        } else {
            log.warn("Список товаров пуст!");
            return null;
        }
    }

    @Override
    public List<StatisticGroupByDate> getDateStatistic() {
        List<Object[]> allObjects = priceDao.getDateStatistic();
        int sizeAllObject = allObjects.size();
        if (sizeAllObject != 0) {
            log.info(String.valueOf(allObjects.size()));
            List<StatisticGroupByDate> statisticGroupByDates = new ArrayList<>();
            for (Object[] objects : allObjects) {
                Date date = (Date) convertToList(objects).get(0);
                Long frequency = (Long) convertToList(objects).get(1);
                statisticGroupByDates.add(new StatisticGroupByDate(date, frequency));
            }
            return statisticGroupByDates;
        } else {
            log.warn("Список товаров пуст!");
            return null;
        }
    }
}
