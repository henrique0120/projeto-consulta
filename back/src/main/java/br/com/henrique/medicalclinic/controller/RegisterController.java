package br.com.henrique.medicalclinic.controller;

import br.com.henrique.medicalclinic.controller.request.RegisterRequest;
import br.com.henrique.medicalclinic.controller.response.RegisterResponse;
import br.com.henrique.medicalclinic.mapper.UserMapper;
import br.com.henrique.medicalclinic.service.IRegisterService;
import br.com.henrique.medicalclinic.service.impl.RegisterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {

    private final IRegisterService service;
    private final UserMapper mapper;
    private final RegisterService userService;

    @PostMapping
    RegisterResponse createUser(@RequestBody @Valid final RegisterRequest request){
        var entity = mapper.toEntity(request);
        userService.createUser(entity);
        return mapper.toSaveResponse(entity);
    }
}
