package ru.cvetkov.exercise.service;

import org.springframework.stereotype.Service;
import ru.cvetkov.exercise.models.Price;
import ru.cvetkov.exercise.repository.PriceDao;

import java.util.List;


@Service
public class PriceServiceImpl implements PriceService{

    PriceDao priceDao;

    public PriceServiceImpl(PriceDao priceDAO) {
        this.priceDao = priceDAO;
    }

    @Override
    public List<Price> getAll() {
        return priceDao.findAll();
    }
}
