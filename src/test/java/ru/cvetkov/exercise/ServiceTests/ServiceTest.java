package ru.cvetkov.exercise.ServiceTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.cvetkov.exercise.models.*;
import ru.cvetkov.exercise.repository.PriceDao;
import ru.cvetkov.exercise.repository.ProductDao;
import ru.cvetkov.exercise.service.PriceServiceImpl;
import ru.cvetkov.exercise.service.ProductServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ServiceTest {

    @InjectMocks
    private PriceServiceImpl priceServiceImpl;
    @InjectMocks
    private ProductServiceImpl productServiceImpl;
    @Mock
    PriceDao priceDao;

    @Mock
    ProductDao productDao;


    @Autowired
    public ServiceTest(PriceServiceImpl priceServiceImpl, ProductServiceImpl productServiceImpl) {
        this.priceServiceImpl = priceServiceImpl;
        this.productServiceImpl = productServiceImpl;
    }

    @Test
    void getListByDateTest() {
        Product product = new Product(1l, "nokia3310");
        Price price = new Price();
        price.setProduct(product);
        price.setId(1l);
        price.setDate(LocalDate.parse("2021-09-19"));
        price.setCost(2900d);

        List<Price> prices = new ArrayList<>();
        prices.add(price);

        Mockito.doReturn(prices).when(priceDao).getListByDate(LocalDate.now());
        List<PriceDto> priceDtoList = priceServiceImpl.getListByDate(LocalDate.now());

        Assertions.assertEquals("nokia3310", priceDtoList.get(0).getName());
        Assertions.assertEquals(2900d, priceDtoList.get(0).getPrice());
    }

    @Test
    void getCountPriceProductTest() {
        List<Object[]> list = new ArrayList<>();
        Object[] objects = {"nokia3310", 2l};
        list.add(objects);

        Mockito.doReturn(list).when(priceDao).getCountPriceProduct();
        List<StatisticGroupByProduct> statisticGroupByProductList = priceServiceImpl.getCountPriceProduct();

        Assertions.assertEquals("nokia3310", statisticGroupByProductList.get(0).getName());
        Assertions.assertEquals(2L, statisticGroupByProductList.get(0).getFrequency());
    }

    @Test
    void getDateStatisticTest() throws ParseException {
        List<Object[]> list = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse("2023-01-09");
        Object[] objects = {date, 3l};
        list.add(objects);
        Mockito.doReturn(list).when(priceDao).getDateStatistic();
        List<StatisticGroupByDate> statisticGroupByDates = priceServiceImpl.getDateStatistic();
        Date date1 = statisticGroupByDates.get(0).getDate();
        String dateFromDao = format.format(date1);
        Assertions.assertEquals("2023-01-09", dateFromDao);
        Assertions.assertEquals(3, statisticGroupByDates.get(0).getFrequency());
    }

    @Test
    void getGeneralStatisticTest() throws ParseException, SbException {
        Long count = 5l;
        Mockito.doReturn(count).when(productDao).count();
//        Mockito.doReturn(productDao.count()).when(productServiceImpl).getCount();

        List<Object[]> list = new ArrayList<>();
        Object[] objects = {"nokia3310", 2l};
        list.add(objects);
        Mockito.doReturn(list).when(priceDao).getCountPriceProduct();
        List<StatisticGroupByProduct> groupByProducts = priceServiceImpl.getCountPriceProduct();


        List<Object[]> list1 = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse("2023-01-09");
        Object[] objects1 = {date, 3l};
        list1.add(objects1);
        Mockito.doReturn(list1).when(priceDao).getDateStatistic();
        List<StatisticGroupByDate> groupByDateList = priceServiceImpl.getDateStatistic();

        GeneralProductPriceStatistic generalList = new GeneralProductPriceStatistic(count, groupByProducts, groupByDateList);

        Assertions.assertEquals(generalList.toString(), (priceServiceImpl.getGeneralStatistic()).toString());
    }

    @Test
    void getProductByIdTest() {
        Product product = new Product(1l, "nokia3310");
        Price price = new Price();
        price.setProduct(product);
        price.setId(1l);
        price.setDate(LocalDate.parse("2021-09-19"));
        price.setCost(2900d);
        List<Price> prices = new ArrayList<>();
        prices.add(price);
        product.setPrices(prices);
        PriceDto dtoExcpected = new PriceDto(product.getName(), price.getCost());
        Optional<Product> productOptional = Optional.of(product);

        when(productDao.findById(1l)).thenReturn(productOptional);
        PriceDto priceDto = productServiceImpl.getById(1);

        Assertions.assertEquals(dtoExcpected, priceDto);
    }

    @Test
    void getCountTest() {
        Mockito.doReturn(3l).when(productDao).count();
        Long count = productServiceImpl.getCount();
        Assertions.assertEquals(3l, count);
    }

}