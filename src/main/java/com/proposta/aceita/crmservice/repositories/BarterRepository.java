package com.proposta.aceita.crmservice.repositories;

import com.proposta.aceita.crmservice.entities.Barter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BarterRepository extends JpaRepository<Barter, Integer> {
    List<Barter> findByInterestId(Integer interestId);
}
