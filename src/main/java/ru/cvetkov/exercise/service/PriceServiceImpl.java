package ru.cvetkov.exercise.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cvetkov.exercise.models.GeneralProductPriceStatistic;
import ru.cvetkov.exercise.models.StatisticGroupByDate;
import ru.cvetkov.exercise.models.Price;
import ru.cvetkov.exercise.models.StatisticGroupByProduct;
import ru.cvetkov.exercise.repository.PriceDao;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.*;

@Slf4j
@Service
public class PriceServiceImpl extends ConvertToListServiceImpl implements PriceService {

    PriceDao priceDao;
    @Autowired
    ProductService productService;

    public PriceServiceImpl(PriceDao priceDAO) {
        this.priceDao = priceDAO;
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
            return Collections.emptyList();
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
            return Collections.emptyList();
        }
    }

    @Override
    public GeneralProductPriceStatistic getGeneralStatistic() {
        final ExecutorService executor = Executors.newFixedThreadPool(3);
        GeneralProductPriceStatistic answer = new GeneralProductPriceStatistic();
        try {
            Future<Long> countProducts = getFutureCount(1, executor);
            Future<List<StatisticGroupByProduct>> futureStatistic = getFutureCountPriceProduct(2, executor);
            Future<List<StatisticGroupByDate>> futureDay = getFutureDateStatistic(3, executor);

            Long count = countProducts.get(5, TimeUnit.SECONDS);
            List<StatisticGroupByProduct> statisticGroupByProducts = futureStatistic.get(5, TimeUnit.SECONDS);
            List<StatisticGroupByDate> statisticGroupByDates = futureDay.get(5, TimeUnit.SECONDS);
            answer = GeneralProductPriceStatistic.builder()
                    .count(count)
                    .statisticGroupByProductList(statisticGroupByProducts)
                    .statisticGroupByDateList(statisticGroupByDates).build();

            executor.shutdown();

        } catch (InterruptedException | TimeoutException | ExecutionException e) {
            log.warn("Interrupted!", e);
            Thread.currentThread().interrupt();
        }

        return answer;
    }

    private Future<Long> getFutureCount(int threadNum, ExecutorService executorService) {
        return executorService.submit(() -> productService.getCount());
    }

    private Future<List<StatisticGroupByProduct>> getFutureCountPriceProduct(int threadNum, ExecutorService executorService) {
        return executorService.submit(this::getCountPriceProduct);
    }

    private Future<List<StatisticGroupByDate>> getFutureDateStatistic(int threadNum, ExecutorService executorService) {
        return executorService.submit(this::getDateStatistic);
    }
}
