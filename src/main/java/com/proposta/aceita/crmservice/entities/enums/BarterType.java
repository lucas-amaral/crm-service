package com.proposta.aceita.crmservice.entities.enums;

public enum BarterType {
    VEHICLE("Veículo"), PROPERTY("Imóvel");

    private String type;

    BarterType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
