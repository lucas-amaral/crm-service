package com.proposta.aceita.crmservice.services;

import com.proposta.aceita.crmservice.entities.res.NegotiationResponseBody;
import com.proposta.aceita.crmservice.services.integrations.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NegotiationService {

    private final MatchService matchService;

    @Autowired
    public NegotiationService(MatchService matchService) {
        this.matchService = matchService;
    }

    public Optional<List<NegotiationResponseBody>> getByInterest(Integer interestId) {
        return matchService.getNegotiationsByInterest(interestId);
    }

    public Optional<List<NegotiationResponseBody>> getBySale(Integer saleId) {
        return matchService.getNegotiationsBySale(saleId);
    }

    public void deleteById(String id) {
        matchService.deleteNegotiation(id);
    }

    public void approvedBySeller(String id) {
        matchService.approvedNegotiationBySeller(id);
    }

    public void approvedByBuyer(String id) {
        matchService.approvedNegotiationByBuyer(id);
    }

    public void reprovedBySeller(String id) {
        matchService.reprovedNegotiationBySeller(id);
    }

    public void reprovedByBuyer(String id) {
        matchService.reprovedNegotiationByBuyer(id);
    }

}
