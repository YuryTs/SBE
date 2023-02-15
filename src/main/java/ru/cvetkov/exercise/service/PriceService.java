package ru.cvetkov.exercise.service;

import ru.cvetkov.exercise.models.GeneralProductAndPriceStatistic;
import ru.cvetkov.exercise.models.StatisticGroupByDate;
import ru.cvetkov.exercise.models.Price;
import ru.cvetkov.exercise.models.StatisticGroupByProduct;

import java.time.LocalDate;
import java.util.List;

public interface PriceService {
    List<Price> getAll();

    List<Price> getListByDate(LocalDate date);

    List<StatisticGroupByProduct> getCountPriceProduct();

    List<StatisticGroupByDate> getDateStatistic();

    GeneralProductAndPriceStatistic getGeneralStatistic();

}
