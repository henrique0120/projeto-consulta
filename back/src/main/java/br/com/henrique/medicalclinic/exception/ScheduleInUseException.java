package br.com.henrique.medicalclinic.exception;

public class ScheduleInUseException extends RuntimeException {

  public ScheduleInUseException(String message) {
    super(message);
  }

}
