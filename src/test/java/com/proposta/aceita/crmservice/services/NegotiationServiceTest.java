package com.proposta.aceita.crmservice.services;

import com.proposta.aceita.crmservice.services.integrations.MatchService;
import com.proposta.aceita.crmservice.services.integrations.NotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class NegotiationServiceTest {

    @MockBean
    private MatchService matchService;

    @MockBean
    private NotificationService notificationService;

    private NegotiationService negotiationService;

    @BeforeEach
    void setup() {
        negotiationService = new NegotiationService(matchService, notificationService);
    }

    @Test
    void getByInterest() {
        var interestId = 61;

        negotiationService.getByInterest(interestId);

        verify(matchService).getNegotiationsByInterest(interestId);
    }


    @Test
    void getBySale() {
        var saleId = 61;

        negotiationService.getBySale(saleId);

        verify(matchService).getNegotiationsBySale(saleId);
    }


    @Test
    void deleteById() {
        var id = "anjass29kxkjansajkns";

        negotiationService.deleteById(id);

        verify(matchService).deleteNegotiation(id);
    }

    @Test
    void approvedBySeller() {
        var id = "2324sdd022343";

        negotiationService.approvedBySeller(id);

        verify(matchService).approvedNegotiationBySeller(id);
    }

    @Test
    void approvedByBuyer() {
        var id = "2324sdd022343";

        negotiationService.approvedByBuyer(id);

        verify(matchService).approvedNegotiationByBuyer(id);
    }

    @Test
    void reprovedBySeller() {
        var id = "2324sdd022343";

        negotiationService.reprovedBySeller(id);

        verify(matchService).reprovedNegotiationBySeller(id);
    }

    @Test
    void reprovedByBuyer() {
        var id = "2324sdd022343";

        negotiationService.reprovedByBuyer(id);

        verify(matchService).reprovedNegotiationByBuyer(id);
    }
}
