package com.amarnath.inventoryservice.dto.requestDto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDeleteRequest {

    private String skuCode;

}
