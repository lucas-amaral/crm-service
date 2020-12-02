package com.proposta.aceita.crmservice.services;

import com.proposta.aceita.crmservice.entities.Sale;
import com.proposta.aceita.crmservice.entities.req.SaleRequestBody;
import com.proposta.aceita.crmservice.exceptions.InterestException;
import com.proposta.aceita.crmservice.repositories.SaleRepository;
import com.proposta.aceita.crmservice.services.integrations.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final PropertyService propertyService;
    private final MatchService matchService;

    @Autowired
    public SaleService(SaleRepository saleRepository, PropertyService propertyService, MatchService matchService) {
        this.saleRepository = saleRepository;
        this.propertyService = propertyService;
        this.matchService = matchService;
    }

    public Optional<Sale> getById(Integer id) {
        return saleRepository.findById(id);
    }

    public Optional<Sale> getByProperty(Integer propertyId) {
        return saleRepository.findByPropertyId(propertyId);
    }

    public Optional<List<Sale>> getByUser(String username) {
        return Optional.of(propertyService.getByUser(username).stream()
                .map(property -> getByProperty(property.getId()).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList()));
    }

    @Transactional
    public Optional<Sale> save(SaleRequestBody body) {
        return propertyService.getById(body.getPropertyId())
                .map(property -> {
                    checkIfAlreadyExistOtherSaleForProperty(property.getId(), body.getId());

                    var sale = saleRepository.save(Sale.of(body, property));

                    matchService.saveSale(sale);

                    return sale;
                });
    }

    private void checkIfAlreadyExistOtherSaleForProperty(Integer propertyId, Integer saleId) {
        if (saleId == null) {
            saleRepository.findByPropertyId(propertyId)
                    .ifPresent(sale -> {
                        var message = String.format("Property %s already have sale", propertyId);

                        throw new InterestException(message);
                    });
        }
    }

    public void delete(Integer id) {
        saleRepository.deleteById(id);
        matchService.deleteSale(id);
    }
}
