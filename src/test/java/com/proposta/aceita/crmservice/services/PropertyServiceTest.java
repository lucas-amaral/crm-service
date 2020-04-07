package com.proposta.aceita.crmservice.services;

import com.proposta.aceita.crmservice.entities.*;
import com.proposta.aceita.crmservice.entities.req.*;
import com.proposta.aceita.crmservice.repositories.PropertyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.proposta.aceita.crmservice.entities.enums.PropertyType.APARTMENT;
import static com.proposta.aceita.crmservice.entities.enums.Sex.MALE;
import static com.proposta.aceita.crmservice.entities.enums.UserType.FISICAL;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PropertyServiceTest {

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
    public void setup() {
        propertyService = new PropertyService(propertyRepository, userService, addressService, garageService);
    }
    
    @Test
    public void getById() {

        var id = 23;

        propertyService.getById(id);

        verify(propertyRepository).findById(id);
    }

    @Test
    public void getByUser() {

        propertyService.getByUser("email@test.com");

        verify(propertyRepository).findByUserUsername("email@test.com");
    }

    @Test
    public void save() {

        var addressBody = new EditAddressRequestBody(56, "95020-320", "212", "Perto do tio joão");
        var garageBody = new GarageRequestBody(3, 20, "snajknjkasnsa");
        var body = new EditPropertyRequestBody(234, "joao@joao.com", "Descricao", APARTMENT, BigDecimal.valueOf(2133), 232, addressBody, 23423422, 3, 2, 2, true, true, true, false, List.of(garageBody), true, null);

        var address = new Address(56, new Street(), "403B", "Ap 203");
        var user = new User("joao@joao.com", "Joao", LocalDate.of(1978, 3, 23),
                FISICAL, "45230929-04", MALE, new Address(), true, LocalDateTime.of(2020,1,20,10,30));
        var property = new Property(234, user, "Descricao", APARTMENT, BigDecimal.valueOf(2133), 232, address, 23423422, 3, 2, 2, true, true, true, false, null, true, null);
        var garage = new Garage(3, property, 20, "snajknjkasnsa");

        when(userService.getById("joao@joao.com")).thenReturn(Optional.of(user));
        when(addressService.save(addressBody)).thenReturn(Optional.of(address));
        when(garageService.save(body.getGarages(), property)).thenReturn(Optional.of(List.of(garage)));
        when(propertyRepository.save(property)).thenReturn(property);

        propertyService.save(body);

        verify(propertyRepository).save(property);
    }

    @Test
    public void saveWithoutAddress() {

        var garageBody = new GarageRequestBody(3, 20, "snajknjkasnsa");
        var body = new EditPropertyRequestBody(234, "joao@joao.com", "Descricao", APARTMENT, BigDecimal.valueOf(2133), 232, null, 23423422, 3, 2, 2, true, true, true, false, List.of(garageBody), true, null);

        var user = new User("joao@joao.com", "Joao", LocalDate.of(1978, 3, 23),
                FISICAL, "45230929-04", MALE, new Address(), true, LocalDateTime.of(2020,1,20,10,30));
        var property = new Property(234, user, "Descricao", APARTMENT, BigDecimal.valueOf(2133), 232, null, 23423422, 3, 2, 2, true, true, true, false, null, true, null);
        var garage = new Garage(3, property, 20, "snajknjkasnsa");

        when(userService.getById("joao@joao.com")).thenReturn(Optional.of(user));
        when(addressService.save(null)).thenReturn(Optional.empty());
        when(garageService.save(body.getGarages(), property)).thenReturn(Optional.of(List.of(garage)));
        when(propertyRepository.save(property)).thenReturn(property);

        propertyService.save(body);

        verify(propertyRepository).save(property);
    }

    @Test
    public void saveWithoutUser() {

        var addressBody = new EditAddressRequestBody(56, "95020-320", "212", "Perto do tio joão");
        var garageBody = new GarageRequestBody(3, 20, "snajknjkasnsa");
        var body = new EditPropertyRequestBody(234, "joao@joao.com", "Descricao", APARTMENT, BigDecimal.valueOf(2133), 232, addressBody, 23423422, 3, 2, 2, true, true, true, false, List.of(garageBody), true, null);

        when(userService.getById("joao@joao.com")).thenReturn(Optional.empty());

        propertyService.save(body);

        verifyNoInteractions(addressService);
        verifyNoInteractions(garageService);
        verifyNoMoreInteractions(propertyRepository);
    }

    @Test
    public void saveWithoutGarages() {

        var addressBody = new EditAddressRequestBody(56, "95020-320", "212", "Perto do tio joão");
        var body = new EditPropertyRequestBody(234, "joao@joao.com", "Descricao", APARTMENT, BigDecimal.valueOf(2133), 232, addressBody, 23423422, 3, 2, 2, true, true, true, false, null, true, null);

        var address = new Address(56, new Street(), "403B", "Ap 203");
        var user = new User("joao@joao.com", "Joao", LocalDate.of(1978, 3, 23),
                FISICAL, "45230929-04", MALE, new Address(), true, LocalDateTime.of(2020,1,20,10,30));
        var property = new Property(234, user, "Descricao", APARTMENT, BigDecimal.valueOf(2133), 232, address, 23423422, 3, 2, 2, true, true, true, false, null, true, null);

        when(userService.getById("joao@joao.com")).thenReturn(Optional.of(user));
        when(addressService.save(addressBody)).thenReturn(Optional.of(address));
        when(propertyRepository.save(property)).thenReturn(property);

        propertyService.save(body);

        verify(propertyRepository).save(property);
    }

    @Test
    public void delete() {

        var id = 23;

        propertyService.delete(id);

        verify(propertyRepository).deleteById(id);
    }

}
