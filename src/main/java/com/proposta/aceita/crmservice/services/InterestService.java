package com.proposta.aceita.crmservice.services;

import com.proposta.aceita.crmservice.entities.Interest;
import com.proposta.aceita.crmservice.entities.req.InterestRequestBody;
import com.proposta.aceita.crmservice.repositories.InterestRepository;
import com.proposta.aceita.crmservice.util.CheckUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class InterestService {

    private final InterestRepository interestRepository;
    private final UserService userService;
    private final NeighborhoodService neighborhoodService;
    private final BarterService barterService;

    @Autowired
    public InterestService(InterestRepository interestRepository, UserService userService, NeighborhoodService neighborhoodService, BarterService barterService) {
        this.interestRepository = interestRepository;
        this.userService = userService;
        this.neighborhoodService = neighborhoodService;
        this.barterService = barterService;
    }

    public Optional<Interest> getById(Integer id) {
        return interestRepository.findById(id);
    }

    public Optional<Interest> getByUser(String username) {
        return interestRepository.findByUserUsername(username);
    }

    @Transactional
    public Optional<Interest> save(InterestRequestBody body) {
        return userService.getById(body.getUsername()).map(user -> {
            var neighborhoods = neighborhoodService.list(body.getNeighborhoodIds());

            var interest = interestRepository.save(Interest.from(body, user, neighborhoods));

            updateTypes(body, interest);

            barterService.save(body.getBarters(), interest).ifPresent(interest::setBarters);

            return interest;
        });
    }

    private void updateTypes(InterestRequestBody body, Interest interest) {
        if (!CheckUtils.listIsNullOrEmpty(body.getTypes())) {
            interestRepository.updateTypes(interest.getId(), body.getStringTypes());
            interest.setTypes(body.getTypes());
        }
    }

    public void delete(Integer id) {
        interestRepository.deleteById(id);
    }
}
