package com.proposta.aceita.crmservice.services;

import com.proposta.aceita.crmservice.entities.Garage;
import com.proposta.aceita.crmservice.entities.Property;
import com.proposta.aceita.crmservice.entities.req.GarageRequestBody;
import com.proposta.aceita.crmservice.repositories.GarageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class GarageService {

    private final GarageRepository garageRepository;

    @Autowired
    public GarageService(GarageRepository garageRepository) {
        this.garageRepository = garageRepository;
    }

    public Optional<Garage> getById(Integer id) {
        return garageRepository.findById(id);
    }

    public List<Garage> getByPropertyId(Integer propertyId) {
        return garageRepository.findByPropertyId(propertyId);
    }

    @Transactional
    public Optional<List<Garage>> save(List<GarageRequestBody> body, Property property) {
        return Optional.ofNullable(body)
                .flatMap(garage -> Optional.ofNullable(property)
                        .map(p -> garageRepository.saveAll(Garage.fromList(garage, p))));
    }

    public void delete(Integer id) {
        garageRepository.deleteById(id);
    }
}
