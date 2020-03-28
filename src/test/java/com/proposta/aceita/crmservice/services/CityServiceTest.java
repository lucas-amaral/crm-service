package com.proposta.aceita.crmservice.services;

import com.proposta.aceita.crmservice.entities.City;
import com.proposta.aceita.crmservice.entities.enums.State;
import com.proposta.aceita.crmservice.entities.req.CityRequestBody;
import com.proposta.aceita.crmservice.repositories.CityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.verify;

@SpringBootTest
public class CityServiceTest {

    @MockBean
    private CityRepository cityRepository;

    private CityService cityService;

    @BeforeEach
    public void setup() {
        cityService = new CityService(cityRepository);
    }
    
    @Test
    public void getById() {

        final Integer id = 23;

        cityService.getById(id);

        verify(cityRepository).findById(id);
    }

    @Test
    public void list() {

        cityService.list();

        verify(cityRepository).findAll();
    }

    @Test
    public void save() {

        final CityRequestBody body = new CityRequestBody(null, "Caxias do Sul", State.RS);
        final City city = new City(null, "Caxias do Sul", State.RS);

        cityService.save(body);

        verify(cityRepository).save(city);
    }

    @Test
    public void delete() {

        final Integer id = 23;

        cityService.delete(id);

        verify(cityRepository).deleteById(id);
    }

}
