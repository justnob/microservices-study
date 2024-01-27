package com.amarnath.productservice.controller;

import com.amarnath.productservice.dto.responseDto.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amarnath.productservice.dto.requestDto.CreateProductRequestDto;
import com.amarnath.productservice.dto.requestDto.ProductRequestDto;
import com.amarnath.productservice.service.ProductService;

@RestController
@Slf4j
@RequestMapping("v1/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /***
     * METHOD POST
     * @param createProductRequestDto
     * RETURN the response if the creation of product is successful of not.
     */
    @PostMapping("/create")
    public ResponseEntity<?> addProduct(@Validated @RequestBody CreateProductRequestDto createProductRequestDto){
        log.debug("Entering product create api.......");
        ServerResponse productResponse = productService.createProduct(createProductRequestDto);

        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    /***
     * METHOD GET
     * RETURN the list of the products available in the database.
     */
    @GetMapping("/list")
    public ResponseEntity<?> getProducts(){
        log.debug("Entering product list api.......");
        ServerResponse allProducts = productService.getAllProducts();
        return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }

    /***
     * METHOD POST
     * @param productRequestDto
     * RETURN the requested product if available or not in the database.
     */
    @PostMapping("/get/id")
    public ResponseEntity<?> getProduct(@Validated @RequestBody ProductRequestDto productRequestDto){
        log.debug("Entering get product by id api.......");
        ServerResponse product =  productService.getProductById(productRequestDto);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    /***
     * METHOD POST
     * @param productRequestDto
     * RETURN the product from the database base on the input request provided as id of product.
     */
    @PostMapping("/delete/id")
    public ResponseEntity<?> deleteProduct(@Validated @RequestBody ProductRequestDto productRequestDto){
        log.debug("Entering delete product by id api.......");
        ServerResponse product =  productService.deleteProductById(productRequestDto);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    
}
