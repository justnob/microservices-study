package com.amarnath.productservice.service;

import com.amarnath.productservice.dto.requestDto.CreateProductRequestDto;
import com.amarnath.productservice.dto.requestDto.ProductRequestDto;
import com.amarnath.productservice.dto.responseDto.ServerResponse;

public interface ProductService {

    public ServerResponse createProduct(CreateProductRequestDto createProductRequestDto);

    public ServerResponse getAllProducts();

    public ServerResponse getProductById(ProductRequestDto productRequestDto);

    public ServerResponse deleteProductById(ProductRequestDto productRequestDto);
    
}
