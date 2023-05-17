package com.telros.test.model;

public record UpdateUser(
        Long id,
        String lastname,
        String firstname,
        String surname,
        String birthday,
        String telephone,
        String email
) {
}
