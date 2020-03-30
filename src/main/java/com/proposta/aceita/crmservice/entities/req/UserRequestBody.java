package com.proposta.aceita.crmservice.entities.req;

import com.proposta.aceita.crmservice.entities.enums.Sex;
import com.proposta.aceita.crmservice.entities.enums.UserType;

import java.time.LocalDate;

public interface UserRequestBody {
    Integer getId();
    String getName();
    LocalDate getDateOfBirth();
    String getEmail();
    UserType getType();
    String getCpfCnpj();
    Sex getSex();
    AddressRequestBody getAddress();
}
