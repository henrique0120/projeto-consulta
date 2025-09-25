package br.com.henrique.medicalclinic.service.impl;

import br.com.henrique.medicalclinic.entity.PatientEntity;
import br.com.henrique.medicalclinic.repository.IPatientRepository;
import br.com.henrique.medicalclinic.service.IPatientService;
import br.com.henrique.medicalclinic.service.query.IPatientQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class PatientService implements IPatientService {

    private final IPatientRepository repository;
    private final IPatientQueryService queryService;

    @Override
    public PatientEntity save(final PatientEntity entity) {
        queryService.verifyEmail(entity.getEmail());
        queryService.verifyPhone(entity.getPhone());

        return repository.save(entity);
    }

    @Override
    public PatientEntity update(final PatientEntity entity) {
        queryService.verifyEmail(entity.getId(), entity.getEmail());
        queryService.verifyPhone(entity.getId(), entity.getPhone());

        var stored = queryService.findById(entity.getId());
        stored.setName(entity.getName());
        stored.setPhone(entity.getPhone());
        stored.setEmail(entity.getEmail());
        return repository.save(stored);
    }

    @Override
    public void delete(final long id) {
        queryService.findById(id);
        repository.deleteById(id);
    }
}
