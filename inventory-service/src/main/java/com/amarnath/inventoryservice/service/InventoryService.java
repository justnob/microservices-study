package com.amarnath.inventoryservice.service;

import com.amarnath.inventoryservice.dto.requestDto.ProductCreateRequest;
import com.amarnath.inventoryservice.dto.requestDto.ProductDeleteRequest;
import com.amarnath.inventoryservice.dto.requestDto.ProductUpdateRequest;
import com.amarnath.inventoryservice.dto.responseDto.ServerResponse;

import java.util.List;

public interface InventoryService {

    public ServerResponse isInStock(List<String> skuCode);

    public ServerResponse createProductStock(ProductCreateRequest productCreateRequest);

    public ServerResponse deleteProductStock(ProductDeleteRequest productDeleteRequest);

    public ServerResponse updateProductStock(ProductUpdateRequest productUpdateRequest);

    public ServerResponse findProductStockBySkuCode(ProductDeleteRequest productDeleteRequest);

}
