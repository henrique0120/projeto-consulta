package br.com.henrique.medicalclinic.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "security.config")
public class JWTConfig {
    private String KEY;
    private String PREFIX;
    private Long EXPIRATION;
}
