package com.proposta.aceita.crmservice.entities.enums;

public enum PropertyType {
        HOUSE("Casa"), APARTMENT("Apartamento"), COMERCIAL("Comercial"), FARM("Chácara"), GROUND("Terreno");

    private String type;

    PropertyType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static String toString(PropertyType propertyType) {
        return String.format("'%s'", propertyType);
    }
}
