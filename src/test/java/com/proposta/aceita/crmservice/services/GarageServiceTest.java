package com.proposta.aceita.crmservice.services;

import com.proposta.aceita.crmservice.entities.Garage;
import com.proposta.aceita.crmservice.entities.Property;
import com.proposta.aceita.crmservice.entities.req.GarageRequestBody;
import com.proposta.aceita.crmservice.repositories.GarageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class GarageServiceTest {

    @MockBean
    private GarageRepository garageRepository;

    private GarageService garageService;

    @BeforeEach
    void setup() {
        garageService = new GarageService(garageRepository);
    }
    
    @Test
    void getById() {

        var id = 23;

        garageService.getById(id);

        verify(garageRepository).findById(id);
    }

    @Test
    void getByPropertyId() {

        garageService.getByPropertyId(2);

        verify(garageRepository).findByPropertyId(2);
    }

    @Test
    void save() {

        var property = new Property();
        var body = new GarageRequestBody(3, 20, "sna23j3knj32ka23sn23sa");
        var garage = new Garage(3, property, 20, "sna23j3knj32ka23sn23sa");

        garageService.save(List.of(body), property);

        verify(garageRepository).saveAll(List.of(garage));

        verifyNoMoreInteractions(garageRepository);
    }

    @Test
    void saveWhenThereIsGarageToRemove() {

        var property = new Property();

        var body3 = new GarageRequestBody(3, 20, "2012029");
        var body7 = new GarageRequestBody(null, 21, "sna23j3knj32ka23sn23sa");

        var garage2 = new Garage(2, property, 12, "12092190");
        var garage3 = new Garage(3, property, 20, "2012029");
        var garage5 = new Garage(5, property, 39, "2389");
        var garage7 = new Garage(null, property, 21, "sna23j3knj32ka23sn23sa");

        property.setGarages(List.of(garage2, garage3, garage5));

        garageService.save(List.of(body7, body3), property);

        verify(garageRepository).saveAll(List.of(garage7, garage3));
        verify(garageRepository).deleteById(2);
        verify(garageRepository).deleteById(5);
    }

    @Test
    void delete() {

        var id = 23;

        garageService.deleteRemoved(id);

        verify(garageRepository).deleteById(id);
    }

}
