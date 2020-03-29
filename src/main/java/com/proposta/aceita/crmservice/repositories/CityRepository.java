package com.proposta.aceita.crmservice.repositories;

import com.proposta.aceita.crmservice.entities.City;
import com.proposta.aceita.crmservice.entities.enums.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface CityRepository extends JpaRepository<City, Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE cities c SET c.name = :name, c.state = :state WHERE c.id = :id")
    int update(@Param("id") Integer id, @Param("name") String name, @Param("state") State state);

    List<City> findByState(State state);
}
