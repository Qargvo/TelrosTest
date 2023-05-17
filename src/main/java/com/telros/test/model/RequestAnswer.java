package com.telros.test.model;

import org.springframework.http.HttpStatus;

public record RequestAnswer(
        String message,
        Integer statusCode
) {
}
