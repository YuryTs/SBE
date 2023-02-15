package ru.cvetkov.exercise.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ConvertToListServiceImpl implements ConvertToListService {
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
