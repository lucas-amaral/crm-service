package com.proposta.aceita.crmservice.entities.req;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface SaleRequestBody {

    Integer getId();

    Integer getPropertyId();

    BigDecimal getValue();

    boolean isFinancing();

    BigDecimal getFinancingValue();

    boolean isBarterVehicle();

    BigDecimal getBarterVehicleValue();

    boolean isBarterProperty();

    BigDecimal getBarterPropertyValue();

    LocalDate getAgencying();
}
