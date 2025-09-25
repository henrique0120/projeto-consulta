package br.com.henrique.medicalclinic.service.query;

import br.com.henrique.medicalclinic.entity.PatientEntity;

import java.util.List;

public interface IPatientQueryService {

    PatientEntity findById(final long id);

    List<PatientEntity> list();

    void verifyPhone(final String phone);

    void verifyPhone(final long id,final String phone);

    void verifyEmail(final String email);

    void verifyEmail(final long id,final String email);

}