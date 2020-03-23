package com.proposta.aceita.crmservice.services;

import com.proposta.aceita.crmservice.entities.Street;
import com.proposta.aceita.crmservice.repositories.StreetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StreetService {

    private final StreetRepository streetRepository;

    @Autowired
    public StreetService(StreetRepository streetRepository) {
        this.streetRepository = streetRepository;
    }

    public Optional<Street> getByZipCode(String zipCode) {
        return streetRepository.findById(zipCode);
    }

    public List<Street> getList() {
        return streetRepository.findAll();
    }

    public Street create(Street street) {
        return streetRepository.save(street);
    }

    public void delete(Street street) {
        streetRepository.delete(street);
    }
}
