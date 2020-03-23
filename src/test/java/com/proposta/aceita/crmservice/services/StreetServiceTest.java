package com.proposta.aceita.crmservice.services;

import com.proposta.aceita.crmservice.entities.City;
import com.proposta.aceita.crmservice.entities.Neighborhood;
import com.proposta.aceita.crmservice.entities.Street;
import com.proposta.aceita.crmservice.entities.enums.State;
import com.proposta.aceita.crmservice.repositories.StreetRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.verify;

@SpringBootTest
public class StreetServiceTest {

    @MockBean
    private StreetRepository streetRepository;

    private StreetService streetService;

    @Before
    public void before() {
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
        final String zipCode = "97110-564";

        streetService.getList();

        verify(streetRepository).findAll();
    }

    @Test
    public void create() {
        final City city = City.create(1, "Santa Maria", State.RS);
        final Neighborhood neighborhood = Neighborhood.create(1, "Pé de Plátano", city);
        final Street street = Street.create("97110-564", "Rua A Quatro (Vl Almeida)", neighborhood);

        streetService.create(street);

        verify(streetRepository).save(street);
    }

    @Test
    public void delete() {
        final City city = City.create(1, "Santa Maria", State.RS);
        final Neighborhood neighborhood = Neighborhood.create(1, "Pé de Plátano", city);
        final Street street = Street.create("97110-564", "Rua A Quatro (Vl Almeida)", neighborhood);

        streetService.delete(street);

        verify(streetRepository).delete(street);
    }

}
