package ru.cvetkov.exercise.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.cvetkov.exercise.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
