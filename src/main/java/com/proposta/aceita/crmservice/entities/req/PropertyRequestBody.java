package com.proposta.aceita.crmservice.entities.req;

import com.proposta.aceita.crmservice.entities.enums.PropertyType;

import java.math.BigDecimal;
import java.util.List;

public interface PropertyRequestBody {
    Integer getId();
    Integer getUserId();
    String getDescription();
    PropertyType getType();
    BigDecimal getArea();
    Integer getRegistration();
    AddressRequestBody getAddress();
    Integer getIptu();
    Integer getDorms();
    Integer getSuites();
    Integer getBathrooms();
    Boolean getPool();
    Boolean getBalcony();
    Boolean getElevator();
    Boolean getBarbecueGrill();
    List<GarageRequestBody> getGarages();
    Boolean getEnable();
}