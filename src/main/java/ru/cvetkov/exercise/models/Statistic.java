package ru.cvetkov.exercise.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Statistic {

    @JsonProperty("Товар")
    String name;

    @JsonProperty("Частота изменения цены товара")
    Long frequency;

    public Statistic(String name, Long frequency) {
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
        return "Statistic{" +
                "name='" + name  +
                ", frequency=" + frequency +
                '}';
    }
}
