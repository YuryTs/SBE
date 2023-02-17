package ru.cvetkov.exercise.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cvetkov.exercise.models.*;
import ru.cvetkov.exercise.repository.PriceDao;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.*;

@Slf4j
@Service
public class PriceServiceImpl implements PriceService {

    PriceDao priceDao;
    ProductService productService;

    public PriceServiceImpl(PriceDao priceDAO) {
        this.priceDao = priceDAO;
    }
    @Autowired
    public PriceServiceImpl(PriceDao priceDao, ProductService productService) {
        this.priceDao = priceDao;
        this.productService = productService;
    }

    @Override
    public List<PriceDto> getListByDate(LocalDate date) {
        List<Price> prices = priceDao.getListByDate(date);
        if (prices.isEmpty()) {
            log.warn("Список товаров пуст!");
            return Collections.emptyList();
        } else {
            log.info("Размер списка вовзращаемого списка: " + prices.size());
            return prices.stream().map(PriceDto::new).toList();
        }
    }

    @Override
    public List<StatisticGroupByProduct> getCountPriceProduct() {
        List<Object[]> allObjects = priceDao.getCountPriceProduct();
        int sizeAllObject = allObjects.size();
        if (sizeAllObject != 0) {
            log.info("Количество товаров: " + allObjects.size());
            return
            allObjects.stream()
                    .map(objects-> {
                                String name = (String) objects[0];
                                Long frequency = (Long) objects[1];
                                return new StatisticGroupByProduct(name, frequency);
                            }
                            ).toList();
        } else {
            log.warn("Список товаров пуст!");
            return Collections.emptyList();
        }
    }

    @Override
    public List<StatisticGroupByDate> getDateStatistic() {
        List<Object[]> allObjects = priceDao.getDateStatistic();
        int sizeAllObject = allObjects.size();
        if (sizeAllObject != 0) {
            log.info("Размер вовзращаемого списка: " + allObjects.size());
            return
                    allObjects.stream()
                            .map(objects-> {
                                        Date date = (Date) objects[0];
                                        Long frequency = (Long) objects[1];
                                        return new StatisticGroupByDate(date, frequency);
                                    }
                            ).toList();
        } else {
            log.warn("Список товаров пуст!");
            return Collections.emptyList();
        }
    }

    @Override
    public GeneralProductPriceStatistic getGeneralStatistic() throws SbException {
        final ExecutorService executor = Executors.newFixedThreadPool(3);
        GeneralProductPriceStatistic answer = new GeneralProductPriceStatistic();
        try {
            Future<Long> countProducts = executor.submit(() -> productService.getCount());
            Future<List<StatisticGroupByProduct>> futureStatistic = executor.submit(this::getCountPriceProduct);
            Future<List<StatisticGroupByDate>> futureDay = executor.submit(this::getDateStatistic);

            Long count = countProducts.get(5, TimeUnit.SECONDS);
            List<StatisticGroupByProduct> statisticGroupByProducts = futureStatistic.get(5, TimeUnit.SECONDS);
            List<StatisticGroupByDate> statisticGroupByDates = futureDay.get(5, TimeUnit.SECONDS);
            answer = GeneralProductPriceStatistic.builder()
                    .count(count)
                    .statisticGroupByProductList(statisticGroupByProducts)
                    .statisticGroupByDateList(statisticGroupByDates).build();

            executor.shutdown();

        } catch (InterruptedException | TimeoutException | ExecutionException e) {
            throw new SbException("Ошибка выполнения getGeneralStatistic");
        }
        return answer;
    }
}
