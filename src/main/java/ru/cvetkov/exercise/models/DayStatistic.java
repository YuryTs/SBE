package ru.cvetkov.exercise.models;

import java.time.LocalDate;

public class DayStatistic {
    LocalDate localDate;
    Integer frequency;

    public DayStatistic(LocalDate localDate, Integer frequency) {
        this.localDate = localDate;
        this.frequency = frequency;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }
}
