package br.com.henrique.medicalclinic.controller.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginResponse {
    private String login;
    private String token;
}
