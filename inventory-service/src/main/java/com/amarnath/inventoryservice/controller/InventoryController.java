package com.amarnath.inventoryservice.controller;

import com.amarnath.inventoryservice.dto.requestDto.ProductCreateRequest;
import com.amarnath.inventoryservice.dto.requestDto.ProductDeleteRequest;
import com.amarnath.inventoryservice.dto.requestDto.ProductUpdateRequest;
import com.amarnath.inventoryservice.dto.responseDto.ServerResponse;
import com.amarnath.inventoryservice.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/v1/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    /***
     * METHOD GET
     * @param skuCode
     * RETURN the boolean value for the product is available in the database or not.
     */
    @GetMapping("/{sku-code}")
    public ResponseEntity<?> isInStock(@PathVariable("sku-code") String skuCode){
        log.debug("Entering product stock check api.......");
        ServerResponse inStock = inventoryService.isInStock(skuCode);
        return new ResponseEntity<>(inStock, HttpStatus.OK);
    }

    /***
     * METHOD POST
     * @param productCreateRequest
     * RETURN the response of creation of product stock.
     */
    @PostMapping("/create")
    public ResponseEntity<?> createProductStock(@RequestBody ProductCreateRequest productCreateRequest){
        log.debug("Entering product stock creation api.......");
        ServerResponse productStock = inventoryService.createProductStock(productCreateRequest);
        return new ResponseEntity<>(productStock, HttpStatus.CREATED);
    }

    /***
     * METHOD POST
     * @param productUpdateRequest
     * RETURN response of the update process of the product stock in the database.
     */
    @PostMapping("/update")
    public ResponseEntity<?>  updateProductStock(@RequestBody ProductUpdateRequest productUpdateRequest){
        log.debug("Entering product stock update api.......");
        ServerResponse productUpdate = inventoryService.updateProductStock(productUpdateRequest);
        return new ResponseEntity<>(productUpdate, HttpStatus.OK);
    }

    /***
     * METHOD POST
     * @param productDeleteRequest
     * RETURN the response of the deletion of product stock from the database.
     */
    @PostMapping("/delete")
    public ResponseEntity<?> deleteProductStock(@RequestBody ProductDeleteRequest productDeleteRequest){
        log.debug("Entering product stock delete api.......");
        ServerResponse deletedProductStock = inventoryService.deleteProductStock(productDeleteRequest);
        return new ResponseEntity<>(deletedProductStock, HttpStatus.OK);
    }

    /***
     * METHOD POST
     * @param productDeleteRequest
     * RETURN the product stock details by the skuCode of the product stock.
     */
    @PostMapping("/find")
    public ResponseEntity<?> findProduct(@RequestBody ProductDeleteRequest productDeleteRequest){
        log.debug("Entering product stock finding api.......");
        return new ResponseEntity<>("", HttpStatus.OK);
    }

}
