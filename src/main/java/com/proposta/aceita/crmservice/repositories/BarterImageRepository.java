package com.proposta.aceita.crmservice.repositories;

import com.proposta.aceita.crmservice.entities.BarterImage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface BarterImageRepository extends MongoRepository<BarterImage, String> {
    Optional<List<BarterImage>> findByBarterId(Integer barterId);
}