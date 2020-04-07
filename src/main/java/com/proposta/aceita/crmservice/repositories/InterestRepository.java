package com.proposta.aceita.crmservice.repositories;

import com.proposta.aceita.crmservice.entities.Interest;
import com.proposta.aceita.crmservice.entities.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InterestRepository extends JpaRepository<Interest, Integer> {
    Optional<Interest> findByUserUsername(String username);
}
