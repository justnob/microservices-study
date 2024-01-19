package com.amarnath.inventoryservice.service;

import com.amarnath.inventoryservice.dto.requestDto.ProductCreateRequest;
import com.amarnath.inventoryservice.dto.requestDto.ProductDeleteRequest;
import com.amarnath.inventoryservice.dto.requestDto.ProductUpdateRequest;
import com.amarnath.inventoryservice.dto.responseDto.ServerResponse;

public interface InventoryService {

    public ServerResponse isInStock(String skuCode);

    public ServerResponse createProductStock(ProductCreateRequest productCreateRequest);

    public ServerResponse deleteProductStock(ProductDeleteRequest productDeleteRequest);

    public ServerResponse updateProductStock(ProductUpdateRequest productUpdateRequest);

    public ServerResponse findProductStockBySkuCode(ProductDeleteRequest productDeleteRequest);

}
