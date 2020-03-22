package com.proposta.aceita.crmservice.entities.enums;

public enum UserType {
    FISICAL("física"), LEGAL("jurídica");

    private String type;

    UserType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
