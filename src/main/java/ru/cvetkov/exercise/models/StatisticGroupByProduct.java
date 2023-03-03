package ru.cvetkov.exercise.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Objects;

@Data
public class StatisticGroupByProduct {

    @JsonProperty("Товар")
    String name;

    @JsonProperty("Частота изменения цены товара")
    Long frequency;

    public StatisticGroupByProduct() {
    }

    public StatisticGroupByProduct(String name, Long frequency) {
        this.name = name;
        this.frequency = frequency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getFrequency() {
        return frequency;
    }

    public void setFrequency(Long frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "StatisticGroupByProduct{" +
                "name='" + name  +
                ", frequency=" + frequency +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StatisticGroupByProduct that)) return false;
        return Objects.equals(name, that.name) && Objects.equals(frequency, that.frequency);
    }

}
