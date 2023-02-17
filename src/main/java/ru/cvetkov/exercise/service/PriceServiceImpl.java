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
    @Autowired
    ProductService productService;

    public PriceServiceImpl(PriceDao priceDAO) {
        this.priceDao = priceDAO;
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
            log.info(String.valueOf(allObjects.size()));
            List<StatisticGroupByProduct> statisticGroupByProductList = new ArrayList<>();
            for (Object[] objects : allObjects) {
                String name = (String) objects[0];
                Long frequency = (Long) objects[1];
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
            log.info("Размер вовзращаемого списка: " + allObjects.size());
            List<StatisticGroupByDate> statisticGroupByDates = new ArrayList<>();
            for (Object[] objects : allObjects) {
                Date date = (Date) objects[0];
                Long frequency = (Long) objects[1];
                statisticGroupByDates.add(new StatisticGroupByDate(date, frequency));
            }
            return statisticGroupByDates;
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
            throw new SbException("Ошибка выполнения getGeneralStatistic");
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
