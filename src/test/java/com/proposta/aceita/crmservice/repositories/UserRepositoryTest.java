package com.proposta.aceita.crmservice.repositories;

import com.proposta.aceita.crmservice.entities.*;
import com.proposta.aceita.crmservice.entities.enums.State;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.proposta.aceita.crmservice.entities.enums.Sex.MALE;
import static com.proposta.aceita.crmservice.entities.enums.UserType.FISICAL;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Test
    @Sql(scripts = "classpath:clearTables.sql", statements = {
            "INSERT INTO addresses(id, street_zip_code, number, complement) VALUES (1, '97110-564', '43', 'Apartamento 23')"
    })
    void create() {
        var city = new City(1, "Santa Maria", State.RS);
        var neighborhood = new Neighborhood(1, "Pé de Plátano", city);
        var street = new Street("97110-564", "Rua A Quatro (Vl Almeida)", neighborhood);
        var address = new Address(1, street, "43", "Apartamento 23");
        var user = new User("joao@joao.com", "1234","Joao", LocalDate.of(1978, 3, 23),
                FISICAL, "45230929-04", MALE, address, true);

        assertThat(userRepository.save(user).getCreatedAt()).isNotNull();
    }

    @Test
    @Sql(scripts = "classpath:clearTables.sql", statements = {
            "INSERT INTO addresses(id, street_zip_code, number, complement) VALUES (1, '97110-564', '43', 'Apartamento 23')",
            "INSERT INTO users(username, name, date_of_birth, type, cpf_cnpj, sex, address_id, created_at) VALUES ('joao@joao.com', 'Joao', '1978-3-23', 'FISICAL', '45230929-04', 'MALE', 1, '2020-01-20T10:30:00')"
    })
    void update() {
        var city = new City(1, "Santa Maria", State.RS);
        var neighborhood = new Neighborhood(1, "Pé de Plátano", city);
        var street = new Street("97110-564", "Rua A Quatro (Vl Almeida)", neighborhood);
        var address = new Address(1, street, "43", "Apartamento 23");
        var user = new User("joao@joao.com", "1234","Joao", LocalDate.of(1978, 3, 23),
                FISICAL, "45230929-04", MALE, address, true);

        assertThat(userRepository.save(user)).isEqualTo(user);
    }

    @Test
    @Sql(scripts = "classpath:clearTables.sql", statements = {
            "INSERT INTO addresses(id, street_zip_code, number, complement) VALUES (1, '97110-564', '43', 'Apartamento 23')",
            "INSERT INTO addresses(id, street_zip_code, number, complement) VALUES (2, '97015-440', '47', 'Apartamento 450')",
            "INSERT INTO users(username, name, date_of_birth, type, cpf_cnpj, sex, address_id, created_at) VALUES ('joao@joao.com',  'Joao', '1978-3-23', 'FISICAL', '45230929-04', 'MALE', 1, '2020-01-20T10:30:00')"
    })
    void updateIfAddressChange() {
        var city = new City(1, "Santa Maria", State.RS);
        var neighborhood = new Neighborhood(32, "Nossa Senhora de Fátima", city);
        var street = new Street("97015-440", "Rua Olavo Bilac", neighborhood);
        var address = new Address(2, street, "47", "Apartamento 450");
        var user = new User("joao@joao.com", "1234","Joao", LocalDate.of(1978, 3, 23),
                FISICAL, "45230929-04", MALE, address, true);

        assertThat(userRepository.save(user)).isEqualTo(user);
    }

    @Test
    @Sql(scripts = "classpath:clearTables.sql", statements = {
            "INSERT INTO users(username, name, date_of_birth, type, cpf_cnpj, sex, address_id, created_at) VALUES ('joao@joao.com',  'Joao', '1978-3-23', 'FISICAL', '45230929-04', 'MALE', null, '2020-01-20T10:30:00')"
    })
    void updateWithoutAddress() {
        var user = new User("joao@joao.com", "1234","Joao", LocalDate.of(1978, 3, 23),
                FISICAL, "45230929-04", MALE, null, true);

        assertThat(userRepository.save(user)).isEqualTo(user);
    }

    @Test
    @Sql(scripts = "classpath:clearTables.sql", statements = {
            "INSERT INTO addresses(id, street_zip_code, number, complement) VALUES (1, '97110-564', '43', 'Apartamento 23')",
            "INSERT INTO users(username, name, date_of_birth, type, cpf_cnpj, sex, address_id, created_at) VALUES ('joao@joao.com',  'Joao', '1978-3-23', 'FISICAL', '45230929-04', 'MALE', 1, '2020-01-20T10:30:00-3:00')"
    })
    void delete() {
        assertThat(addressRepository.findById(1)).isNotEmpty();

        userRepository.deleteById("joao@joao.com");

        assertThat(addressRepository.findById(1)).isEmpty();
    }

}
