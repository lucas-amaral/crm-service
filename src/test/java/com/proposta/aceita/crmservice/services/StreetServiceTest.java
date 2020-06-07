package com.proposta.aceita.crmservice.services;

import com.proposta.aceita.crmservice.entities.City;
import com.proposta.aceita.crmservice.entities.Neighborhood;
import com.proposta.aceita.crmservice.entities.Street;
import com.proposta.aceita.crmservice.entities.enums.State;
import com.proposta.aceita.crmservice.repositories.StreetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class StreetServiceTest {

    @MockBean
    private StreetRepository streetRepository;

    private StreetService streetService;

    @BeforeEach
    void setup() {
        streetService = new StreetService(streetRepository);
    }
    
    @Test
    void getByZipCode() {

        var zipCode = "97110-564";

        streetService.getByZipCode(zipCode);

        verify(streetRepository).findById(zipCode);
    }

    @Test
    void list() {

        streetService.list(1, 0, 10);

        verify(streetRepository).findByNeighborhoodId(1, PageRequest.of(0, 10, Sort.by("zipCode")));
    }

    @Test
    void create() {

        var city = new City(1, "Santa Maria", State.RS);
        var neighborhood = new Neighborhood(1, "Pé de Plátano", city);
        var street = new Street("97110-564", "Rua A Quatro (Vl Almeida)", neighborhood);

        streetService.save(street);

        verify(streetRepository).save(street);
    }

    @Test
    void delete() {

        var city = new City(1, "Santa Maria", State.RS);
        var neighborhood = new Neighborhood(1, "Pé de Plátano", city);
        var street = new Street("97110-564", "Rua A Quatro (Vl Almeida)", neighborhood);

        streetService.delete(street);

        verify(streetRepository).delete(street);
    }

}
