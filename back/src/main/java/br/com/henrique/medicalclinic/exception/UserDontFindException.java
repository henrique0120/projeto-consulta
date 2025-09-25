package br.com.henrique.medicalclinic.exception;

public class UserDontFindException extends RuntimeException {
    public UserDontFindException(String message) {
        super(message);
    }
}

