package com.amarnath.productservice.mapper;

import com.amarnath.productservice.dto.responseDto.CreateProductResponseDto;
import com.amarnath.productservice.model.Product;

public class ProductMapper {


    public static CreateProductResponseDto mapToProductResponse(Product product){
        return CreateProductResponseDto.builder()
                .id(product.getId())
                .description(product.getDescription())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }

}
