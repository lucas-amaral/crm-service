package com.proposta.aceita.crmservice.repositories;

import com.proposta.aceita.crmservice.entities.Neighborhood;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NeighborhoodRepository extends JpaRepository<Neighborhood, Integer> {
    List<Neighborhood> findByCityId(Integer cityId);
}
