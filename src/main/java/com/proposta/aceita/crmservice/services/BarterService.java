package com.proposta.aceita.crmservice.services;

import com.proposta.aceita.crmservice.entities.Barter;
import com.proposta.aceita.crmservice.entities.Interest;
import com.proposta.aceita.crmservice.entities.req.BarterRequestBody;
import com.proposta.aceita.crmservice.repositories.BarterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BarterService {

    private final BarterRepository barterRepository;

    @Autowired
    public BarterService(BarterRepository barterRepository) {
        this.barterRepository = barterRepository;
    }

    public Optional<Barter> getById(Integer id) {
        return barterRepository.findById(id);
    }

    public List<Barter> getByInterest(Integer interestId) {
        return barterRepository.findByInterestId(interestId);
    }

    @Transactional
    public Optional<List<Barter>> save(List<BarterRequestBody> body, Interest interest) {
        return Optional.ofNullable(body).map(b -> barterRepository.saveAll(Barter.fromList(b, interest)));
    }

    public void delete(Integer id) {
        barterRepository.deleteById(id);
    }
}
