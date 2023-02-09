package ru.cvetkov.exercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.cvetkov.exercise.models.Product;
import ru.cvetkov.exercise.service.PriceServiceImpl;
import ru.cvetkov.exercise.service.ProductServiceImpl;

@SpringBootApplication
public class ExerciseApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExerciseApplication.class, args);
    }
}
