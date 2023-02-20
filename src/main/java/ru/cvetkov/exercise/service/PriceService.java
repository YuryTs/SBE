package ru.cvetkov.exercise.service;

import ru.cvetkov.exercise.models.*;

import java.time.LocalDate;
import java.util.List;

public interface PriceService {

    List<PriceDto> getListByDate(LocalDate date);

    List<StatisticGroupByProduct> getCountPriceProduct();

    List<StatisticGroupByDate> getDateStatistic();

    GeneralProductPriceStatistic getGeneralStatistic() throws SbException;

    GeneralProductPriceStatistic getStatisticSingleThread();


}
