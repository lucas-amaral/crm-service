package com.proposta.aceita.crmservice.entities.req;

import com.proposta.aceita.crmservice.entities.enums.BarterType;

import java.math.BigDecimal;

public interface BarterRequestBody {
    Integer getId();
    Integer getInterestId();
    BarterType getType();
    BigDecimal getValue();
}
