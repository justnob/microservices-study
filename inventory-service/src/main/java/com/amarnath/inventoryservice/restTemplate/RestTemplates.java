package com.amarnath.inventoryservice.restTemplate;


import com.amarnath.inventoryservice.dto.responseDto.ServerResponse;
import com.amarnath.inventoryservice.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.RequestScope;

@Slf4j
@Service
@RequestScope
public class RestTemplates {

    private static final String baseURL = "http://localhost:8082/";

    public ServerResponse doGetRequest(String path, String data){

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        String fullPath = baseURL.concat(path).concat(data);
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
