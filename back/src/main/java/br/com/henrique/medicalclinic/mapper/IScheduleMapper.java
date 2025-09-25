package br.com.henrique.medicalclinic.mapper;

import br.com.henrique.medicalclinic.controller.request.SaveScheduleRequest;
import br.com.henrique.medicalclinic.controller.response.PatientScheduleAppointmentResponse;
import br.com.henrique.medicalclinic.controller.response.SaveScheduleResponse;
import br.com.henrique.medicalclinic.controller.response.ScheduleAppointmentMonthResponse;
import br.com.henrique.medicalclinic.entity.ScheduleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface IScheduleMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "patient.id", source = "patientId")
    ScheduleEntity toEntity(final SaveScheduleRequest request);

    @Mapping(target = "patientId", source = "patient.id")
    SaveScheduleResponse toSaveResponse(final ScheduleEntity entity);

    @Mapping(target = "scheduledAppointments", expression = "java(toPatientMonthResponse(entities))")
    ScheduleAppointmentMonthResponse toMonthResponse(final int year, final int month, final List<ScheduleEntity> entities);

    List<PatientScheduleAppointmentResponse> toPatientMonthResponse(final List<ScheduleEntity> entities);

    @Mapping(target = "patientId", source = "patient.id")
    @Mapping(target = "patientName", source = "patient.name")
    @Mapping(target = "day", expression = "java(entity.getStartAt().getDayOfMonth())")
    PatientScheduleAppointmentResponse toPatientMonthResponse(final ScheduleEntity entity);
}