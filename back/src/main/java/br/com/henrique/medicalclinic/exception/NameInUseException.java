package br.com.henrique.medicalclinic.exception;

public class NameInUseException extends RuntimeException {
    public NameInUseException(String message) {
        super(message);
    }
}
