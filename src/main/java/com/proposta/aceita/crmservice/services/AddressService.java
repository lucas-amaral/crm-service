package com.proposta.aceita.crmservice.services;

import com.proposta.aceita.crmservice.entities.Address;
import com.proposta.aceita.crmservice.entities.req.AddressRequestBody;
import com.proposta.aceita.crmservice.repositories.AddressRepository;
import com.proposta.aceita.crmservice.repositories.StreetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final StreetRepository streetRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository, StreetRepository streetRepository) {
        this.addressRepository = addressRepository;
        this.streetRepository = streetRepository;
    }

    public Optional<Address> getById(Integer id) {
        return addressRepository.findById(id);
    }

    public List<Address> list() {
        return addressRepository.findAll();
    }

    public Optional<Address> save(AddressRequestBody body) {
        return Optional.ofNullable(body)
                .flatMap(a -> streetRepository.findById(a.getStreetId())
                        .map(s -> addressRepository.save(Address.from(a, s))));
    }

    public void delete(Integer id) {
        addressRepository.deleteById(id);
    }
}
