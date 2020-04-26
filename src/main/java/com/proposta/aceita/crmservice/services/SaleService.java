package com.proposta.aceita.crmservice.services;

import com.proposta.aceita.crmservice.entities.Sale;
import com.proposta.aceita.crmservice.entities.req.SaleRequestBody;
import com.proposta.aceita.crmservice.repositories.SaleRepository;
import com.proposta.aceita.crmservice.services.integrations.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    @Transactional
    public Optional<Sale> save(SaleRequestBody body) {
        return propertyService.getById(body.getPropertyId())
                .map(property -> {
                    var sale = saleRepository.save(Sale.of(body, property));

                    matchService.saveSale(sale);

                    return sale;
                });
    }

    public void delete(Integer id) {
        saleRepository.deleteById(id);
        matchService.deleteSale(id);
    }
}
