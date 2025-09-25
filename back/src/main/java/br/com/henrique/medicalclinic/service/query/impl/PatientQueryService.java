package br.com.henrique.medicalclinic.service.query.impl;

import br.com.henrique.medicalclinic.entity.PatientEntity;
import br.com.henrique.medicalclinic.exception.NotFoundException;
import br.com.henrique.medicalclinic.exception.PhoneInUseException;
import br.com.henrique.medicalclinic.repository.IPatientRepository;
import br.com.henrique.medicalclinic.service.query.IPatientQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class PatientQueryService implements IPatientQueryService {

    private final IPatientRepository repository;

    @Override
    public PatientEntity findById(final long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Não foi encontrado o pacient de id " + id)
        );
    }

    @Override
    public List<PatientEntity> list() {
        return repository.findAll();
    }

    @Override
    public void verifyPhone(final String phone) {
        if (repository.existsByPhone(phone)) {
            var message = "O telefone " + phone + " já está em uso";
            throw new PhoneInUseException(message);
        }
    }

    @Override
    public void verifyPhone(final long id, final String phone) {
        var optional = repository.findByPhone(phone);
        if (optional.isPresent() && !Objects.equals(optional.get().getPhone(), phone)) {
            var message = "O telefone " + phone + " já está em uso";
            throw new PhoneInUseException(message);
        }
    }

    @Override
    public void verifyEmail(final String email) {
        if (repository.existsByEmail(email)) {
            var message = "O e-mail " + email + " já está em uso";
            throw new PhoneInUseException(message);
        }
    }

    @Override
    public void verifyEmail(final long id, final String email) {
        var optional = repository.findByEmail(email);
        if (optional.isPresent() && !Objects.equals(optional.get().getPhone(), email)) {
            var message = "O e-mail " + email + " já está em uso";
            throw new PhoneInUseException(message);
        }
    }
}