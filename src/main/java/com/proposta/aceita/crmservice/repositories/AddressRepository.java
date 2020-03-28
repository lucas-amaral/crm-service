package com.proposta.aceita.crmservice.repositories;

import com.proposta.aceita.crmservice.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
