package ru.cvetkov.exercise.service;

import ru.cvetkov.exercise.models.PriceDto;

public interface ProductService {

    PriceDto getById(long id);

    Long getCount();

}
