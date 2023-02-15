package ru.cvetkov.exercise.service;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Slf4j
public class ParseServiceImpl implements ParseService{
    @Override
    public List<?> convertToList(Object object) {
        List<?> list = new ArrayList<>();
        if (object.getClass().isArray()){
            list = Arrays.asList((Object[]) object);
        }else if (object instanceof Collection<?>){
            list = new ArrayList<>((Collection<?>) object);
        }
        return list;
    }
}
