package com.proposta.aceita.crmservice.services;

import com.proposta.aceita.crmservice.entities.Barter;
import com.proposta.aceita.crmservice.entities.Interest;
import com.proposta.aceita.crmservice.entities.req.BarterRequestBody;
import com.proposta.aceita.crmservice.entities.req.GarageRequestBody;
import com.proposta.aceita.crmservice.repositories.BarterRepository;
import com.proposta.aceita.crmservice.repositories.InterestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BarterService {

    private final BarterRepository barterRepository;
    private final InterestRepository interestRepository;

    @Autowired
    public BarterService(BarterRepository barterRepository, InterestRepository interestRepository) {
        this.barterRepository = barterRepository;
        this.interestRepository = interestRepository;
    }

    public Optional<Barter> getById(Integer id) {
        return barterRepository.findById(id);
    }

    public List<Barter> getByInterest(Integer interestId) {
        return barterRepository.findByInterestId(interestId);
    }

    @Transactional
    public Optional<List<Barter>> save(List<? extends BarterRequestBody> body, Interest interest) {
        deleteRemoved(body, interest.getId());

        return Optional.ofNullable(body).map(barter -> barterRepository.saveAll(Barter.ofList(barter, interest)));
    }

    public Optional<Barter> save(BarterRequestBody body) {
        final var interest = (body.getInterestId() == null) ? null
                : interestRepository.findById(body.getInterestId()).orElse(null);

        return Optional.of(barterRepository.save(Barter.of(body, interest)));
    }

    private void deleteRemoved(List<? extends BarterRequestBody> body, Integer interestId) {
        final var interestBarters = barterRepository.findByInterestId(interestId);

        if (!CollectionUtils.isEmpty(interestBarters)) {

            final var bartersIds = body.stream()
                    .map(BarterRequestBody::getId)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            interestBarters.stream()
                    .filter(barter -> !bartersIds.contains(barter.getId()))
                    .forEach(barter -> barterRepository.deleteById(barter.getId()));
        }
    }


    public void delete(Integer id) {
        barterRepository.deleteById(id);
    }
}
