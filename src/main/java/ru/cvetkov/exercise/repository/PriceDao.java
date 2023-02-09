package ru.cvetkov.exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cvetkov.exercise.models.Price;

public interface PriceDao extends JpaRepository<Price, Long> {
}
