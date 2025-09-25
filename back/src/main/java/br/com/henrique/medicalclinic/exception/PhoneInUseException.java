package br.com.henrique.medicalclinic.exception;

public class PhoneInUseException extends RuntimeException {

  public PhoneInUseException(String message) {
    super(message);
  }

}
