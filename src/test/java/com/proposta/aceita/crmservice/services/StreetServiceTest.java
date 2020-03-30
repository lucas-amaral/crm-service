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

        var zipCode = "97110-564";

        streetService.getByZipCode(zipCode);

        verify(streetRepository).findById(zipCode);
    }

    @Test
    public void list() {

        streetService.list(1, 0, 10);

        verify(streetRepository).findAllByNeighborhoodId(1, PageRequest.of(0, 10, Sort.by("zipCode")));
    }

    @Test
    public void create() {

        var city = new City(1, "Santa Maria", State.RS);
        var neighborhood = new Neighborhood(1, "Pé de Plátano", city);
        var street = new Street("97110-564", "Rua A Quatro (Vl Almeida)", neighborhood);

        streetService.save(street);

        verify(streetRepository).save(street);
    }

    @Test
    public void delete() {

        var city = new City(1, "Santa Maria", State.RS);
        var neighborhood = new Neighborhood(1, "Pé de Plátano", city);
        var street = new Street("97110-564", "Rua A Quatro (Vl Almeida)", neighborhood);

        streetService.delete(street);

        verify(streetRepository).delete(street);
    }

}
