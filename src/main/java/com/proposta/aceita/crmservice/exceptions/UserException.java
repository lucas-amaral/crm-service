package com.proposta.aceita.crmservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class UserException extends RuntimeException {

    private List<String> errors;

    public UserException(String message) {
        super(message);
    }

    public UserException(String message, List<String> errors) {
        super(message);
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}

