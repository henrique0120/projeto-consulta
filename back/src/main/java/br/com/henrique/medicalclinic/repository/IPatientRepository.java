package br.com.henrique.medicalclinic.repository;

import br.com.henrique.medicalclinic.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPatientRepository extends JpaRepository<PatientEntity, Long> {

    boolean existsByEmail(final String email);

    boolean existsByPhone(final String phone);

    Optional<PatientEntity> findByEmail(final String email);

    Optional<PatientEntity> findByPhone(final String phone);

}
