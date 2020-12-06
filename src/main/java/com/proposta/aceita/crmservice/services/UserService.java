package com.proposta.aceita.crmservice.services;

import com.proposta.aceita.crmservice.entities.Authority;
import com.proposta.aceita.crmservice.entities.User;
import com.proposta.aceita.crmservice.entities.req.AddUserRequestBody;
import com.proposta.aceita.crmservice.entities.req.UserRequestBody;
import com.proposta.aceita.crmservice.exceptions.UserException;
import com.proposta.aceita.crmservice.repositories.AuthorityRepository;
import com.proposta.aceita.crmservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final AddressService addressService;

    @Autowired
    public UserService(UserRepository userRepository, AuthorityRepository authorityRepository, AddressService addressService) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.addressService = addressService;
    }

    public Optional<User> getById(String username) {
        return userRepository.findById(username);
    }

    public List<User> list() {
        return userRepository.findAll();
    }

    @Transactional
    public Optional<User> create(AddUserRequestBody body) {
        getById(body.getUsername()).ifPresent(user -> {
            throw new UserException(String.format("User %s already exist", body.getUsername()));
        });

        return save(body);
    }

    @Transactional
    public Optional<User> save(UserRequestBody body) {

        var address = addressService.save(body.getAddress()).orElse(null);

        var user = userRepository.save(User.of(body, address));

        authorityRepository.save(Authority.ofUser(body.getUsername()));

        return Optional.of(user);
    }

    public void delete(String username) {
        userRepository.deleteById(username);
    }
}
