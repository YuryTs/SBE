package ru.cvetkov.exercise.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import ru.cvetkov.exercise.models.AgrigatedStatistic;
import ru.cvetkov.exercise.models.Price;
import ru.cvetkov.exercise.models.Statistic;
import ru.cvetkov.exercise.repository.PriceDao;
import ru.cvetkov.exercise.repository.ProductDao;

import java.time.LocalDate;
import java.util.List;


@Service
public class PriceServiceImpl implements PriceService{

    PriceDao priceDao;
    ProductDao productDao;

    public PriceServiceImpl(PriceDao priceDAO) {
        this.priceDao = priceDAO;
    }

    @Override
    public List<Price> getAll() {
        return priceDao.findAll();
    }

    @Override
    public List<Price> getListByDate(LocalDate date){
        return priceDao.getListByDate(date);
    }

    @Override
    public List<Statistic> getCountPriceProduct() {
        List<Statistic> statisticList = priceDao.getCountPriceProduct();
        return statisticList;
    }

    public String getStatistic() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

         return  objectMapper.writeValueAsString(AgrigatedStatistic.builder()
                .dayStatisticList(priceDao.getDateStatistic())
                .statisticList(priceDao.getCountPriceProduct())
                .count(productDao.count()).build());


    }
}
