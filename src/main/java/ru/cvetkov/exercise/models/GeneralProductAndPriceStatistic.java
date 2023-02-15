package ru.cvetkov.exercise.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;

@Builder
@AllArgsConstructor
public class GeneralProductAndPriceStatistic {
    @JsonProperty("Количество товаров в БД:")
    Long count;

    @JsonProperty("Частота изменения цены в разрезе товаров:")
    List<StatisticGroupByProduct> statisticGroupByProductList;

    @JsonProperty("Частота изменения цены в разрезе дат:")
    List<StatisticGroupByDate> statisticGroupByDateList;

    public GeneralProductAndPriceStatistic() {
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<StatisticGroupByProduct> getStatisticGroupByProductList() {
        return statisticGroupByProductList;
    }

    public void setStatisticGroupByProductList(List<StatisticGroupByProduct> statisticGroupByProductList) {
        this.statisticGroupByProductList = statisticGroupByProductList;
    }

    public List<StatisticGroupByDate> getStatisticGroupByDateList() {
        return statisticGroupByDateList;
    }

    public void setStatisticGroupByDateList(List<StatisticGroupByDate> statisticGroupByDateList) {
        this.statisticGroupByDateList = statisticGroupByDateList;
    }

    @Override
    public String toString() {
        return "GeneralProductAndPriceStatistic{" +
                "count=" + count +
                ", statisticGroupByProductList=" + statisticGroupByProductList +
                ", statisticGroupByDateList=" + statisticGroupByDateList +
                '}';
    }
}
