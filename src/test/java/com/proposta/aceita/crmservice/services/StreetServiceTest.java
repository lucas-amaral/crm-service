package com.proposta.aceita.crmservice.services;

import com.proposta.aceita.crmservice.entities.City;
import com.proposta.aceita.crmservice.entities.Neighborhood;
import com.proposta.aceita.crmservice.entities.Street;
import com.proposta.aceita.crmservice.entities.enums.State;
import com.proposta.aceita.crmservice.repositories.StreetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.mockito.Mockito.verify;

@SpringBootTest
public class StreetServiceTest {

    @MockBean
    private StreetRepository streetRepository;

    private StreetService streetService;

    @BeforeEach
    public void setup() {
        streetService = new StreetService(streetRepository);
    }
    
    @Test
    public void getByZipCode() {

        final String zipCode = "97110-564";

        streetService.getByZipCode(zipCode);

        verify(streetRepository).findById(zipCode);
    }

    @Test
    public void getList() {

        streetService.getList(0, 10);

        verify(streetRepository).findAll(PageRequest.of(0, 10, Sort.by("zipCode")));
    }

    @Test
    public void create() {

        final City city = new City(1, "Santa Maria", State.RS);
        final Neighborhood neighborhood = new Neighborhood(1, "Pé de Plátano", city);
        final Street street = new Street("97110-564", "Rua A Quatro (Vl Almeida)", neighborhood);

        streetService.create(street);

        verify(streetRepository).save(street);
    }

    @Test
    public void delete() {

        final City city = new City(1, "Santa Maria", State.RS);
        final Neighborhood neighborhood = new Neighborhood(1, "Pé de Plátano", city);
        final Street street = new Street("97110-564", "Rua A Quatro (Vl Almeida)", neighborhood);

        streetService.delete(street);

        verify(streetRepository).delete(street);
    }

}
