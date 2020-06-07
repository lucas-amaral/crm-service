package com.proposta.aceita.crmservice.services;

import com.proposta.aceita.crmservice.entities.*;
import com.proposta.aceita.crmservice.entities.req.EditAddressRequestBody;
import com.proposta.aceita.crmservice.entities.req.EditPropertyRequestBody;
import com.proposta.aceita.crmservice.entities.req.GarageRequestBody;
import com.proposta.aceita.crmservice.entities.res.UserResponseBody;
import com.proposta.aceita.crmservice.repositories.PropertyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.proposta.aceita.crmservice.entities.enums.PropertyType.APARTMENT;
import static com.proposta.aceita.crmservice.entities.enums.Sex.MALE;
import static com.proposta.aceita.crmservice.entities.enums.UserType.FISICAL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class PropertyServiceTest {

    @MockBean
    private PropertyRepository propertyRepository;

    @MockBean
    private UserService userService;

    @MockBean
    private AddressService addressService;

    @MockBean
    private GarageService garageService;

    private PropertyService propertyService;

    @BeforeEach
    void setup() {
        propertyService = new PropertyService(propertyRepository, userService, addressService, garageService);
    }
    
    @Test
    void getById() {

        var id = 23;

        propertyService.getById(id);

        verify(propertyRepository).findById(id);
    }

    @Test
    void getUserById() {

        var id = 23;

        var address = new Address(id, new Street(), "403B", "Ap 203");
        var user = new User("joao@joao.com", "1234","Joao", LocalDate.of(1978, 3, 23),
                FISICAL, "45230929-04", MALE, new Address(), true);
        var property = new Property(234, user, "Descricao", APARTMENT, BigDecimal.valueOf(2133), 232, address, 23423422, 3, 2, 2, true, true, true, false, null, true);

        when(propertyRepository.findById(id)).thenReturn(Optional.of(property));

        var expected = new UserResponseBody("Joao", "joao@joao.com");

        assertThat(propertyService.getUserById(id)).isNotEmpty().hasValue(expected);

        verify(propertyRepository).findById(id);
    }

    @Test
    void getByUser() {

        propertyService.getByUser("email@test.com");

        verify(propertyRepository).findByUserUsername("email@test.com");
    }

    @Test
    void save() {

        var addressBody = new EditAddressRequestBody(56, "95020-320", "212", "Perto do tio joão");
        var garageBody = new GarageRequestBody(3, 20, "snajknjkasnsa");
        var body = new EditPropertyRequestBody(234, "joao@joao.com", "Descricao", APARTMENT, BigDecimal.valueOf(2133), 232, addressBody, 23423422, 3, 2, 2, true, true, true, false, List.of(garageBody), true);

        var address = new Address(56, new Street(), "403B", "Ap 203");
        var user = new User("joao@joao.com", "1234","Joao", LocalDate.of(1978, 3, 23),
                FISICAL, "45230929-04", MALE, new Address(), true);
        var property = new Property(234, user, "Descricao", APARTMENT, BigDecimal.valueOf(2133), 232, address, 23423422, 3, 2, 2, true, true, true, false, null, true);
        var garage = new Garage(3, property, 20, "snajknjkasnsa");

        when(userService.getById("joao@joao.com")).thenReturn(Optional.of(user));
        when(addressService.save(addressBody)).thenReturn(Optional.of(address));
        when(garageService.save(body.getGarages(), property)).thenReturn(Optional.of(List.of(garage)));
        when(propertyRepository.save(property)).thenReturn(property);

        propertyService.save(body);

        verify(propertyRepository).save(property);
    }

    @Test
    void saveWithoutAddress() {

        var garageBody = new GarageRequestBody(3, 20, "snajknjkasnsa");
        var body = new EditPropertyRequestBody(234, "joao@joao.com", "Descricao", APARTMENT, BigDecimal.valueOf(2133), 232, null, 23423422, 3, 2, 2, true, true, true, false, List.of(garageBody), true);

        var user = new User("joao@joao.com", "1234","Joao", LocalDate.of(1978, 3, 23),
                FISICAL, "45230929-04", MALE, new Address(), true);
        var property = new Property(234, user, "Descricao", APARTMENT, BigDecimal.valueOf(2133), 232, null, 23423422, 3, 2, 2, true, true, true, false, null, true);
        var garage = new Garage(3, property, 20, "snajknjkasnsa");

        when(userService.getById("joao@joao.com")).thenReturn(Optional.of(user));
        when(addressService.save(null)).thenReturn(Optional.empty());
        when(garageService.save(body.getGarages(), property)).thenReturn(Optional.of(List.of(garage)));
        when(propertyRepository.save(property)).thenReturn(property);

        propertyService.save(body);

        verify(propertyRepository).save(property);
    }

    @Test
    void saveWithoutUser() {

        var addressBody = new EditAddressRequestBody(56, "95020-320", "212", "Perto do tio joão");
        var garageBody = new GarageRequestBody(3, 20, "snajknjkasnsa");
        var body = new EditPropertyRequestBody(234, "joao@joao.com", "Descricao", APARTMENT, BigDecimal.valueOf(2133), 232, addressBody, 23423422, 3, 2, 2, true, true, true, false, List.of(garageBody), true);

        when(userService.getById("joao@joao.com")).thenReturn(Optional.empty());

        propertyService.save(body);

        verifyNoInteractions(addressService);
        verifyNoInteractions(garageService);
        verifyNoMoreInteractions(propertyRepository);
    }

    @Test
    void saveWithoutGarages() {

        var addressBody = new EditAddressRequestBody(56, "95020-320", "212", "Perto do tio joão");
        var body = new EditPropertyRequestBody(234, "joao@joao.com", "Descricao", APARTMENT, BigDecimal.valueOf(2133), 232, addressBody, 23423422, 3, 2, 2, true, true, true, false, null, true);

        var address = new Address(56, new Street(), "403B", "Ap 203");
        var user = new User("joao@joao.com", "1234","Joao", LocalDate.of(1978, 3, 23),
                FISICAL, "45230929-04", MALE, new Address(), true);
        var property = new Property(234, user, "Descricao", APARTMENT, BigDecimal.valueOf(2133), 232, address, 23423422, 3, 2, 2, true, true, true, false, null, true);

        when(userService.getById("joao@joao.com")).thenReturn(Optional.of(user));
        when(addressService.save(addressBody)).thenReturn(Optional.of(address));
        when(propertyRepository.save(property)).thenReturn(property);

        propertyService.save(body);

        verify(propertyRepository).save(property);
    }

    @Test
    void delete() {

        var id = 23;

        propertyService.delete(id);

        verify(propertyRepository).deleteById(id);
    }

}
