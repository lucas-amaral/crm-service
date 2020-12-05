package com.proposta.aceita.crmservice.services;

import com.proposta.aceita.crmservice.entities.PropertyImage;
import com.proposta.aceita.crmservice.entities.res.BarterResponseBody;
import com.proposta.aceita.crmservice.entities.res.NegotiationResponseBody;
import com.proposta.aceita.crmservice.entities.res.SaleResponseBody;
import com.proposta.aceita.crmservice.services.integrations.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NegotiationService {

    private final MatchService matchService;
    private final InterestService interestService;
    private final SaleService saleService;
    private final PropertyImageService propertyImageService;
    private final BarterImageService barterImageService;

    @Autowired
    public NegotiationService(MatchService matchService,
                              InterestService interestService,
                              SaleService saleService, PropertyImageService propertyImageService, BarterImageService barterImageService) {
        this.matchService = matchService;
        this.interestService = interestService;
        this.saleService = saleService;
        this.propertyImageService = propertyImageService;
        this.barterImageService = barterImageService;
    }

    public Optional<List<NegotiationResponseBody>> getForBuyer(String username) {
        return interestService.getByUser(username).map(interest -> {
            final var negotiations = matchService.getNegotiationsByInterest(interest.getId())
                    .orElse(Collections.emptyList());

            negotiations.forEach(negotiation -> {
                negotiation.getSale().setImages(getPropertyImages(negotiation.getSale()));
                negotiation.getSale().setDescription(getPropertyDescription(negotiation.getSale().getId()));
            });

            return negotiations;
        });
    }

    private List<PropertyImage> getPropertyImages(SaleResponseBody sale) {
        return propertyImageService.get(sale.getPropertyId()).orElse(Collections.emptyList());
    }

    private String getPropertyDescription(Integer saleId) {
        return saleService.getById(saleId).map(sale -> sale.getProperty().getDescription()).orElse(null);
    }

    public Optional<List<NegotiationResponseBody>> getForSeller(String username) {
        return saleService.getByUser(username).map(sales -> sales.stream().map(sale -> {
            final var negotiations = matchService.getNegotiationsBySale(sale.getId())
                    .orElse(Collections.emptyList());

            negotiations.forEach(negotiation -> negotiation
                    .getInterest()
                    .getBarters()
                    .forEach(this::setBarterImages));

            return negotiations;
        }).flatMap(Collection::stream).collect(Collectors.toList()));
    }

    private void setBarterImages(BarterResponseBody barter) {
        barter.setImages(barterImageService.get(barter.getId()).orElse(Collections.emptyList()));
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
