package com.proposta.aceita.crmservice.repositories;

import com.proposta.aceita.crmservice.entities.Authority;
import com.proposta.aceita.crmservice.entities.AuthorityId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, AuthorityId> {
}
