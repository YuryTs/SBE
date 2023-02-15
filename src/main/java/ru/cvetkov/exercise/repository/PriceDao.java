package ru.cvetkov.exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.cvetkov.exercise.models.Price;

import java.time.LocalDate;
import java.util.List;

public interface PriceDao extends JpaRepository<Price, Long> {

    @Query ("SELECT p AS price FROM Price p WHERE p.id IN (SELECT MAX(p1.id) FROM Price p1 " +
            "WHERE p1.date <= :select_date GROUP BY p1.product.id HAVING MAX(p1.date) <= :select_date)")
    List<Price> getListByDate(@Param("select_date") LocalDate date);


    @Query(value = "select p.name , count (pr.price) as frequency from prices pr join products p on p.id=pr.product_id group by p.name", nativeQuery = true)
    List<Object[]> getCountPriceProduct();

    @Query(value = "select pr.date , count (pr.price) as frequency from prices pr join products p on p.id=pr.product_id group by pr.date", nativeQuery = true)
    List<Object[]> getDateStatistic();

}
