package com.proposta.aceita.crmservice.services;

import com.proposta.aceita.crmservice.entities.Barter;
import com.proposta.aceita.crmservice.entities.Interest;
import com.proposta.aceita.crmservice.entities.req.EditBarterRequestBody;
import com.proposta.aceita.crmservice.repositories.BarterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.List;

import static com.proposta.aceita.crmservice.entities.enums.BarterType.VEHICLE;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class BarterServiceTest {

    @MockBean
    private BarterRepository barterRepository;

    private BarterService barterService;

    @BeforeEach
    public void setup() {
        barterService = new BarterService(barterRepository);
    }
    
    @Test
    public void getById() {

        var id = 23;

        barterService.getById(id);

        verify(barterRepository).findById(id);
    }

    @Test
    public void getByInterestId() {

        barterService.getByInterest(2);

        verify(barterRepository).findByInterestId(2);
    }

    @Test
    public void save() {

        var interest = new Interest();
        var body = new EditBarterRequestBody(3, null, VEHICLE, BigDecimal.TEN);
        var barter = new Barter(3, interest, VEHICLE, BigDecimal.TEN);

        barterService.save(List.of(body), interest);

        verify(barterRepository).saveAll(List.of(barter));
    }

    @Test
    public void delete() {

        var id = 23;

        barterService.delete(id);

        verify(barterRepository).deleteById(id);
    }

}
