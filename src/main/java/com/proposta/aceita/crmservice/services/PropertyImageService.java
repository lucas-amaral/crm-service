package com.proposta.aceita.crmservice.services;

import com.proposta.aceita.crmservice.entities.PropertyImage;
import com.proposta.aceita.crmservice.repositories.PropertyImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyImageService {

    private final PropertyImageRepository propertyImageRepository;
    
    @Autowired
    public PropertyImageService(PropertyImageRepository propertyImageRepository) {
        this.propertyImageRepository = propertyImageRepository;
    }

    public Optional<List<PropertyImage>> get(Integer propertyId) {
        return propertyImageRepository.findByPropertyId(propertyId);
    }

    public Optional<List<PropertyImage>> save(List<PropertyImage> propertyImages) {
       return Optional.of(propertyImageRepository.saveAll(propertyImages));
    }

    public void delete(String id) {
        propertyImageRepository.deleteById(id);
    }
}
