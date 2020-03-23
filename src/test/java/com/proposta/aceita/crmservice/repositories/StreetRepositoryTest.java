package com.proposta.aceita.crmservice.repositories;

import com.proposta.aceita.crmservice.entities.City;
import com.proposta.aceita.crmservice.entities.Neighborhood;
import com.proposta.aceita.crmservice.entities.Street;
import com.proposta.aceita.crmservice.entities.enums.State;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class StreetRepositoryTest {

    @Autowired
    private StreetRepository streetRepository;

    @Test
    public void find() {
        final City city = City.create(1, "Santa Maria", State.RS);
        final Neighborhood neighborhood = Neighborhood.create(1, "Pé de Plátano", city);
        final Street expected = Street.create("97110-564", "Rua A Quatro (Vl Almeida)", neighborhood);

        assertThat(streetRepository.findById("97110-564")).isEqualTo(expected);
    }

    @Test
    public void findAll() {
        final City city = City.create(1, "Santa Maria", State.RS);
        final Neighborhood neighborhood = Neighborhood.create(1, "Pé de Plátano", city);
        final Street expected = Street.create("97110-564", "Rua A Quatro (Vl Almeida)", neighborhood);

        assertThat(streetRepository.findAll(PageRequest.of(0, 10))).contains(expected);
    }

}
