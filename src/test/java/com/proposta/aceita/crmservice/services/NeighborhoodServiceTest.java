package com.proposta.aceita.crmservice.services;

import com.proposta.aceita.crmservice.repositories.NeighborhoodRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.verify;

@SpringBootTest
public class NeighborhoodServiceTest {

    @MockBean
    private NeighborhoodRepository neighborhoodRepository;

    private NeighborhoodService neighborhoodService;

    @BeforeEach
    public void setup() {
        neighborhoodService = new NeighborhoodService(neighborhoodRepository);
    }
    
    @Test
    public void getById() {

        final Integer id = 46;

        neighborhoodService.getById(id);

        verify(neighborhoodRepository).findById(id);
    }

    @Test
    public void getByCity() {

        neighborhoodService.getByCity(1);

        verify(neighborhoodRepository).findByCityId(1);
    }

}
