package com.amarnath.orderservice.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonUtil {

    public static String toString(Object object) {
        String reponse = null;
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            reponse = ow.writeValueAsString(object);
        } catch (Exception e) {
            log.debug(e.getMessage());
        }

        return reponse;
    }

}
