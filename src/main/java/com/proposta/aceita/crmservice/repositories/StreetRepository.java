package com.proposta.aceita.crmservice.repositories;

import com.proposta.aceita.crmservice.entities.Street;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StreetRepository extends JpaRepository<Street, String> {
    Page<Street> findByNeighborhoodId(Integer neighborhoodId, Pageable pageable);
}
