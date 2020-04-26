package com.proposta.aceita.crmservice.services;

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

    public List<Property> getByUser(String username) {
        return propertyRepository.findByUserUsername(username);
    }

    @Transactional
    public Optional<Property> save(PropertyRequestBody body) {
        return userService.getById(body.getUsername()).map(user -> {
            var address = addressService.save(body.getAddress()).orElse(null);

            var property = propertyRepository.save(Property.of(body, user, address));

            garageService.save(body.getGarages(), property).ifPresent(property::setGarages);

            return property;
        });
    }

    public void delete(Integer id) {
        propertyRepository.deleteById(id);
    }
}
