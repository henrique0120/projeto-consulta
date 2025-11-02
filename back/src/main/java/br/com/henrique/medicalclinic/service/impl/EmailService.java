package br.com.henrique.medicalclinic.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);

    private final JavaMailSender mailSender;

    /**
     * Envia um e-mail simples (texto puro).
     *
     * @param destinatario E-mail de destino (ex: patient.getEmail())
     * @param assunto      Assunto do e-mail
     * @param corpo        Corpo do e-mail (mensagem)
     */
    public void enviarEmail(String destinatario, String assunto, String corpo) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(destinatario);
            message.setSubject(assunto);
            message.setText(corpo);
            message.setFrom("medicalclinicprojeto@gmail.com"); // ⚠️ o mesmo do application.yml

            mailSender.send(message);
            log.info("📧 E-mail enviado com sucesso para {}", destinatario);

        } catch (Exception e) {
            log.error("❌ Erro ao enviar e-mail: {}", e.getMessage());
            throw new RuntimeException("Falha ao enviar e-mail", e);
        }
    }
}
