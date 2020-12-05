package com.proposta.aceita.crmservice.services;

import com.proposta.aceita.crmservice.entities.Interest;
import com.proposta.aceita.crmservice.entities.Property;
import com.proposta.aceita.crmservice.entities.Sale;
import com.proposta.aceita.crmservice.entities.enums.BarterType;
import com.proposta.aceita.crmservice.entities.res.BarterResponseBody;
import com.proposta.aceita.crmservice.entities.res.InterestResponseBody;
import com.proposta.aceita.crmservice.entities.res.NegotiationResponseBody;
import com.proposta.aceita.crmservice.entities.res.SaleResponseBody;
import com.proposta.aceita.crmservice.services.integrations.MatchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.proposta.aceita.crmservice.entities.enums.PropertyType.APARTMENT;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class NegotiationServiceTest {

    @MockBean
    private MatchService matchService;

    @MockBean
    private InterestService interestService;

    @MockBean
    private SaleService saleService;

    @MockBean
    private PropertyImageService propertyImageService;

    @MockBean
    private BarterImageService barterImageService;

    private NegotiationService negotiationService;

    @BeforeEach
    void setup() {
        negotiationService = new NegotiationService(matchService, interestService, saleService, propertyImageService, barterImageService);
    }

    @Test
    void getForBuyer() {
        var username = "joao@joao.com";
        var interestId = 214;

        var interest = new Interest(interestId, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

        var interestBody = new InterestResponseBody(321, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        var saleBody = new SaleResponseBody(356, 21, null, null, null, null, null, null, false, false, true, true, null, false, null, false, null, false, null);

        var negotiation = new NegotiationResponseBody("as32d23as", interestBody, saleBody, null);

        when(interestService.getByUser(username)).thenReturn(Optional.of(interest));
        when(matchService.getNegotiationsByInterest(interestId)).thenReturn(Optional.of(List.of(negotiation)));

        negotiationService.getForBuyer(username);

        verify(interestService).getByUser(username);
        verify(matchService).getNegotiationsByInterest(interestId);
        verify(propertyImageService).get(21);
        verify(saleService).getById(356);

        verifyNoMoreInteractions(interestService, matchService, propertyImageService, saleService);
        verifyNoInteractions(barterImageService);
    }

    @Test
    void getForSeller() {
        var username = "joao@joao.com";
        var saleId = 356;

        var property = new Property(21, null, "Descricao", APARTMENT, BigDecimal.valueOf(2133), 232, null, 23423422, 3, 2, 2, true, true, true, false, null, true);
        var sale = new Sale(saleId, property, BigDecimal.valueOf(214.55), true, BigDecimal.valueOf(100), false, null, false, null, LocalDate.of(2020, 10, 20));

        var barter = new BarterResponseBody(213, BarterType.VEHICLE, BigDecimal.TEN);
        var interestBody = new InterestResponseBody(321, null, null, null, null, null, null, null, null, null, null, null, null, null, List.of(barter));
        var saleBody = new SaleResponseBody(saleId, 21, null, null, null, null, null, null, false, false, true, true, null, false, null, false, null, false, null);

        var negotiation = new NegotiationResponseBody("as32d23as", interestBody, saleBody, null);

        when(saleService.getByUser(username)).thenReturn(Optional.of(List.of(sale)));
        when(matchService.getNegotiationsBySale(saleId)).thenReturn(Optional.of(List.of(negotiation)));

        negotiationService.getForSeller(username);

        verify(saleService).getByUser(username);
        verify(matchService).getNegotiationsBySale(saleId);
        verify(barterImageService).get(213);

        verifyNoMoreInteractions(saleService, matchService, barterImageService);
        verifyNoInteractions(interestService, propertyImageService);
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
