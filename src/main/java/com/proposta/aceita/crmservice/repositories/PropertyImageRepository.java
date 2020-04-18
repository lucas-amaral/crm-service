package com.proposta.aceita.crmservice.repositories;

import com.proposta.aceita.crmservice.entities.PropertyImage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PropertyImageRepository extends MongoRepository<PropertyImage, String> {
    Optional<List<PropertyImage>> findByPropertyId(Integer propertyId);
}