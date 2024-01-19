package com.amarnath.productservice.dto.responseDto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateProductResponseDto {

    private long id;
    private String name;
    private String price;
    private String description;
    
}
