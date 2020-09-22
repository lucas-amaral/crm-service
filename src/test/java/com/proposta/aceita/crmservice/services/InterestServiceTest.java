package com.proposta.aceita.crmservice.services;

import com.proposta.aceita.crmservice.entities.*;
import com.proposta.aceita.crmservice.entities.req.EditBarterRequestBody;
import com.proposta.aceita.crmservice.entities.req.EditInterestRequestBody;
import com.proposta.aceita.crmservice.entities.res.UserResponseBody;
import com.proposta.aceita.crmservice.exceptions.InterestException;
import com.proposta.aceita.crmservice.repositories.InterestRepository;
import com.proposta.aceita.crmservice.services.integrations.MatchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.proposta.aceita.crmservice.entities.enums.BarterType.VEHICLE;
import static com.proposta.aceita.crmservice.entities.enums.PropertyType.APARTMENT;
import static com.proposta.aceita.crmservice.entities.enums.PropertyType.HOUSE;
import static com.proposta.aceita.crmservice.entities.enums.Sex.MALE;
import static com.proposta.aceita.crmservice.entities.enums.UserType.FISICAL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class InterestServiceTest {

    @MockBean
    private InterestRepository interestRepository;

    @MockBean
    private UserService userService;

    @MockBean
    private NeighborhoodService neighborhoodService;

    @MockBean
    private BarterService barterService;

    @MockBean
    private MatchService matchService;

    private InterestService interestService;

    @BeforeEach
    void setup() {
        interestService = new InterestService(interestRepository, userService, neighborhoodService, barterService, matchService);
    }

    @Test
    void getById() {

        var id = 23;

        interestService.getById(id);

        verify(interestRepository).findById(id);
    }

    @Test
    void getUserById() {

        var id = 23;

        var user = new User("joao@joao.com", "1234","Joao", LocalDate.of(1978, 3, 23),
                FISICAL, "45230929-04", MALE, new Address(), true);
        var interest = new Interest(id, user, BigDecimal.valueOf(121323,2), false, null, null, null, 3, 1, 3, 1, false, true, true, true);

        when(interestRepository.findById(id)).thenReturn(Optional.of(interest));

        var expected = new UserResponseBody("Joao", "joao@joao.com");

        assertThat(interestService.getUserById(id)).isNotEmpty().hasValue(expected);

        verify(interestRepository).findById(id);
    }

    @Test
    void getByUser() {

        var username = "joao@joao.com";

        interestService.getByUser(username);

        verify(interestRepository).findByUserUsername(username);
    }

    @Test
    void create() {

        var barterBody = new EditBarterRequestBody(null, 234, VEHICLE, BigDecimal.valueOf(3432, 2));
        var body = new EditInterestRequestBody(null, "joao@joao.com", BigDecimal.valueOf(121323,2), false, null, List.of(HOUSE, APARTMENT), List.of(2), 3, 1, 3, 1, false, true, true, true, List.of(barterBody));

        var neighborhood = new Neighborhood(2, "Pé de Plátano", null);
        var user = new User("joao@joao.com", "1234","Joao", LocalDate.of(1978, 3, 23),
                FISICAL, "45230929-04", MALE, new Address(), true);
        var interest = new Interest(null, user, BigDecimal.valueOf(121323,2), false, null, null, List.of(neighborhood), 3, 1, 3, 1, false, true, true, true);
        var barter = new Barter(null, interest,  VEHICLE, BigDecimal.valueOf(3432, 2));

        when(userService.getById("joao@joao.com")).thenReturn(Optional.of(user));
        when(neighborhoodService.list(List.of(2))).thenReturn(List.of(neighborhood));
        when(barterService.save(body.getBarters(), interest)).thenReturn(Optional.of(List.of(barter)));
        when(interestRepository.save(interest)).thenReturn(interest);
        when(interestRepository.findByUserUsername(user.getUsername())).thenReturn(Optional.empty());

        interestService.create(body);

        verify(interestRepository).save(any());
        verify(interestRepository).updateTypes(any(),any());
        verify(matchService).saveInterest(interest);
    }

    @Test
    void createThrowExceptionWhenUserAlreadyHaveInterest() {

        var barterBody = new EditBarterRequestBody(null, 234, VEHICLE, BigDecimal.valueOf(3432, 2));
        var body = new EditInterestRequestBody(null, "joao@joao.com", BigDecimal.valueOf(121323,2), false, null, List.of(HOUSE, APARTMENT), List.of(2), 3, 1, 3, 1, false, true, true, true, List.of(barterBody));

        var neighborhood = new Neighborhood(2, "Pé de Plátano", null);
        var user = new User("joao@joao.com", "1234","Joao", LocalDate.of(1978, 3, 23),
                FISICAL, "45230929-04", MALE, new Address(), true);

        var currentInterest = new Interest(21413, user, BigDecimal.valueOf(1343,2), true, null, null, List.of(neighborhood), 2, 2, 3, 1, false, true, true, true);

        when(userService.getById("joao@joao.com")).thenReturn(Optional.of(user));
        when(interestRepository.findByUserUsername(user.getUsername())).thenReturn(Optional.of(currentInterest));

        assertThatExceptionOfType(InterestException.class)
                .isThrownBy(() -> interestService.create(body))
                .matches(e -> e.getMessage().equals("User joao@joao.com already have interest: Interest[id=21413, user=User[username='joao@joao.com', name='Joao', dateOfBirth=1978-03-23, type=FISICAL, cpfCnpj='45230929-04', sex=MALE, address=Address[id=null, street=null, number='null', complement='null'], enabled=true, createdAt=null], value=13.43, financing=true, financingValue=null, types=null, neighborhoods=[Neighborhood[id=2, name='Pé de Plátano', city=null]], dorms=2, suites=2, bathrooms=3, garages=null, pool=false, balcony=true, elevator=true, barbecueGrill=true, createdAt=null]"));


        verify(interestRepository).findByUserUsername(user.getUsername());

        verifyNoMoreInteractions(interestRepository);
        verifyNoInteractions(neighborhoodService);
        verifyNoInteractions(matchService);
    }

    @Test
    void update() {

        var barterBody = new EditBarterRequestBody(3, 234, VEHICLE, BigDecimal.valueOf(3432, 2));
        var body = new EditInterestRequestBody(234, "joao@joao.com", BigDecimal.valueOf(121323,2), false, null, List.of(HOUSE, APARTMENT), List.of(2), 3, 1, 3, 1, false, true, true, true, List.of(barterBody));

        var neighborhood = new Neighborhood(2, "Pé de Plátano", null);
        var user = new User("joao@joao.com", "1234","Joao", LocalDate.of(1978, 3, 23),
                FISICAL, "45230929-04", MALE, new Address(), true);
        var interest = new Interest(234, user, BigDecimal.valueOf(121323,2), false, null, null, List.of(neighborhood), 3, 1, 3, 1, false, true, true, true);
        var barter = new Barter(3, interest,  VEHICLE, BigDecimal.valueOf(3432, 2));

        when(userService.getById("joao@joao.com")).thenReturn(Optional.of(user));
        when(neighborhoodService.list(List.of(2))).thenReturn(List.of(neighborhood));
        when(barterService.save(body.getBarters(), interest)).thenReturn(Optional.of(List.of(barter)));
        when(interestRepository.save(interest)).thenReturn(interest);

        interestService.update(body);

        var expected = new Interest(234, user, BigDecimal.valueOf(121323,2), false, null, null, List.of(neighborhood), 3, 1, 3, 1, false, true, true, true);

        verify(interestRepository).save(expected);
        verify(interestRepository).updateTypes(234, "'HOUSE', 'APARTMENT'");
        verify(matchService).saveInterest(interest);
    }

    @Test
    void updateWithoutNeighborhoods() {

        var barterBody = new EditBarterRequestBody(3, 234, VEHICLE, BigDecimal.valueOf(3432, 2));
        var body = new EditInterestRequestBody(234, "joao@joao.com", BigDecimal.valueOf(121323,2), false, null, List.of(HOUSE, APARTMENT), null, 3, 1, 3, 1, false, true, true, true, List.of(barterBody));

        var user = new User("joao@joao.com", "1234","Joao", LocalDate.of(1978, 3, 23),
                FISICAL, "45230929-04", MALE, new Address(), true);
        var interest = new Interest(234, user, BigDecimal.valueOf(121323,2), false, null, null, Collections.emptyList(), 3, 1, 3, 1, false, true, true, true);
        var barter = new Barter(3, interest,  VEHICLE, BigDecimal.valueOf(3432, 2));

        when(userService.getById("joao@joao.com")).thenReturn(Optional.of(user));
        when(neighborhoodService.list(null)).thenReturn(Collections.emptyList());
        when(barterService.save(body.getBarters(), interest)).thenReturn(Optional.of(List.of(barter)));
        when(interestRepository.save(interest)).thenReturn(interest);

        interestService.update(body);

        var expected = new Interest(234, user, BigDecimal.valueOf(121323,2), false, null, null, Collections.emptyList(), 3, 1, 3, 1, false, true, true, true);

        verify(interestRepository).save(expected);
        verify(interestRepository).updateTypes(234, "'HOUSE', 'APARTMENT'");
    }

    @Test
    void updateWithoutUser() {

        var barterBody = new EditBarterRequestBody(3, 234, VEHICLE, BigDecimal.valueOf(3432, 2));
        var body = new EditInterestRequestBody(234, "joao@joao.com", BigDecimal.valueOf(121323,2), false, null, List.of(HOUSE, APARTMENT), null, 3, 1, 3, 1, false, true, true, true, List.of(barterBody));

        when(userService.getById("joao@joao.com")).thenReturn(Optional.empty());

        interestService.update(body);

        verifyNoInteractions(neighborhoodService);
        verifyNoInteractions(barterService);
        verifyNoMoreInteractions(interestRepository);
    }

    @Test
    void updateWithoutBarters() {

        var body = new EditInterestRequestBody(234, "joao@joao.com", BigDecimal.valueOf(121323,2), false, null, List.of(HOUSE, APARTMENT), List.of(2), 3, 1, 3, 1, false, true, true, true, null);

        var neighborhood = new Neighborhood(2, "Pé de Plátano", null);
        var user = new User("joao@joao.com", "1234","Joao", LocalDate.of(1978, 3, 23),
                FISICAL, "45230929-04", MALE, new Address(), true);
        var interest = new Interest(234, user, BigDecimal.valueOf(121323,2), false, null, null, List.of(neighborhood), 3, 1, 3, 1, false, true, true, true);

        when(userService.getById("joao@joao.com")).thenReturn(Optional.of(user));
        when(neighborhoodService.list(List.of(2))).thenReturn(List.of(neighborhood));
        when(barterService.save(body.getBarters(), interest)).thenReturn(Optional.empty());
        when(interestRepository.save(interest)).thenReturn(interest);

        interestService.update(body);

        var expected = new Interest(234, user, BigDecimal.valueOf(121323,2), false, null, null, List.of(neighborhood), 3, 1, 3, 1, false, true, true, true);

        verify(interestRepository).save(expected);
        verify(interestRepository).updateTypes(234, "'HOUSE', 'APARTMENT'");
    }

    @Test
    void delete() {

        var id = 23;

        interestService.delete(id);

        verify(interestRepository).deleteById(id);
    }

}
