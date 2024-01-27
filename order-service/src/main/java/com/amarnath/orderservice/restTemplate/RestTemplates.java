package com.amarnath.orderservice.restTemplate;

import com.amarnath.orderservice.Constants.OrderConstants;
import com.amarnath.orderservice.dto.responseDto.ServerResponse;
import com.amarnath.orderservice.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequestScope
public class RestTemplates {

    private static final String baseURL = "http://localhost:8082/";

    public ServerResponse doGetRequest(String path, List<String> skuCodesList){

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseURL.concat(path))
                .queryParam(OrderConstants.SKU_CODE, skuCodesList.toArray());
        String fullPath = builder.toUriString();

        log.debug("Requesting url :: {}", fullPath);
        try {
            ResponseEntity<ServerResponse> response = restTemplate.exchange(
                fullPath,
                HttpMethod.GET,
                entity,
                ServerResponse.class);

            if (response.getBody() != null){
                log.debug("The Response Data :: {}", JsonUtil.toString(response.getBody()));
                return response.getBody();
            }else{
                return ServerResponse.builder()
                    .success(false)
                    .code(2)
                    .message(response.getBody().getMessage())
                    .build();
            }
        } catch (Exception e) {
            log.error("Error occurred while making the POST request", e);
            return ServerResponse.builder()
                    .success(false)
                    .code(2)
                    .message("Error occurred while making the request")
                    .build();
        }
    }

    public <T> ServerResponse doPostRequest(String path, T data){

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> entity = new HttpEntity<>(data, headers);

        String fullPath = baseURL.concat(path);
        log.debug("Requesting url :: {}", fullPath);
        try {
            ResponseEntity<ServerResponse> response = restTemplate.exchange(
                fullPath,
                HttpMethod.POST,
                entity,
                ServerResponse.class);

            if (response.getBody() != null){
                log.debug("The Response Data :: {}", JsonUtil.toString(response.getBody()));
                return response.getBody();
            }else{
                return ServerResponse.builder()
                    .success(false)
                    .code(2)
                    .message(response.getBody().getMessage())
                    .build();
            }

        } catch (Exception e) {
            log.error("Error occurred while making the POST request", e);
            return ServerResponse.builder()
                .success(false)
                .code(2)
                .message("Error occurred while making the request")
                .build();
        }
    }

}
