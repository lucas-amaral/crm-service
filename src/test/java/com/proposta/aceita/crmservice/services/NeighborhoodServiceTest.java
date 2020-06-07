package com.proposta.aceita.crmservice.services;

import com.proposta.aceita.crmservice.repositories.NeighborhoodRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class NeighborhoodServiceTest {

    @MockBean
    private NeighborhoodRepository neighborhoodRepository;

    private NeighborhoodService neighborhoodService;

    @BeforeEach
    void setup() {
        neighborhoodService = new NeighborhoodService(neighborhoodRepository);
    }
    
    @Test
    void getById() {

        var id = 46;

        neighborhoodService.getById(id);

        verify(neighborhoodRepository).findById(id);
    }

    @Test
    void getByCity() {

        neighborhoodService.getByCity(1);

        verify(neighborhoodRepository).findByCityId(1);
    }

}
