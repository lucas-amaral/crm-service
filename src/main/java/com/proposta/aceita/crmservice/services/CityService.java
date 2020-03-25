package com.proposta.aceita.crmservice.services;

import com.proposta.aceita.crmservice.entities.City;
import com.proposta.aceita.crmservice.entities.req.CityRequestBody;
import com.proposta.aceita.crmservice.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public Optional<City> getById(Integer id) {
        return cityRepository.findById(id);
    }

    public List<City> getList() {
        return cityRepository.findAll();
    }

    public void create(CityRequestBody body) {
        cityRepository.save(City.from(body));
    }

    public void update(CityRequestBody body) {
        cityRepository.update(body.getId(), body.getName(), body.getState());
    }

    public void delete(Integer id) {
        cityRepository.deleteById(id);
    }
}
