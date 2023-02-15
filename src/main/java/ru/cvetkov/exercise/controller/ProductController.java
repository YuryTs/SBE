package ru.cvetkov.exercise.controller;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.ObjectNotFoundException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cvetkov.exercise.models.*;
import ru.cvetkov.exercise.service.PriceService;
import ru.cvetkov.exercise.service.ProductService;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
@Slf4j
public class ProductController {

    ProductService productService;
    PriceService priceService;

    public ProductController(ProductService productService, PriceService priceService) {
        this.productService = productService;
        this.priceService = priceService;
    }

    @SneakyThrows
    @GetMapping(value = "/{id}")
    public PriceDto getProductById(@PathVariable(name = "id") long id) throws ObjectNotFoundException {
        return productService.getById(id);
    }

    @GetMapping(value = "/statistic")

    public AgrigatedStatistic getStatistic() {
        final ExecutorService executor = Executors.newFixedThreadPool(3);
        AgrigatedStatistic answer = new AgrigatedStatistic();
        try {
            Future<Long> countProducts = getFutureCount(1, executor);
            Future<List<Statistic>> futureStatistic = getFutureCountPriceProduct(2, executor);
            Future<List<DayStatistic>> futureDay = getFutureDateStatistic(3, executor);

            Long count = countProducts.get(5, TimeUnit.SECONDS);
            List<Statistic> statistics = futureStatistic.get(5, TimeUnit.SECONDS);
            List<DayStatistic> dayStatistics = futureDay.get(5, TimeUnit.SECONDS);
            answer = AgrigatedStatistic.builder()
                    .count(count)
                    .statisticList(statistics)
                    .dayStatisticList(dayStatistics).build();

            executor.shutdown();

        } catch (InterruptedException | TimeoutException | ExecutionException e) {
            e.printStackTrace();
        }
        return answer;
    }

    private Future<Long> getFutureCount(int ThreadNum, ExecutorService executorService) {
        return executorService.submit(() -> productService.getCount());
    }

    private Future<List<Statistic>> getFutureCountPriceProduct(int ThreadNum, ExecutorService executorService) {
        return executorService.submit(() -> priceService.getCountPriceProduct());
    }

    private Future<List<DayStatistic>> getFutureDateStatistic(int ThreadNum, ExecutorService executorService) {
        return executorService.submit(() -> priceService.getDateStatistic());
    }

    @GetMapping
    public List<PriceDto> getAllByDate(
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        List<Price> prices = priceService.getListByDate(date);
        return prices.stream().map(PriceDto::new).collect(Collectors.toList());
    }
}
