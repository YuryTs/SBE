package ru.cvetkov.exercise.service;

import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;
import ru.cvetkov.exercise.models.Price;
import ru.cvetkov.exercise.repository.PriceDAO;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;


@Service
public class PriceServiceImpl implements PriceService{

    @Autowired
    PriceDAO priceDAO;

    @Override
    public List<Price> getAll() {
        return priceDAO.findAll();
    }

}
