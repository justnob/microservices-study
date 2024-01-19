package com.amarnath.productservice.service.impl;

import com.amarnath.productservice.dto.requestDto.CreateProductRequestDto;
import com.amarnath.productservice.dto.requestDto.ProductRequestDto;
import com.amarnath.productservice.dto.responseDto.CreateProductResponseDto;
import com.amarnath.productservice.dto.responseDto.ServerResponse;
import com.amarnath.productservice.mapper.ProductMapper;
import com.amarnath.productservice.model.Product;
import com.amarnath.productservice.service.ProductService;
import com.amarnath.productservice.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

import com.amarnath.productservice.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ServerResponse createProduct(CreateProductRequestDto createProductRequestDto) {
        
        Product product = Product.builder()
        .name(createProductRequestDto.getName())
        .description(createProductRequestDto.getDescription())
        .price(createProductRequestDto.getPrice())
        .build();

        Product savedProduct = productRepository.save(product);

        CreateProductResponseDto createProductResponseDto = ProductMapper.mapToProductResponse(savedProduct);

        log.debug("Saved product in database: {}", JsonUtil.toString(createProductResponseDto));
        return ServerResponse.builder()
                .code(1)
                .message("Product created successfully!")
                .success(true)
                .data(createProductResponseDto)
                .build();
    }

    @Override
    public ServerResponse getAllProducts() {

        List<Product> allProducts = productRepository.findAll();
        List<CreateProductResponseDto> productListResponse = allProducts.stream().map(ProductMapper::mapToProductResponse).toList();
        log.debug("List of product from database: {}", JsonUtil.toString(allProducts));
       if (!allProducts.isEmpty()){
           return ServerResponse.builder()
                   .code(1)
                   .message("Product list fetch successfully!")
                   .success(true)
                   .data(productListResponse)
                   .build();
       }else {
           return ServerResponse.builder()
                   .code(2)
                   .message("No product found!")
                   .success(false)
                   .build();
       }

    }

    @Override
    public ServerResponse getProductById(ProductRequestDto productRequestDto) {

        Optional<Product> productById = productRepository.findById(productRequestDto.getId());

        if (productById.isPresent()){
            CreateProductResponseDto createProductResponseDto = ProductMapper.mapToProductResponse(productById.get());
            log.debug("Product fetched: {}", JsonUtil.toString(createProductResponseDto));
            return ServerResponse.builder()
                    .code(1)
                    .message("Product fetch successfully!")
                    .success(true)
                    .data(createProductResponseDto)
                    .build();
        }else {
            log.debug("Product does not exist with id: {}", productRequestDto.getId());
            return ServerResponse.builder()
                    .code(2)
                    .message("Product does not exist!")
                    .success(false)
                    .build();
        }
    }

    @Override
    public ServerResponse deleteProductById(ProductRequestDto productRequestDto) {

        Optional<Product> productById = productRepository.findById(productRequestDto.getId());

        if (productById.isPresent()){
            CreateProductResponseDto createProductResponseDto = ProductMapper.mapToProductResponse(productById.get());
            productRepository.deleteById(productRequestDto.getId());
            log.debug("Product deleted: {}", JsonUtil.toString(createProductResponseDto));
            return ServerResponse.builder()
                    .code(1)
                    .message("Product deleted successfully!")
                    .success(true)
                    .data(createProductResponseDto)
                    .build();
        }else {
            log.debug("Product does not exist with id: {}", productRequestDto.getId());
            return ServerResponse.builder()
                    .code(2)
                    .message("Product does not exist!")
                    .success(false)
                    .build();
        }
    }

}
