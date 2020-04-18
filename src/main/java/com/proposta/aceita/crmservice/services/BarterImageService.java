package com.proposta.aceita.crmservice.services;

import com.proposta.aceita.crmservice.entities.BarterImage;
import com.proposta.aceita.crmservice.repositories.BarterImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BarterImageService {

    private final BarterImageRepository barterImageRepository;
    
    @Autowired
    public BarterImageService(BarterImageRepository barterImageRepository) {
        this.barterImageRepository = barterImageRepository;
    }

    public Optional<List<BarterImage>> get(Integer barterId) {
        return barterImageRepository.findByBarterId(barterId);
    }

    public Optional<List<BarterImage>> save(List<BarterImage> barterImages) {
        return Optional.of(barterImageRepository.saveAll(barterImages));
    }

    public void delete(String id) {
        barterImageRepository.deleteById(id);
    }
}
