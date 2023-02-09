package ru.cvetkov.exercise.service;

import org.springframework.stereotype.Service;
import ru.cvetkov.exercise.models.Price;
import ru.cvetkov.exercise.repository.PriceDAO;

import java.util.List;


@Service
public class PriceServiceImpl implements PriceService{

    PriceDAO priceDAO;

    public PriceServiceImpl(PriceDAO priceDAO) {
        this.priceDAO = priceDAO;
    }

    @Override
    public List<Price> getAll() {
        return priceDAO.findAll();
    }
}
