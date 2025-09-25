package br.com.henrique.medicalclinic.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ListPatientResponse(
        @JsonProperty("id")
        Long id,
        @JsonProperty("name")
        String name,
        @JsonProperty("email")
        String email,
        @JsonProperty("phone")
        String phone
) {}