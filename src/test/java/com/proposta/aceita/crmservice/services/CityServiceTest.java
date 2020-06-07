package com.proposta.aceita.crmservice.services;

import com.proposta.aceita.crmservice.entities.City;
import com.proposta.aceita.crmservice.entities.enums.State;
import com.proposta.aceita.crmservice.entities.req.CityRequestBody;
import com.proposta.aceita.crmservice.repositories.CityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class CityServiceTest {

    @MockBean
    private CityRepository cityRepository;

    private CityService cityService;

    @BeforeEach
    void setup() {
        cityService = new CityService(cityRepository);
    }
    
    @Test
    void getById() {

        var id = 23;

        cityService.getById(id);

        verify(cityRepository).findById(id);
    }

    @Test
    void getByState() {

        cityService.getByState(State.SC);

        verify(cityRepository).findByState(State.SC);
    }

    @Test
    void save() {

        var body = new CityRequestBody(null, "Caxias do Sul", State.RS);
        var city = new City(null, "Caxias do Sul", State.RS);

        when(cityRepository.save(city)).thenReturn(city);

        cityService.save(body);

        verify(cityRepository).save(city);
    }

    @Test
    void delete() {

        var id = 23;

        cityService.delete(id);

        verify(cityRepository).deleteById(id);
    }

}
