package com.proposta.aceita.crmservice.entities.enums;

public enum Sex {
    MALE("masculino"), FEMALE("feminino"), NOT_INFORM("não informado");

    private String type;

    Sex(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
