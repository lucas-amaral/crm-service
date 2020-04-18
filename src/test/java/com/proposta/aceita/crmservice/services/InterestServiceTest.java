package com.proposta.aceita.crmservice.services;

import com.proposta.aceita.crmservice.entities.*;
import com.proposta.aceita.crmservice.entities.req.EditBarterRequestBody;
import com.proposta.aceita.crmservice.entities.req.EditInterestRequestBody;
import com.proposta.aceita.crmservice.repositories.InterestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.proposta.aceita.crmservice.entities.enums.BarterType.CAR;
import static com.proposta.aceita.crmservice.entities.enums.PropertyType.APARTMENT;
import static com.proposta.aceita.crmservice.entities.enums.PropertyType.HOUSE;
import static com.proposta.aceita.crmservice.entities.enums.Sex.MALE;
import static com.proposta.aceita.crmservice.entities.enums.UserType.FISICAL;
import static org.mockito.Mockito.*;

@SpringBootTest
public class InterestServiceTest {

    @MockBean
    private InterestRepository interestRepository;

    @MockBean
    private UserService userService;

    @MockBean
    private NeighborhoodService neighborhoodService;

    @MockBean
    private BarterService barterService;

    private InterestService interestService;

    @BeforeEach
    public void setup() {
        interestService = new InterestService(interestRepository, userService, neighborhoodService, barterService);
    }

    @Test
    public void getById() {

        var id = 23;

        interestService.getById(id);

        verify(interestRepository).findById(id);
    }

    @Test
    public void save() {

        var barterBody = new EditBarterRequestBody(3, 234, CAR, BigDecimal.valueOf(3432, 2));
        var body = new EditInterestRequestBody(234, "joao@joao.com", BigDecimal.valueOf(121323,2), false, null, List.of(HOUSE, APARTMENT), List.of(2), 3, 1, 3, false, true, true, true, List.of(barterBody));

        var neighborhood = new Neighborhood(2, "Pé de Plátano", null);
        var user = new User("joao@joao.com", "1234","Joao", LocalDate.of(1978, 3, 23),
                FISICAL, "45230929-04", MALE, new Address(), true);
        var interest = new Interest(234, user, BigDecimal.valueOf(121323,2), false, null, null, List.of(neighborhood), 3, 1, 3, false, true, true, true);
        var barter = new Barter(3, interest,  CAR, BigDecimal.valueOf(3432, 2));

        when(userService.getById("joao@joao.com")).thenReturn(Optional.of(user));
        when(neighborhoodService.list(List.of(2))).thenReturn(List.of(neighborhood));
        when(barterService.save(body.getBarters(), interest)).thenReturn(Optional.of(List.of(barter)));
        when(interestRepository.save(interest)).thenReturn(interest);

        interestService.save(body);

        var expected = new Interest(234, user, BigDecimal.valueOf(121323,2), false, null, null, List.of(neighborhood), 3, 1, 3, false, true, true, true);

        verify(interestRepository).save(expected);
        verify(interestRepository).updateTypes(234, "'HOUSE', 'APARTMENT'");

    }

    @Test
    public void saveWithoutNeighborhoods() {

        var barterBody = new EditBarterRequestBody(3, 234, CAR, BigDecimal.valueOf(3432, 2));
        var body = new EditInterestRequestBody(234, "joao@joao.com", BigDecimal.valueOf(121323,2), false, null, List.of(HOUSE, APARTMENT), null, 3, 1, 3, false, true, true, true, List.of(barterBody));

        var user = new User("joao@joao.com", "1234","Joao", LocalDate.of(1978, 3, 23),
                FISICAL, "45230929-04", MALE, new Address(), true);
        var interest = new Interest(234, user, BigDecimal.valueOf(121323,2), false, null, null, Collections.emptyList(), 3, 1, 3, false, true, true, true);
        var barter = new Barter(3, interest,  CAR, BigDecimal.valueOf(3432, 2));

        when(userService.getById("joao@joao.com")).thenReturn(Optional.of(user));
        when(neighborhoodService.list(null)).thenReturn(Collections.emptyList());
        when(barterService.save(body.getBarters(), interest)).thenReturn(Optional.of(List.of(barter)));
        when(interestRepository.save(interest)).thenReturn(interest);

        interestService.save(body);

        var expected = new Interest(234, user, BigDecimal.valueOf(121323,2), false, null, null, Collections.emptyList(), 3, 1, 3, false, true, true, true);

        verify(interestRepository).save(expected);
        verify(interestRepository).updateTypes(234, "'HOUSE', 'APARTMENT'");
    }

    @Test
    public void saveWithoutUser() {

        var barterBody = new EditBarterRequestBody(3, 234, CAR, BigDecimal.valueOf(3432, 2));
        var body = new EditInterestRequestBody(234, "joao@joao.com", BigDecimal.valueOf(121323,2), false, null, List.of(HOUSE, APARTMENT), null, 3, 1, 3, false, true, true, true, List.of(barterBody));

        when(userService.getById("joao@joao.com")).thenReturn(Optional.empty());

        interestService.save(body);

        verifyNoInteractions(neighborhoodService);
        verifyNoInteractions(barterService);
        verifyNoMoreInteractions(interestRepository);
    }

    @Test
    public void saveWithoutBarters() {

        var body = new EditInterestRequestBody(234, "joao@joao.com", BigDecimal.valueOf(121323,2), false, null, List.of(HOUSE, APARTMENT), List.of(2), 3, 1, 3, false, true, true, true, null);

        var neighborhood = new Neighborhood(2, "Pé de Plátano", null);
        var user = new User("joao@joao.com", "1234","Joao", LocalDate.of(1978, 3, 23),
                FISICAL, "45230929-04", MALE, new Address(), true);
        var interest = new Interest(234, user, BigDecimal.valueOf(121323,2), false, null, null, List.of(neighborhood), 3, 1, 3, false, true, true, true);

        when(userService.getById("joao@joao.com")).thenReturn(Optional.of(user));
        when(neighborhoodService.list(List.of(2))).thenReturn(List.of(neighborhood));
        when(barterService.save(body.getBarters(), interest)).thenReturn(Optional.empty());
        when(interestRepository.save(interest)).thenReturn(interest);

        interestService.save(body);

        var expected = new Interest(234, user, BigDecimal.valueOf(121323,2), false, null, null, List.of(neighborhood), 3, 1, 3, false, true, true, true);

        verify(interestRepository).save(expected);
        verify(interestRepository).updateTypes(234, "'HOUSE', 'APARTMENT'");
    }

    @Test
    public void delete() {

        var id = 23;

        interestService.delete(id);

        verify(interestRepository).deleteById(id);
    }

}
