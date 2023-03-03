package ru.cvetkov.exercise.service;

import ru.cvetkov.exercise.models.PriceDto;
import ru.cvetkov.exercise.models.SbException;

public interface ProductService {

    PriceDto getById(long id) throws SbException;

    Long getCount();

}
