package br.com.henrique.medicalclinic.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserDTO {
    private String email;
    private String password;
}
