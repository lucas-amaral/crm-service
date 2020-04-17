package com.proposta.aceita.crmservice.entities.req;

import com.proposta.aceita.crmservice.entities.enums.PropertyType;

import java.math.BigDecimal;
import java.util.List;

public interface InterestRequestBody {
    Integer getId();

    String getUsername();

    BigDecimal getValue();

    Boolean getFinancing();

    BigDecimal getFinancingValue();

    List<PropertyType> getTypes();

    List<Integer> getNeighborhoodIds();

    Integer getDorms();

    Integer getSuites();

    Integer getBathrooms();

    Boolean getPool();

    Boolean getBalcony();

    Boolean getElevator();

    Boolean getBarbecueGrill();

    List<? extends BarterRequestBody> getBarters();

    String getStringTypes();
}
