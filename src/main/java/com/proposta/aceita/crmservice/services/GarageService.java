package com.proposta.aceita.crmservice.services;

import com.proposta.aceita.crmservice.entities.Garage;
import com.proposta.aceita.crmservice.entities.Property;
import com.proposta.aceita.crmservice.entities.req.GarageRequestBody;
import com.proposta.aceita.crmservice.repositories.GarageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
        deleteRemoved(body, property.getId());

        return Optional.of(body)
                .flatMap(garage -> Optional.of(property)
                        .map(p -> garageRepository.saveAll(Garage.ofList(garage, p))));
    }

    private void deleteRemoved(List<GarageRequestBody> body, Integer propertyId) {
        final var propertyGarages = garageRepository.findByPropertyId(propertyId);

        if (!CollectionUtils.isEmpty(propertyGarages)) {

            final var bodyGarageIds = body.stream()
                    .map(GarageRequestBody::getId)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            propertyGarages.stream()
                    .filter(garage -> !bodyGarageIds.contains(garage.getId()))
                    .forEach(garage -> deleteRemoved(garage.getId()));
        }
    }

    public void deleteRemoved(Integer id) {
        garageRepository.deleteById(id);
    }
}
