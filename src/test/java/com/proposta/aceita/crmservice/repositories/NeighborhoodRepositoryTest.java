package com.proposta.aceita.crmservice.repositories;

import com.proposta.aceita.crmservice.entities.City;
import com.proposta.aceita.crmservice.entities.Neighborhood;
import com.proposta.aceita.crmservice.entities.enums.State;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class NeighborhoodRepositoryTest {

    @Autowired
    private NeighborhoodRepository neighborhoodRepository;

    @Test
    @Sql(scripts = "classpath:clearTables.sql")
    public void findByCIty() {
        var city = new City(1, "Santa Maria", State.RS);
        var neighborhood = new Neighborhood(1, "Pé de Plátano", city);

        assertThat(neighborhoodRepository.findByCityId(1)).contains(neighborhood);
    }

    @Test
    public void findAllByIdWithNull() {
        assertThat(neighborhoodRepository.findByCityId(null)).isEmpty();
    }

}
