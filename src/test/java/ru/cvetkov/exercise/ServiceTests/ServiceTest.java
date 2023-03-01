package ru.cvetkov.exercise.ServiceTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.cvetkov.exercise.models.*;
import ru.cvetkov.exercise.service.PriceServiceImpl;
import ru.cvetkov.exercise.service.ProductServiceImpl;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class ServiceTest {

    private PriceServiceImpl priceServiceImpl;
    private ProductServiceImpl productServiceImpl;

    @Autowired
    public ServiceTest(PriceServiceImpl priceServiceImpl, ProductServiceImpl productServiceImpl){
        this.priceServiceImpl = priceServiceImpl;
        this.productServiceImpl = productServiceImpl;
    }

    @Test
    void getListByDateTest(){
        List<PriceDto> priceDtoList = priceServiceImpl.getListByDate(LocalDate.now());

        Assertions.assertEquals("nokia3310", priceDtoList.get(0).getName());
        Assertions.assertEquals(2900d, priceDtoList.get(0).getPrice());
        Assertions.assertEquals("iPhone", priceDtoList.get(1).getName());
        Assertions.assertEquals(37200d, priceDtoList.get(1).getPrice());
        Assertions.assertEquals("Mi", priceDtoList.get(2).getName());
        Assertions.assertEquals(12100d, priceDtoList.get(2).getPrice());
        Assertions.assertEquals(3, priceDtoList.size());
    }
    @Test
    void getCountPriceProductTest() {
        List<StatisticGroupByProduct> statisticGroupByProductList = priceServiceImpl.getCountPriceProduct();
//        List<Object[]> priceObjects = priceDao.getCountPriceProduct();
//        List<StatisticGroupByProduct> statisticGroupByProductList = priceObjects.stream()
//                .map(objects -> {
//                            String name = (String) objects[0];
//                            Long frequency = (Long) objects[1];
//                            return new StatisticGroupByProduct(name, frequency);
//                        }
//                ).toList();

        Assertions.assertEquals("nokia3310", statisticGroupByProductList.get(0).getName());
        Assertions.assertEquals(3L, statisticGroupByProductList.get(0).getFrequency());
        Assertions.assertEquals("iPhone", statisticGroupByProductList.get(1).getName());
        Assertions.assertEquals(3L, statisticGroupByProductList.get(1).getFrequency());
        Assertions.assertEquals("Mi", statisticGroupByProductList.get(2).getName());
        Assertions.assertEquals(2L, statisticGroupByProductList.get(2).getFrequency());
    }

    @Test
    void getDateStatisticTest() {
        List<StatisticGroupByDate> statisticGroupByDates = priceServiceImpl.getDateStatistic();
        Assertions.assertEquals("2023-01-09", (statisticGroupByDates.get(0).getDate()).toString());
        Assertions.assertEquals("2021-09-19", (statisticGroupByDates.get(1).getDate()).toString());
        Assertions.assertEquals("2023-01-18", (statisticGroupByDates.get(2).getDate()).toString());
        Assertions.assertEquals("2023-02-01", (statisticGroupByDates.get(3).getDate()).toString());
        Assertions.assertEquals("2022-09-04", (statisticGroupByDates.get(4).getDate()).toString());
        Assertions.assertEquals("2020-02-01", (statisticGroupByDates.get(5).getDate()).toString());
        Assertions.assertEquals("2022-12-01", (statisticGroupByDates.get(6).getDate()).toString());
        Assertions.assertEquals("2022-10-23", (statisticGroupByDates.get(7).getDate()).toString());
        Assertions.assertEquals(1,statisticGroupByDates.get(0).getFrequency());
        Assertions.assertEquals(1,statisticGroupByDates.get(1).getFrequency());
        Assertions.assertEquals(1,statisticGroupByDates.get(2).getFrequency());
        Assertions.assertEquals(1,statisticGroupByDates.get(3).getFrequency());
        Assertions.assertEquals(1,statisticGroupByDates.get(4).getFrequency());
        Assertions.assertEquals(1,statisticGroupByDates.get(5).getFrequency());
        Assertions.assertEquals(1,statisticGroupByDates.get(6).getFrequency());
        Assertions.assertEquals(1,statisticGroupByDates.get(7).getFrequency());
    }
    @Test
    void getGeneralStatisticTest() throws SbException {
        GeneralProductPriceStatistic generalProductPriceStatistic = priceServiceImpl.getGeneralStatistic();
        Assertions.assertEquals(3, generalProductPriceStatistic.getCount());
        Assertions.assertEquals("nokia3310", generalProductPriceStatistic.getStatisticGroupByProductList().get(0).getName());
        Assertions.assertEquals("iPhone", generalProductPriceStatistic.getStatisticGroupByProductList().get(1).getName());
        Assertions.assertEquals("Mi", generalProductPriceStatistic.getStatisticGroupByProductList().get(2).getName());
        Assertions.assertEquals(3L,generalProductPriceStatistic.getStatisticGroupByProductList().get(0).getFrequency());
        Assertions.assertEquals(3L,generalProductPriceStatistic.getStatisticGroupByProductList().get(1).getFrequency());
        Assertions.assertEquals(2L,generalProductPriceStatistic.getStatisticGroupByProductList().get(2).getFrequency());
        Assertions.assertEquals("2023-01-09",(generalProductPriceStatistic.getStatisticGroupByDateList().get(0).getDate()).toString());
        Assertions.assertEquals("2021-09-19",(generalProductPriceStatistic.getStatisticGroupByDateList().get(1).getDate()).toString());
        Assertions.assertEquals("2023-01-18",(generalProductPriceStatistic.getStatisticGroupByDateList().get(2).getDate()).toString());
        Assertions.assertEquals("2023-02-01",(generalProductPriceStatistic.getStatisticGroupByDateList().get(3).getDate()).toString());
        Assertions.assertEquals("2022-09-04",(generalProductPriceStatistic.getStatisticGroupByDateList().get(4).getDate()).toString());
        Assertions.assertEquals("2020-02-01",(generalProductPriceStatistic.getStatisticGroupByDateList().get(5).getDate()).toString());
        Assertions.assertEquals("2022-12-01",(generalProductPriceStatistic.getStatisticGroupByDateList().get(6).getDate()).toString());
        Assertions.assertEquals("2022-10-23",(generalProductPriceStatistic.getStatisticGroupByDateList().get(7).getDate()).toString());
        Assertions.assertEquals(1,generalProductPriceStatistic.getStatisticGroupByDateList().get(0).getFrequency());
        Assertions.assertEquals(1,generalProductPriceStatistic.getStatisticGroupByDateList().get(1).getFrequency());
        Assertions.assertEquals(1,generalProductPriceStatistic.getStatisticGroupByDateList().get(2).getFrequency());
        Assertions.assertEquals(1,generalProductPriceStatistic.getStatisticGroupByDateList().get(3).getFrequency());
        Assertions.assertEquals(1,generalProductPriceStatistic.getStatisticGroupByDateList().get(4).getFrequency());
        Assertions.assertEquals(1,generalProductPriceStatistic.getStatisticGroupByDateList().get(5).getFrequency());
        Assertions.assertEquals(1,generalProductPriceStatistic.getStatisticGroupByDateList().get(6).getFrequency());
        Assertions.assertEquals(1,generalProductPriceStatistic.getStatisticGroupByDateList().get(7).getFrequency());

    }

    @Test
    void getStatisticSingleTreadTest(){
        GeneralProductPriceStatistic generalProductPriceStatistic = priceServiceImpl.getStatisticSingleThread();
        Assertions.assertEquals(3, generalProductPriceStatistic.getCount());
        Assertions.assertEquals("nokia3310", generalProductPriceStatistic.getStatisticGroupByProductList().get(0).getName());
        Assertions.assertEquals("iPhone", generalProductPriceStatistic.getStatisticGroupByProductList().get(1).getName());
        Assertions.assertEquals("Mi", generalProductPriceStatistic.getStatisticGroupByProductList().get(2).getName());
        Assertions.assertEquals(3L,generalProductPriceStatistic.getStatisticGroupByProductList().get(0).getFrequency());
        Assertions.assertEquals(3L,generalProductPriceStatistic.getStatisticGroupByProductList().get(1).getFrequency());
        Assertions.assertEquals(2L,generalProductPriceStatistic.getStatisticGroupByProductList().get(2).getFrequency());
        Assertions.assertEquals("2023-01-09",(generalProductPriceStatistic.getStatisticGroupByDateList().get(0).getDate()).toString());
        Assertions.assertEquals("2021-09-19",(generalProductPriceStatistic.getStatisticGroupByDateList().get(1).getDate()).toString());
        Assertions.assertEquals("2023-01-18",(generalProductPriceStatistic.getStatisticGroupByDateList().get(2).getDate()).toString());
        Assertions.assertEquals("2023-02-01",(generalProductPriceStatistic.getStatisticGroupByDateList().get(3).getDate()).toString());
        Assertions.assertEquals("2022-09-04",(generalProductPriceStatistic.getStatisticGroupByDateList().get(4).getDate()).toString());
        Assertions.assertEquals("2020-02-01",(generalProductPriceStatistic.getStatisticGroupByDateList().get(5).getDate()).toString());
        Assertions.assertEquals("2022-12-01",(generalProductPriceStatistic.getStatisticGroupByDateList().get(6).getDate()).toString());
        Assertions.assertEquals("2022-10-23",(generalProductPriceStatistic.getStatisticGroupByDateList().get(7).getDate()).toString());
        Assertions.assertEquals(1,generalProductPriceStatistic.getStatisticGroupByDateList().get(0).getFrequency());
        Assertions.assertEquals(1,generalProductPriceStatistic.getStatisticGroupByDateList().get(1).getFrequency());
        Assertions.assertEquals(1,generalProductPriceStatistic.getStatisticGroupByDateList().get(2).getFrequency());
        Assertions.assertEquals(1,generalProductPriceStatistic.getStatisticGroupByDateList().get(3).getFrequency());
        Assertions.assertEquals(1,generalProductPriceStatistic.getStatisticGroupByDateList().get(4).getFrequency());
        Assertions.assertEquals(1,generalProductPriceStatistic.getStatisticGroupByDateList().get(5).getFrequency());
        Assertions.assertEquals(1,generalProductPriceStatistic.getStatisticGroupByDateList().get(6).getFrequency());
        Assertions.assertEquals(1,generalProductPriceStatistic.getStatisticGroupByDateList().get(7).getFrequency());

    }

    @Test
    void getProductByIdTest(){
        PriceDto priceDto = productServiceImpl.getById(3);
        Assertions.assertEquals("Mi",priceDto.getName());
        Assertions.assertEquals(12100d, priceDto.getPrice());
    }

    @Test
    void grtCountTest(){
        Long count = productServiceImpl.getCount();
        Assertions.assertEquals(3, count);
    }

}