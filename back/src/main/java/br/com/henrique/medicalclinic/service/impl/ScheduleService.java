package br.com.henrique.medicalclinic.service.impl;

import br.com.henrique.medicalclinic.entity.PatientEntity;
import br.com.henrique.medicalclinic.entity.ScheduleEntity;
import br.com.henrique.medicalclinic.mapper.IScheduleMapper;
import br.com.henrique.medicalclinic.repository.IPatientRepository;
import br.com.henrique.medicalclinic.repository.IScheduleRepository;
import br.com.henrique.medicalclinic.service.IScheduleService;
import br.com.henrique.medicalclinic.service.query.IScheduleQueryService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
@AllArgsConstructor
public class ScheduleService implements IScheduleService {

    private static final Logger log = LoggerFactory.getLogger(ScheduleService.class);

    private final IScheduleRepository repository;
    private final IPatientRepository patientRepository;
    private final IScheduleQueryService queryService;
    private final IScheduleMapper mapper; // opcional, mantive caso use depois
    private final EmailService emailService; // ✅ novo serviço de e-mail


    @Override
    public ScheduleEntity save(final ScheduleEntity entity) {
        // Verifica se já existe um agendamento nesse horário
        queryService.verifyIfScheduleExists(entity.getStartAt(), entity.getEndAt());

        // Verifica se veio patient.id no entity (mapstruct costuma preencher apenas o id)
        if (entity.getPatient() == null || entity.getPatient().getId() == null) {
            throw new RuntimeException("Paciente não informado no agendamento (patient.id ausente).");
        }

        // Busca o paciente completo no banco (para obter o nome e demais campos)
        Long patientId = entity.getPatient().getId();
        PatientEntity patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado com ID: " + patientId));

        // Associa o paciente completo à entidade antes de salvar
        entity.setPatient(patient);

        // Salva o agendamento
        ScheduleEntity savedEntity = repository.save(entity);
        
            // ✅ Envia e-mail de confirmação personalizada
            try {

                String destinatario = patient.getEmail();

                // Formata datas e horas do agendamento
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                String inicio = entity.getStartAt().format(formatter);
                String fim = entity.getEndAt().format(formatter);

                // Assunto do e-mail com nome do paciente
                String assunto = "Confirmação de Consulta - " + patient.getName();

                // Corpo da mensagem
                String mensagem = String.format(
                    "Olá %s,\n\n" +
                    "Sua consulta foi agendada com sucesso!\n\n" +
                    "🗓 Data e horário: %s às %s\n\n" +
                    "Agradecemos por escolher a Medical Clinic.\n\n" +
                    "Atenciosamente,\n" +
                    "Equipe Medical Clinic",
                    patient.getName(), inicio, fim
                );

                // Envia o e-mail
                emailService.enviarEmail(destinatario, assunto, mensagem);

                log.info("📧 E-mail de confirmação enviado para {}", destinatario);

            } catch (Exception e) {
                log.error("❌ Falha ao enviar e-mail de confirmação: {}", e.getMessage());
            }


        // Formata as datas
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String inicio = savedEntity.getStartAt().format(formatter);
        String fim = savedEntity.getEndAt().format(formatter);
        String nomePaciente = patient.getName() != null ? patient.getName() : "Paciente sem nome";

        // Log no console
        log.info("==============================================");
        log.info("✅ Consulta marcada com sucesso!");
        log.info("ID: {}", savedEntity.getId());
        log.info("Paciente: {}", nomePaciente);
        log.info("Início: {}", inicio);
        log.info("Fim: {}", fim);
        log.info("==============================================");

        return savedEntity;
    }

@Override
public void delete(final long id) {
    // Busca o agendamento para pegar dados antes de excluir
    ScheduleEntity schedule = queryService.findById(id);

    // Garante que o agendamento existe
    if (schedule == null) {
        throw new RuntimeException("Agendamento não encontrado para exclusão com ID: " + id);
    }

    // Deleta o agendamento
    repository.deleteById(id);

    // Envia o e-mail de cancelamento
    try {
        PatientEntity patient = schedule.getPatient();
        if (patient != null && patient.getEmail() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            String inicio = schedule.getStartAt().format(formatter);
            String fim = schedule.getEndAt().format(formatter);

            String assunto = "Cancelamento de Consulta - " + patient.getName();
            String mensagem = String.format(
                "Olá %s,\n\n" +
                "Informamos que sua consulta marcada para o dia %s às %s foi cancelada.\n\n" +
                "Se desejar reagendar, entre em contato com a nossa equipe.\n\n" +
                "Atenciosamente,\n" +
                "Equipe Medical Clinic",
                patient.getName(), inicio, fim
            );

            emailService.enviarEmail(patient.getEmail(), assunto, mensagem);
            log.info("📧 E-mail de cancelamento enviado para {}", patient.getEmail());
        }
    } catch (Exception e) {
        log.error("❌ Falha ao enviar e-mail de cancelamento: {}", e.getMessage());
    }

    log.info("🗑️ Agendamento ID {} removido com sucesso", id);
}

}
