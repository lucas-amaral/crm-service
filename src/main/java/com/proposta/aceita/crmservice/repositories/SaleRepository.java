package com.proposta.aceita.crmservice.repositories;

import com.proposta.aceita.crmservice.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Integer> {
    List<Sale> findByPropertyId(Integer propertyId);
}
