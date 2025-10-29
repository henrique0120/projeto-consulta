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
        queryService.findById(id);
        repository.deleteById(id);
    }
}
