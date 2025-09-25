package br.com.henrique.medicalclinic.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "security.config")
public class SecurityConfig {

    private String PREFIX;
    private String KEY;
    private Long EXPIRATION;

}

