package com.proposta.aceita.crmservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class InterestException extends RuntimeException {

    private List<String> errors;

    public InterestException(String message) {
        super(message);
    }

    public InterestException(String message, List<String> errors) {
        super(message);
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}

