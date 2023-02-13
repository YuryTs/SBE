package ru.cvetkov.exercise.models;

import lombok.Data;

@Data
public class Statistic {
    String name;
    Integer frequency;

    public Statistic(String name, Integer frequency) {
        this.name = name;
        this.frequency = frequency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }
}
