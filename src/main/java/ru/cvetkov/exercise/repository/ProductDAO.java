package ru.cvetkov.exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cvetkov.exercise.models.Products;

public interface ProductDAO extends JpaRepository<Products, Long> {

}
