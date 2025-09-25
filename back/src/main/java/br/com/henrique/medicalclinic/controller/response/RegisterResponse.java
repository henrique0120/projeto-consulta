package br.com.henrique.medicalclinic.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record RegisterResponse(
        @JsonProperty("id")
        Long id,
        @JsonProperty("name")
        String name,
        @JsonProperty("password")
        String password,
        @JsonProperty("email")
        String email,
        @JsonProperty("phone")
        String phone,
        @JsonProperty("roles")
        List<String> roles
) {
}
