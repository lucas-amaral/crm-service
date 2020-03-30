package com.proposta.aceita.crmservice.repositories;

import com.proposta.aceita.crmservice.entities.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Integer> {
}
