package com.proposta.aceita.crmservice.services;

import com.proposta.aceita.crmservice.entities.Sale;
import com.proposta.aceita.crmservice.entities.req.SaleRequestBody;
import com.proposta.aceita.crmservice.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final PropertyService propertyService;

    @Autowired
    public SaleService(SaleRepository saleRepository, PropertyService propertyService) {
        this.saleRepository = saleRepository;
        this.propertyService = propertyService;
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
                .map(property -> saleRepository.save(Sale.from(body, property)));
    }

    public void delete(Integer id) {
        saleRepository.deleteById(id);
    }
}
