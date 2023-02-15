package ru.cvetkov.exercise.service;

import org.springframework.data.crossstore.ChangeSetPersister;
import ru.cvetkov.exercise.models.PriceDto;

public interface ProductService {

    PriceDto getById(long id) throws ChangeSetPersister.NotFoundException;

    Long getCount();

}
