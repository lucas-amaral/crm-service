package com.proposta.aceita.crmservice.services;

import com.proposta.aceita.crmservice.entities.Address;
import com.proposta.aceita.crmservice.entities.User;
import com.proposta.aceita.crmservice.entities.req.AddAddressRequestBody;
import com.proposta.aceita.crmservice.entities.req.AddUserRequestBody;
import com.proposta.aceita.crmservice.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Optional;

import static com.proposta.aceita.crmservice.entities.enums.Sex.MALE;
import static com.proposta.aceita.crmservice.entities.enums.UserType.FISICAL;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private AddressService addressService;

    private UserService userService;

    @BeforeEach
    public void setup() {
        userService = new UserService(userRepository, addressService);
    }
    
    @Test
    public void getById() {

        var username = "joao@joao.com";

        userService.getById(username);

        verify(userRepository).findById(username);
    }

    @Test
    public void list() {

        userService.list();

        verify(userRepository).findAll();
    }

    @Test
    public void save() {

        var addressBody = new AddAddressRequestBody("95020-320", "212", "Não consegue moisés");
        var address = new Address(null, null, "212", "Não consegue moisés");

        var body = new AddUserRequestBody("joao@gmail.com", "1234", "João", LocalDate.of(1979, 3, 24),
                 FISICAL, "45230929-04", MALE, addressBody);
        var user = new User("joao@gmail.com", "1234", "João", LocalDate.of(1979, 3, 24),
                 FISICAL, "45230929-04", MALE, address, true);

        when(addressService.save(addressBody)).thenReturn(Optional.of(address));
        when(userRepository.save(user)).thenReturn(user);

        userService.save(body);

        verify(userRepository).save(user);
    }

    @Test
    public void saveWithoutAddress() {

        var body = new AddUserRequestBody("joao@gmail.com", "1234", "João", LocalDate.of(1979, 3, 24),
                FISICAL, "45230929-04", MALE, null);
        var user = new User("joao@gmail.com", "1234", "João", LocalDate.of(1979, 3, 24),
                FISICAL, "45230929-04", MALE, null, true);


        when(addressService.save(body.getAddress())).thenReturn(Optional.empty());
        when(userRepository.save(user)).thenReturn(user);

        userService.save(body);

        verify(userRepository).save(user);
    }

    @Test
    public void delete() {

        var username = "joao@joao.com";

        userService.delete(username);

        verify(userRepository).deleteById(username);
    }

}
