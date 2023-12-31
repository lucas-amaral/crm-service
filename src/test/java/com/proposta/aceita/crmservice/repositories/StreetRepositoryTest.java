package com.proposta.aceita.crmservice.repositories;

import com.proposta.aceita.crmservice.entities.City;
import com.proposta.aceita.crmservice.entities.Neighborhood;
import com.proposta.aceita.crmservice.entities.Street;
import com.proposta.aceita.crmservice.entities.enums.State;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class StreetRepositoryTest {

    @Autowired
    private StreetRepository streetRepository;

    @Test
    void find() {
        var city = new City(1, "Santa Maria", State.RS);
        var neighborhood = new Neighborhood(1, "Pé de Plátano", city);
        var expected = new Street("97110-564", "Rua A Quatro (Vl Almeida)", neighborhood);

        assertThat(streetRepository.findById("97110-564")).hasValue(expected);
    }

    @Test
    void findAll() {
        var city = new City(1, "Santa Maria", State.RS);
        var neighborhood = new Neighborhood(1, "Pé de Plátano", city);
        var expected = new Street("97110-564", "Rua A Quatro (Vl Almeida)", neighborhood);

        assertThat(streetRepository.findAll(PageRequest.of(0, 10))).contains(expected);
    }

    @Test
    void findByNeighborhoodId() {
        var city = new City(1, "Santa Maria", State.RS);
        var neighborhood = new Neighborhood(1, "Pé de Plátano", city);
        var street = new Street("97110-564", "Rua A Quatro (Vl Almeida)", neighborhood);

        assertThat(streetRepository.findByNeighborhoodId(1, PageRequest.of(0, 100))).contains(street);
    }

}
