package com.proposta.aceita.crmservice.repositories;

import com.proposta.aceita.crmservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
