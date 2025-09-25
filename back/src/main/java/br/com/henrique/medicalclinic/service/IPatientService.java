package br.com.henrique.medicalclinic.service;


import br.com.henrique.medicalclinic.entity.PatientEntity;

public interface IPatientService {

    PatientEntity save(final PatientEntity entity);

    PatientEntity update(final PatientEntity entity);

    void delete(final long id);

}