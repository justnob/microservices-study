package com.amarnath.inventoryservice.dto.requestDto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductUpdateRequest {

    private long id;
    private int quantity;

}
