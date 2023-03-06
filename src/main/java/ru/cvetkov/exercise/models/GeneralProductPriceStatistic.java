package ru.cvetkov.exercise.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;
import java.util.Objects;

@Builder
@AllArgsConstructor
public class GeneralProductPriceStatistic {
    @JsonProperty("Количество товаров в БД:")
    Long count;

    @JsonProperty("Частота изменения цены в разрезе товаров:")
    List<StatisticGroupByProduct> statisticGroupByProductList;

    @JsonProperty("Частота изменения цены в разрезе дат:")
    List<StatisticGroupByDate> statisticGroupByDateList;

    public GeneralProductPriceStatistic() {
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
        return "GeneralProductPriceStatistic{" +
                "count=" + count +
                ", statisticGroupByProductList=" + statisticGroupByProductList +
                ", statisticGroupByDateList=" + statisticGroupByDateList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GeneralProductPriceStatistic that)) return false;
        return Objects.equals(count, that.count) && Objects.equals(statisticGroupByProductList, that.statisticGroupByProductList) && Objects.equals(statisticGroupByDateList, that.statisticGroupByDateList);
    }

}
