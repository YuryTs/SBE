package ru.cvetkov.exercise.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class StatisticGroupByDate {

    @JsonProperty ("Дата")
    Date date;
    @JsonProperty ("Частота изменений цен")
    Long frequency;

    public StatisticGroupByDate(Date date, Long frequency) {
        this.date = date;
        this.frequency = frequency;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getFrequency() {
        return frequency;
    }

    public void setFrequency(Long frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "StatisticGroupByDate{" +
                "Дата =" + date +
                ", frequency=" + frequency +
                '}';
    }
}
