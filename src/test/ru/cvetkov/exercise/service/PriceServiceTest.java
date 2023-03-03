package ru.cvetkov.exercise.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.springframework.boot.test.context.SpringBootTest;
import ru.cvetkov.exercise.models.*;
import ru.cvetkov.exercise.repository.PriceDao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class PriceServiceTest {
    @InjectMocks
    private PriceServiceImpl priceServiceImpl;
    @Mock
    PriceDao priceDao;
    @Mock
    ProductServiceImpl productServiceImpl;


    @Test
    void getListByDateTest() {

        Price price = new Price();
        price.setProduct(PriceServiceTest.getProduct());
        price.setId(1l);
        price.setDate(LocalDate.parse("2021-09-19"));
        price.setCost(2900d);

        List<Price> prices = new ArrayList<>();
        prices.add(price);
        PriceDto dtoExpected = new PriceDto(PriceServiceTest.getProduct().getName(), price.getCost());

        when(priceDao.getListByDate(LocalDate.now())).thenReturn(prices);
        List<PriceDto> priceDtoList = priceServiceImpl.getListByDate(LocalDate.now());

        assertEquals(dtoExpected, priceDtoList.get(0));
        assertEquals(1, priceDtoList.size());
    }

    @Test
    void getCountPriceProductTest() {

        when(priceDao.getCountPriceProduct()).thenReturn(PriceServiceTest.getListProduct());
        List<StatisticGroupByProduct> statisticGroupByProductList = priceServiceImpl.getCountPriceProduct();

        assertEquals("nokia3310", statisticGroupByProductList.get(0).getName());
        assertEquals(2L, statisticGroupByProductList.get(0).getFrequency());
        assertEquals(1, statisticGroupByProductList.size());
    }

    @Test
    void getDateStatisticTest() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        when(priceDao.getDateStatistic()).thenReturn(PriceServiceTest.getListDate());

        List<StatisticGroupByDate> statisticGroupByDates = priceServiceImpl.getDateStatistic();
        Date date1 = statisticGroupByDates.get(0).getDate();
        String dateFromDao = format.format(date1);

        assertEquals("2023-01-09", dateFromDao);
        assertEquals(3, statisticGroupByDates.get(0).getFrequency());
        assertEquals(1, statisticGroupByDates.size());
    }

    @Test
    void getGeneralStatistic() throws SbException, ParseException {
        Long count = 5l;
        when(productServiceImpl.getCount()).thenReturn(count);
        when(priceDao.getCountPriceProduct()).thenReturn(PriceServiceTest.getListProduct());
        List<StatisticGroupByProduct> groupByProducts = priceServiceImpl.getCountPriceProduct();
        when(priceDao.getDateStatistic()).thenReturn(PriceServiceTest.getListDate());
        List<StatisticGroupByDate> groupByDateList = priceServiceImpl.getDateStatistic();
        GeneralProductPriceStatistic generalStatistic = new GeneralProductPriceStatistic(count, groupByProducts, groupByDateList);

        assertEquals(generalStatistic.toString(), (priceServiceImpl.getGeneralStatistic()).toString());
    }

    @Test
    void getStatisticSingleThreadTread() throws ParseException {
        Long count = 5l;
        when(productServiceImpl.getCount()).thenReturn(count);
        when(priceDao.getCountPriceProduct()).thenReturn(PriceServiceTest.getListProduct());
        List<StatisticGroupByProduct> groupByProducts = priceServiceImpl.getCountPriceProduct();
        when(priceDao.getDateStatistic()).thenReturn(PriceServiceTest.getListDate());
        List<StatisticGroupByDate> groupByDateList = priceServiceImpl.getDateStatistic();
        GeneralProductPriceStatistic generalStatistic = new GeneralProductPriceStatistic(count, groupByProducts, groupByDateList);

        assertEquals(generalStatistic.toString(), (priceServiceImpl.getStatisticSingleThread()).toString());
    }
    private static Product getProduct(){
    Product product = new Product(1l, "nokia3310");
    return product;
}
    private static List<Object[]> getListProduct(){
        List<Object[]> list = new ArrayList<>();
        Object[] objects = {"nokia3310", 2l};
        list.add(objects);
        return list;
    }
    private static List<Object[]> getListDate() throws ParseException {
        List<Object[]> list = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse("2023-01-09");
        Object[] objects = {date, 3l};
        list.add(objects);
        return list;
    }
}