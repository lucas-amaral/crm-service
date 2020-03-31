package com.proposta.aceita.crmservice.services;

import com.proposta.aceita.crmservice.entities.Garage;
import com.proposta.aceita.crmservice.entities.Property;
import com.proposta.aceita.crmservice.entities.req.GarageRequestBody;
import com.proposta.aceita.crmservice.repositories.GarageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.mockito.Mockito.verify;

@SpringBootTest
public class GarageServiceTest {

    @MockBean
    private GarageRepository garageRepository;

    private GarageService garageService;

    @BeforeEach
    public void setup() {
        garageService = new GarageService(garageRepository);
    }
    
    @Test
    public void getById() {

        var id = 23;

        garageService.getById(id);

        verify(garageRepository).findById(id);
    }

    @Test
    public void getByPropertyId() {

        garageService.getByPropertyId(2);

        verify(garageRepository).findByPropertyId(2);
    }

    @Test
    public void save() {

        var property = new Property();
        var body = new GarageRequestBody(3, 20, "snajknjkasnsa");
        var garage = new Garage(3, property, 20, "snajknjkasnsa");

        garageService.save(List.of(body), property);

        verify(garageRepository).saveAll(List.of(garage));
    }

    @Test
    public void delete() {

        var id = 23;

        garageService.delete(id);

        verify(garageRepository).deleteById(id);
    }

}
