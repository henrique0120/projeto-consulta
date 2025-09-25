package br.com.henrique.medicalclinic.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
public class RegisterUserRequest {
    private String name;
    private String password;
    private String email;
    private String phone;
    private List<String> roles = new ArrayList<>();
}
