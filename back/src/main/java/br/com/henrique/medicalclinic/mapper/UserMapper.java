package br.com.henrique.medicalclinic.mapper;

import br.com.henrique.medicalclinic.controller.request.LoginRequest;
import br.com.henrique.medicalclinic.controller.request.RegisterRequest;
import br.com.henrique.medicalclinic.controller.response.RegisterResponse;
import br.com.henrique.medicalclinic.dto.response.UserDTO;
import br.com.henrique.medicalclinic.entity.UserEntity;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDto(UserEntity entity);

    RegisterResponse toSaveResponse(final UserEntity entity);

    @Mapping(target = "id", ignore = true)
    UserEntity toEntity(final @Valid RegisterRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "phone", ignore = true)
    @Mapping(target = "roles", ignore = true)
    UserEntity toEntityLogin(final @Valid LoginRequest request);
}
