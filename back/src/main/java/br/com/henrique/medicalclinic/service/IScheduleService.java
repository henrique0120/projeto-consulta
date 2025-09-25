package br.com.henrique.medicalclinic.service;

import br.com.henrique.medicalclinic.entity.ScheduleEntity;

public interface IScheduleService {

    ScheduleEntity save(final ScheduleEntity entity);

    void delete(final long id);

}