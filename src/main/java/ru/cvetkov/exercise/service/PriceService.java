package ru.cvetkov.exercise.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.cvetkov.exercise.models.Price;
import ru.cvetkov.exercise.models.Statistic;

import java.time.LocalDate;
import java.util.List;

public interface PriceService {
    List<Price> getAll();

    List<Price> getListByDate(LocalDate date);

    List<Statistic> getCountPriceProduct();

    String getStatistic() throws JsonProcessingException;

}
