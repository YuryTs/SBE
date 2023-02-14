package ru.cvetkov.exercise.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;

@Builder
@AllArgsConstructor
public class AgrigatedStatistic {
    @JsonProperty("Количество товаров в БД:")
    Long count;

    @JsonProperty("Частота изменения цены в разрезе товаров:")
    List<Statistic> statisticList;

    @JsonProperty("Частота изменения цены в разрезе дат:")
    List<DayStatistic> dayStatisticList;

    public AgrigatedStatistic() {
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<Statistic> getStatisticList() {
        return statisticList;
    }

    public void setStatisticList(List<Statistic> statisticList) {
        this.statisticList = statisticList;
    }

    public List<DayStatistic> getDayStatisticList() {
        return dayStatisticList;
    }

    public void setDayStatisticList(List<DayStatistic> dayStatisticList) {
        this.dayStatisticList = dayStatisticList;
    }

    @Override
    public String toString() {
        return "AgrigatedStatistic{" +
                "count=" + count +
                ", statisticList=" + statisticList +
                ", dayStatisticList=" + dayStatisticList +
                '}';
    }
}
