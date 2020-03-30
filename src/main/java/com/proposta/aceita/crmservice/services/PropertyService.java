package com.proposta.aceita.crmservice.services;

import com.proposta.aceita.crmservice.entities.Address;
import com.proposta.aceita.crmservice.entities.Property;
import com.proposta.aceita.crmservice.entities.req.PropertyRequestBody;
import com.proposta.aceita.crmservice.repositories.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final UserService userService;
    private final AddressService addressService;
    private final GarageService garageService;

    @Autowired
    public PropertyService(PropertyRepository propertyRepository, UserService userService, AddressService addressService, GarageService garageService) {
        this.propertyRepository = propertyRepository;
        this.userService = userService;
        this.addressService = addressService;
        this.garageService = garageService;
    }

    public Optional<Property> getById(Integer id) {
        return propertyRepository.findById(id);
    }

    public List<Property> list() {
        return propertyRepository.findAll();
    }

    @Transactional
    public Optional<Property> save(PropertyRequestBody body) {
        return userService.getById(body.getUserId()).map(u -> {
            Address address = addressService.save(body.getAddress()).orElse(null);

            Property property = propertyRepository.save(Property.from(body, u, address));

            garageService.save(body.getGarages(), property).ifPresent(property::setGarages);

            return property;
        });
    }

    public void delete(Integer id) {
        propertyRepository.deleteById(id);
    }
}
