package br.com.henrique.medicalclinic.mapper;

import br.com.henrique.medicalclinic.controller.request.SavePatientRequest;
import br.com.henrique.medicalclinic.controller.request.UpdatePatientRequest;
import br.com.henrique.medicalclinic.controller.response.ListPatientResponse;
import br.com.henrique.medicalclinic.controller.response.PatientDetailResponse;
import br.com.henrique.medicalclinic.controller.response.SavePatientResponse;
import br.com.henrique.medicalclinic.controller.response.UpdatePatientResponse;
import br.com.henrique.medicalclinic.entity.PatientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface IPatientMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "schedules", ignore = true)
    PatientEntity toEntity(final SavePatientRequest request);

    SavePatientResponse toSaveResponse(final PatientEntity entity);

    @Mapping(target = "schedules", ignore = true)
    PatientEntity toEntity(final long id, final UpdatePatientRequest request);

    UpdatePatientResponse toUpdateResponse(final PatientEntity entity);

    PatientDetailResponse toDetailResponse(final PatientEntity entity);

    List<ListPatientResponse> toListResponse(final List<PatientEntity> entities);

}
