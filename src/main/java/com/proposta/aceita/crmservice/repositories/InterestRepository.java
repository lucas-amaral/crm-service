package com.proposta.aceita.crmservice.repositories;

import com.proposta.aceita.crmservice.entities.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface InterestRepository extends JpaRepository<Interest, Integer> {
    Optional<Interest> findByUserUsername(String username);

    @Transactional
    @Modifying
    @Query(value = "UPDATE interests i SET types = ARRAY[:types] WHERE id = :id", nativeQuery = true)
    void updateTypes(@Param("id") Integer id, @Param("types") String types);
}
