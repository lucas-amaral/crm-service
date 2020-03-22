package com.proposta.aceita.crmservice.entities.enums;

public enum PropertyType {
    HOUSE("casa"), APARTMENT("apartamento"), COMERCIAL("comercial"), FARM("Chácara"), GROUND("Terreno");

    private String type;

    PropertyType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
