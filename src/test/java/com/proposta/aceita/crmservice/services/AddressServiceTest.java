package com.proposta.aceita.crmservice.services;

import com.proposta.aceita.crmservice.entities.Address;
import com.proposta.aceita.crmservice.entities.Street;
import com.proposta.aceita.crmservice.entities.req.AddressRequestBody;
import com.proposta.aceita.crmservice.repositories.AddressRepository;
import com.proposta.aceita.crmservice.repositories.StreetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
public class AddressServiceTest {

    @MockBean
    private AddressRepository addressRepository;

    @MockBean
    private StreetRepository streetRepository;

    private AddressService addressService;

    @BeforeEach
    public void setup() {
        addressService = new AddressService(addressRepository, streetRepository);
    }
    
    @Test
    public void getById() {

        final Integer id = 23;

        addressService.getById(id);

        verify(addressRepository).findById(id);
    }

    @Test
    public void list() {

        addressService.list();

        verify(addressRepository).findAll();
    }

    @Test
    public void save() {

        final Street street = new Street("95020-320", "Centro", null);
        final AddressRequestBody body = new AddressRequestBody(null, "95020-320", "212", "Não consegue moisés");
        final Address address = new Address(null, street, "212", "Não consegue moisés");

        when(streetRepository.findById("95020-320")).thenReturn(Optional.of(street));

        addressService.save(body);

        verify(addressRepository).save(address);
    }

    @Test
    public void saveWithInvalidStreetZipCode() {

        final AddressRequestBody body = new AddressRequestBody(null, "95020-320", "212", "Não consegue moisés");

        when(streetRepository.findById("95020-320")).thenReturn(Optional.empty());

        addressService.save(body);

        verifyNoInteractions(addressRepository);
    }

    @Test
    public void delete() {

        final Integer id = 23;

        addressService.delete(id);

        verify(addressRepository).deleteById(id);
    }

}
