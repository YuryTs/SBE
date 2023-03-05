package ru.cvetkov.exercise.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ru.cvetkov.exercise.models.Price;
import ru.cvetkov.exercise.models.PriceDto;
import ru.cvetkov.exercise.models.Product;
import ru.cvetkov.exercise.models.SbException;
import ru.cvetkov.exercise.repository.ProductDao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductServiceTest {
    @InjectMocks
    private ProductServiceImpl productServiceImpl;
    @Mock
    ProductDao productDao;

    @Test
    void getCountTest() {
        when(productDao.count()).thenReturn(3l);
        Long count = productServiceImpl.getCount();
        assertEquals(3l, count);
    }

    @Test
    void getByIdTest() throws SbException {
        Product product = new Product(1l, "nokia3310");
        Price price = new Price();
        price.setProduct(product);
        price.setId(1l);
        price.setDate(LocalDate.parse("2021-09-19"));
        price.setCost(2900d);
        List<Price> prices = new ArrayList<>();
        prices.add(price);
        product.setPrices(prices);
        PriceDto dtoExpected = new PriceDto(product.getName(), price.getCost());
        Optional<Product> productOptional = Optional.of(product);

        when(productDao.findById(1l)).thenReturn(productOptional);
        PriceDto priceDto = productServiceImpl.getById(1);

        assertEquals(dtoExpected, priceDto);
    }

    @Test
    void getByIdExpectedExceptionTest() {
        long id = 2l;

        SbException thrown = assertThrows(SbException.class, () -> {
            when(productDao.findById(id)).thenReturn(Optional.empty());
            productServiceImpl.getById(id);
        });
        SbException exception = new SbException("Товара с id=" + id + " не существует");
        assertEquals(exception.getMessage(), thrown.getMessage());
    }
}