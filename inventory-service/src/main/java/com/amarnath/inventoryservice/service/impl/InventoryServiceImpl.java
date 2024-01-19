package com.amarnath.inventoryservice.service.impl;

import com.amarnath.inventoryservice.dto.requestDto.ProductCreateRequest;
import com.amarnath.inventoryservice.dto.requestDto.ProductDeleteRequest;
import com.amarnath.inventoryservice.dto.requestDto.ProductUpdateRequest;
import com.amarnath.inventoryservice.dto.responseDto.ServerResponse;
import com.amarnath.inventoryservice.module.Inventory;
import com.amarnath.inventoryservice.repository.InventoryRepository;
import com.amarnath.inventoryservice.service.InventoryService;
import com.amarnath.inventoryservice.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public ServerResponse isInStock(String skuCode) {

        Optional<Inventory> product = inventoryRepository.findBySkuCode(skuCode);

        if (product.isPresent()){
            log.debug("Product is in stock: {}", JsonUtil.toString(product.get()));
            return ServerResponse.builder()
                    .code(1)
                    .success(true)
                    .message("Product is available!")
                    .build();
        }else {
            log.debug("Product is not in stock!");
            return ServerResponse.builder()
                    .code(2)
                    .success(false)
                    .message("Product is not available!")
                    .build();
        }
    }

    @Override
    public ServerResponse createProductStock(ProductCreateRequest productCreateRequest) {
        log.debug("Checking for the product stock if already exist!");
        Optional<Inventory> productInStock = inventoryRepository.findBySkuCode(productCreateRequest.getSkuCode());
        if (productInStock.isEmpty()){
            Inventory stock = Inventory.builder()
                    .skuCode(productCreateRequest.getSkuCode())
                    .quantity(productCreateRequest.getQuantity())
                    .build();
            log.debug("Saving the product stock in the database");
            Inventory savedStock = inventoryRepository.save(stock);

            log.debug("Product stock saved successfully: {}", JsonUtil.toString(savedStock));

            return ServerResponse.builder()
                    .code(1)
                    .success(true)
                    .message("Product stock created successfully!")
                    .data(savedStock)
                    .build();
        }else {
            log.debug("product stock already in the database");
            return ServerResponse.builder()
                    .code(2)
                    .success(false)
                    .message("Product stock already present!")
                    .build();
        }
    }

    @Override
    public ServerResponse deleteProductStock(ProductDeleteRequest productDeleteRequest) {
        log.debug("Checking for product stock in the database!");
        Optional<Inventory> productStock = inventoryRepository.findBySkuCode(productDeleteRequest.getSkuCode());

        if (productStock.isPresent()){
            log.debug("Deleting the product from database!");
            inventoryRepository.deleteById(productStock.get().getId());
            log.debug("Product stock deleted successfully: {} requested id: {}", JsonUtil.toString(productStock.get()), productDeleteRequest.getSkuCode());
            return ServerResponse.builder()
                    .code(1)
                    .success(true)
                    .message("Product stock deleted successfully!")
                    .data(productStock.get())
                    .build();
        }else {
            log.debug("Product stock not present in database!");
            return ServerResponse.builder()
                    .code(2)
                    .success(false)
                    .message("Product stock not present!")
                    .build();
        }
    }

    @Override
    public ServerResponse updateProductStock(ProductUpdateRequest productUpdateRequest) {
        Optional<Inventory> productStock = inventoryRepository.findById(productUpdateRequest.getId());

        if (productStock.isPresent()){
            log.debug("Updating the product stock: {} requested product id: {}", JsonUtil.toString(productStock.get()), productUpdateRequest.getId());
            Inventory productStockDetail = productStock.get();

            productStockDetail.setQuantity(productUpdateRequest.getQuantity());

            Inventory savedProductStock = inventoryRepository.save(productStockDetail);
            log.debug("Updated product stock: {}", JsonUtil.toString(savedProductStock));

            return ServerResponse.builder()
                    .code(1)
                    .success(true)
                    .message("Product stock update successfully!")
                    .data(savedProductStock)
                    .build();
        }else {
            log.debug("Product stock not present in database!");
            return ServerResponse.builder()
                    .code(2)
                    .success(false)
                    .message("Product stock not present!")
                    .build();
        }
    }

    @Override
    public ServerResponse findProductStockBySkuCode(ProductDeleteRequest productDeleteRequest) {
        Optional<Inventory> productBySkuCode = inventoryRepository.findBySkuCode(productDeleteRequest.getSkuCode());
        if (productBySkuCode.isPresent()){
            log.debug("Product by skuCode: {} data: {}", productDeleteRequest.getSkuCode(), JsonUtil.toString(productBySkuCode));
            return ServerResponse.builder()
                    .code(1)
                    .success(true)
                    .message("Product stock update successfully!")
                    .data(productBySkuCode)
                    .build();
        }else {
            log.debug("Product stock not present in database!");
            return ServerResponse.builder()
                    .code(2)
                    .success(false)
                    .message("Product stock not present!")
                    .build();
        }
    }
}
