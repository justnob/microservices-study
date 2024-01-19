package com.amarnath.productservice.dto.responseDto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServerResponse {

    private String message;
    private int code;
    private boolean success;
    private Object data;

}
