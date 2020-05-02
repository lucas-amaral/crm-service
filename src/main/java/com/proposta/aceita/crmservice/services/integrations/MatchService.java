package com.proposta.aceita.crmservice.services.integrations;

import com.proposta.aceita.crmservice.entities.Interest;
import com.proposta.aceita.crmservice.entities.Sale;
import com.proposta.aceita.crmservice.entities.req.intergrations.InterestRequestBody;
import com.proposta.aceita.crmservice.entities.req.intergrations.SaleRequestBody;
import com.proposta.aceita.crmservice.entities.res.NegotiationResponseBody;
import com.proposta.aceita.crmservice.services.integrations.clients.MatchClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
public class MatchService extends IntegrationService {

    @Value("${services.match.username}")
    private String username;

    @Value("${services.match.password}")
    private String password;

    private final MatchClient matchClient;

    private String authHeader;

    public MatchService(MatchClient matchClient) {
        this.matchClient = matchClient;
    }

    @PostConstruct
    void postConstruct() {
        final String auth = username + ":" + password;
        authHeader = "Basic " + Base64Utils.encodeToString(auth.getBytes());
    }

    public void saveInterest(Interest interest) {
        matchClient.saveInterest(authHeader, InterestRequestBody.of(interest));
    }

    public void deleteInterest(Integer interestId) {
        matchClient.deleteInterest(authHeader, interestId);
    }

    public void saveSale(Sale sale) {
        matchClient.saveSale(authHeader, SaleRequestBody.of(sale));
    }

    public void deleteSale(Integer saleId) {
        matchClient.deleteSale(authHeader, saleId);
    }

    public Optional<List<NegotiationResponseBody>> getNegotiationsBySale(Integer saleId) {
        return matchClient.getNegotiationsBySale(authHeader, saleId);
    }

    public Optional<List<NegotiationResponseBody>> getNegotiationsByInterest(Integer interestId) {
        return matchClient.getNegotiationsByInterest(authHeader, interestId);
    }

    public void approvedNegotiationBySeller(String negotiationId) {
        matchClient.approvedNegotiationBySeller(authHeader, negotiationId);
    }

    public void approvedNegotiationByBuyer(String negotiationId) {
        matchClient.approvedNegotiationByBuyer(authHeader, negotiationId);
    }

    public void reprovedNegotiationBySeller(String negotiationId) {
        matchClient.reprovedNegotiationBySeller(authHeader, negotiationId);
    }

    public void reprovedNegotiationByBuyer(String negotiationId) {
        matchClient.reprovedNegotiationByBuyer(authHeader, negotiationId);
    }

    public void deleteNegotiation(String negotiationId) {
        matchClient.deleteNegotiation(authHeader, negotiationId);
    }
}