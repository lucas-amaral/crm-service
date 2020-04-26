package com.proposta.aceita.crmservice.services;

import com.proposta.aceita.crmservice.services.integrations.MatchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.verify;

@SpringBootTest
public class NegotiationServiceTest {

    @MockBean
    private MatchService matchService;

    private NegotiationService negotiationService;

    @BeforeEach
    public void setup() {
        negotiationService = new NegotiationService(matchService);
    }

    @Test
    public void getByInterest() {
        var interestId = 61;

        negotiationService.getByInterest(interestId);

        verify(matchService).getNegotiationsByInterest(interestId);
    }


    @Test
    public void getBySale() {
        var saleId = 61;

        negotiationService.getBySale(saleId);

        verify(matchService).getNegotiationsBySale(saleId);
    }


    @Test
    public void deleteById() {
        var id = "anjass29kxkjansajkns";

        negotiationService.deleteById(id);

        verify(matchService).deleteNegotiation(id);
    }

    @Test
    public void approvedBySeller() {
        var id = "2324sdd022343";

        negotiationService.approvedBySeller(id);

        verify(matchService).approvedNegotiationBySeller(id);
    }

    @Test
    public void approvedByBuyer() {
        var id = "2324sdd022343";

        negotiationService.approvedByBuyer(id);

        verify(matchService).approvedNegotiationByBuyer(id);
    }

    @Test
    public void reprovedBySeller() {
        var id = "2324sdd022343";

        negotiationService.reprovedBySeller(id);

        verify(matchService).reprovedNegotiationBySeller(id);
    }

    @Test
    public void reprovedByBuyer() {
        var id = "2324sdd022343";

        negotiationService.reprovedByBuyer(id);

        verify(matchService).reprovedNegotiationByBuyer(id);
    }
}
