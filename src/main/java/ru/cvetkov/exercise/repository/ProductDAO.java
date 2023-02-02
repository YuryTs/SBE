package ru.cvetkov.exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cvetkov.exercise.models.Product;

public interface ProductDAO extends JpaRepository<Product, Long> {

}
