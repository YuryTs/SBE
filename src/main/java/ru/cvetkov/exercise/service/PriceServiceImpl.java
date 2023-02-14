package ru.cvetkov.exercise.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import ru.cvetkov.exercise.models.AgrigatedStatistic;
import ru.cvetkov.exercise.models.DayStatistic;
import ru.cvetkov.exercise.models.Price;
import ru.cvetkov.exercise.models.Statistic;
import ru.cvetkov.exercise.repository.PriceDao;
import ru.cvetkov.exercise.repository.ProductDao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class PriceServiceImpl extends ParseServiceImpl implements PriceService{

    PriceDao priceDao;
//    ProductDao productDao;

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
        List<Object[]> allObjects = priceDao.getCountPriceProduct();
        List<Statistic> statisticList = new ArrayList<>();
        for (Object[] objects: allObjects){
            String name = (String) convertToList(objects).get(0);
          Long frequency = (Long) convertToList(objects).get(1);
            statisticList.add(new Statistic(name,frequency));
        }
        return statisticList;
    }

    @Override
    public List<DayStatistic> getDateStatistic(){
        List<Object[]> allObjects = priceDao.getDateStatistic();
        List<DayStatistic> dayStatistics = new ArrayList<>();
        for(Object[] objects : allObjects){
            Date date = (Date) convertToList(objects).get(0);
            Long frequency = (Long) convertToList(objects).get(1);
            dayStatistics.add(new DayStatistic(date, frequency));
        }
        return dayStatistics;
    }

//    public Long getCountNameProduct(){
//        return productDao.count();
//    }

//    public String getStatistic() throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        PriceServiceImpl priceService = new PriceServiceImpl(priceDao);
//        AgrigatedStatistic.builder().count(priceDao.count())
//                .statisticList().dayStatisticList();
//
//
//         return  objectMapper.writeValue(AgrigatedStatistic.builder()
//                .dayStatisticList(priceDao.getDateStatistic())
//                .statisticList(priceDao.getCountPriceProduct())
//                .count(productDao.count()).build());
//    }
}
