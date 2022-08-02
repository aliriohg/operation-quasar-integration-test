package com.ali.quasar.utils;

import io.restassured.response.Response;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ResponseWrapper {
    private Response response;
}
