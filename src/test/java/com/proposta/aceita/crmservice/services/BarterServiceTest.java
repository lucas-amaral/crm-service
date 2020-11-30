package com.proposta.aceita.crmservice.services;

import com.proposta.aceita.crmservice.entities.Barter;
import com.proposta.aceita.crmservice.entities.Interest;
import com.proposta.aceita.crmservice.entities.req.EditBarterRequestBody;
import com.proposta.aceita.crmservice.repositories.BarterRepository;
import com.proposta.aceita.crmservice.repositories.InterestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static com.proposta.aceita.crmservice.entities.enums.BarterType.VEHICLE;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class BarterServiceTest {

    @MockBean
    private BarterRepository barterRepository;

    @MockBean
    private InterestRepository interestRepository;

    private BarterService barterService;

    @BeforeEach
    void setup() {
        barterService = new BarterService(barterRepository, interestRepository);
    }
    
    @Test
    void getById() {

        var id = 23;

        barterService.getById(id);

        verify(barterRepository).findById(id);
    }

    @Test
    void getByInterestId() {

        barterService.getByInterest(2);

        verify(barterRepository).findByInterestId(2);
    }

    @Test
    void save() {

        var interest = new Interest();
        var body = new EditBarterRequestBody(3, null, VEHICLE, BigDecimal.TEN);
        var barter = new Barter(3, interest, VEHICLE, BigDecimal.TEN);

        barterService.save(List.of(body), interest);

        verify(barterRepository).saveAll(List.of(barter));
    }

    @Test
    void saveWithInterest() {

        var interest = new Interest();
        var body = new EditBarterRequestBody(3, 23, VEHICLE, BigDecimal.TEN);
        var barter = new Barter(3, interest, VEHICLE, BigDecimal.TEN);

        when(interestRepository.findById(23)).thenReturn(Optional.of(interest));

        barterService.save(List.of(body), interest);

        verify(barterRepository).saveAll(List.of(barter));
    }

    @Test
    void delete() {

        var id = 23;

        barterService.delete(id);

        verify(barterRepository).deleteById(id);
    }

}
