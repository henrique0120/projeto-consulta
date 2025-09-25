package br.com.henrique.medicalclinic.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;

public record PatientScheduleAppointmentResponse(
        @JsonProperty("id")
        Long id,
        @JsonProperty("day")
        Integer day,
        @JsonProperty("startAt")
        OffsetDateTime startAt,
        @JsonProperty("endAt")
        OffsetDateTime endAt,
        @JsonProperty("patientId")
        Long patientId,
        @JsonProperty("patientName")
        String patientName
) {}
