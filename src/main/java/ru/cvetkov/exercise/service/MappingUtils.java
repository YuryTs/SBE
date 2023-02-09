package ru.cvetkov.exercise.service;

import org.springframework.stereotype.Service;
import ru.cvetkov.exercise.models.Price;
import ru.cvetkov.exercise.models.Product;
import ru.cvetkov.exercise.models.ProductDto;

import java.util.List;

@Service
public class MappingUtils {
    public ProductDto mapToProductDto(Product product){
        ProductDto dto = new ProductDto();
        dto.setName(product.getName());
        List<Price> priceList = product.getPrices();
        Double d;
        if (priceList.get(0) != null) {
           d = priceList.get(0).getPrice();
        }else{
            d = null;
        }
        dto.setPrice(d);
        return dto;
    }
//    public Product mapToProduct(ProductDto dto){
//        Product product = new Product();
//        product.setName(dto.getName());
//        product.setPrice(dto.getPrice());
//        return product;
//    }
}
