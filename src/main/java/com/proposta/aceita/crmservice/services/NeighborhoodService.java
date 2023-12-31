package com.proposta.aceita.crmservice.services;

import com.proposta.aceita.crmservice.entities.Neighborhood;
import com.proposta.aceita.crmservice.repositories.NeighborhoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NeighborhoodService {

    private final NeighborhoodRepository neighborhoodRepository;

    @Autowired
    public NeighborhoodService(NeighborhoodRepository neighborhoodRepository) {
        this.neighborhoodRepository = neighborhoodRepository;
    }

    public Optional<Neighborhood> getById(Integer id) {
        return neighborhoodRepository.findById(id);
    }

    public List<Neighborhood> getByCity(Integer cityId) {
        return neighborhoodRepository.findByCityId(cityId);
    }

    public List<Neighborhood> list(List<Integer> ids) {
        return neighborhoodRepository.findAllById(ids);
    }
}
