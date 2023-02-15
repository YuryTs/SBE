package ru.cvetkov.exercise.models;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;

@Builder
@AllArgsConstructor
public class AgrigatedStatistic {
    Long count;
    List<Statistic> statisticList;
    List<DayStatistic> dayStatisticList;


}
