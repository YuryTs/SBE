package ru.cvetkov.exercise.controller;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.ObjectNotFoundException;
import org.springframework.format.annotation.DateTimeFormat;
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

    public GeneralProductAndPriceStatistic getStatistic() {
        final ExecutorService executor = Executors.newFixedThreadPool(3);
        GeneralProductAndPriceStatistic answer = new GeneralProductAndPriceStatistic();
        try {
            Future<Long> countProducts = getFutureCount(1, executor);
            Future<List<StatisticGroupByProduct>> futureStatistic = getFutureCountPriceProduct(2, executor);
            Future<List<StatisticGroupByDate>> futureDay = getFutureDateStatistic(3, executor);

            Long count = countProducts.get(5, TimeUnit.SECONDS);
            List<StatisticGroupByProduct> statisticGroupByProducts = futureStatistic.get(5, TimeUnit.SECONDS);
            List<StatisticGroupByDate> statisticGroupByDates = futureDay.get(5, TimeUnit.SECONDS);
            answer = GeneralProductAndPriceStatistic.builder()
                    .count(count)
                    .statisticGroupByProductList(statisticGroupByProducts)
                    .statisticGroupByDateList(statisticGroupByDates).build();

            executor.shutdown();

        } catch (InterruptedException | TimeoutException | ExecutionException e) {
            e.printStackTrace();
        }
        return answer;
    }

    private Future<Long> getFutureCount(int ThreadNum, ExecutorService executorService) {
        return executorService.submit(() -> productService.getCount());
    }

    private Future<List<StatisticGroupByProduct>> getFutureCountPriceProduct(int ThreadNum, ExecutorService executorService) {
        return executorService.submit(() -> priceService.getCountPriceProduct());
    }

    private Future<List<StatisticGroupByDate>> getFutureDateStatistic(int ThreadNum, ExecutorService executorService) {
        return executorService.submit(() -> priceService.getDateStatistic());
    }

    @GetMapping
    public List<PriceDto> getAllByDate(
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        List<Price> prices = priceService.getListByDate(date);
        return prices.stream().map(PriceDto::new).collect(Collectors.toList());
    }
}
