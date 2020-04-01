package com.proposta.aceita.crmservice.services;

import com.proposta.aceita.crmservice.entities.User;
import com.proposta.aceita.crmservice.entities.req.UserRequestBody;
import com.proposta.aceita.crmservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AddressService addressService;

    @Autowired
    public UserService(UserRepository userRepository, AddressService addressService) {
        this.userRepository = userRepository;
        this.addressService = addressService;
    }

    public Optional<User> getById(Integer id) {
        return userRepository.findById(id);
    }

    public List<User> list() {
        return userRepository.findAll();
    }

    @Transactional
    public Optional<User> save(UserRequestBody body) {
        var address = addressService.save(body.getAddress()).orElse(null);
        return Optional.of(userRepository.save(User.from(body, address)));
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
}
