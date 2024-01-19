package com.amarnath.productservice.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequestDto {

    @NotBlank
    private String name;
    @NotBlank
    private String price;
    @NotBlank
    private String description;
    
}
