package br.com.henrique.medicalclinic;

import io.jsonwebtoken.security.Keys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.crypto.SecretKey;

@SpringBootApplication
public class MedicalClinicApplication {
	public static void main(String[] args) {
        SecretKey key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS512);
		SpringApplication.run(MedicalClinicApplication.class, args);
        System.out.println(key);
	}


}
