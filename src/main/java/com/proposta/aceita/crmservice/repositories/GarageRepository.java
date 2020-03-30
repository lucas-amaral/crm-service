package com.proposta.aceita.crmservice.repositories;

import com.proposta.aceita.crmservice.entities.Garage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GarageRepository extends JpaRepository<Garage, Integer> {
    List<Garage> findAllByPropertyId(Integer propertyId);
}
