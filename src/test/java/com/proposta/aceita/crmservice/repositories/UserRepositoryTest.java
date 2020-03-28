package com.proposta.aceita.crmservice.repositories;

import com.proposta.aceita.crmservice.entities.*;
import com.proposta.aceita.crmservice.entities.enums.State;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import static com.proposta.aceita.crmservice.entities.enums.Sex.MALE;
import static com.proposta.aceita.crmservice.entities.enums.UserType.FISICAL;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Sql(scripts = "classpath:clearTables.sql", statements = {
            "INSERT INTO addresses(id, street_zip_code, number, complement) VALUES (1, '97110-564', '43', 'Apartamento 23')"
    })
    public void create() {
        final City city = new City(1, "Santa Maria", State.RS);
        final Neighborhood neighborhood = new Neighborhood(1, "Pé de Plátano", city);
        final Street street = new Street("97110-564", "Rua A Quatro (Vl Almeida)", neighborhood);
        final Address address = new Address(1, street, "43", "Apartamento 23");
        final User user = new User(1, "João", LocalDate.of(1979, 3, 24),
                "joao@gmail.com", FISICAL, "45230929-04", MALE, address, null);

        assertThat(userRepository.save(user).getCreatedAt()).isNotNull();
    }

    @Test
    @Sql(scripts = "classpath:clearTables.sql", statements = {
            "INSERT INTO addresses(id, street_zip_code, number, complement) VALUES (1, '97110-564', '43', 'Apartamento 23')",
            "INSERT INTO users(id, name, date_of_birth, email, type, cpf_cnpj, sex, address_id, created_at) VALUES (1, 'Joao', '1978-3-23','joao@joao.com', 'FISICAL', '45230929-04', 'MALE', 1, '2020-01-20T10:30:00-3:00')"
    })
    public void update() {
        final City city = new City(1, "Santa Maria", State.RS);
        final Neighborhood neighborhood = new Neighborhood(1, "Pé de Plátano", city);
        final Street street = new Street("97110-564", "Rua A Quatro (Vl Almeida)", neighborhood);
        final Address address = new Address(1, street, "43", "Apartamento 23");
        final User user = new User(1, "João", LocalDate.of(1979, 3, 24),
                "joao@gmail.com", FISICAL, "45230929-04", MALE, address, OffsetDateTime.parse("2020-01-20T10:30:00Z"));

        assertThat(userRepository.save(user)).isEqualTo(user);
    }

    @Test
    @Sql(scripts = "classpath:clearTables.sql", statements = {
            "INSERT INTO addresses(id, street_zip_code, number, complement) VALUES (1, '97110-564', '43', 'Apartamento 23')",
            "INSERT INTO addresses(id, street_zip_code, number, complement) VALUES (2, '97015-440', '47', 'Apartamento 450')",
            "INSERT INTO users(id, name, date_of_birth, email, type, cpf_cnpj, sex, address_id, created_at) VALUES (1, 'Joao', '1978-3-23','joao@joao.com', 'FISICAL', '45230929-04', 'MALE', 1, '2020-01-20T10:30:00-3:00')"
    })
    public void updateIfAddressChange() {
        final City city = new City(1, "Santa Maria", State.RS);
        final Neighborhood neighborhood = new Neighborhood(32, "Nossa Senhora de Fátima", city);
        final Street street = new Street("97015-440", "Rua Olavo Bilac", neighborhood);
        final Address address = new Address(2, street, "47", "Apartamento 450");
        final User user = new User(1, "João", LocalDate.of(1979, 3, 24),
                "joao@gmail.com", FISICAL, "45230929-04", MALE, address, OffsetDateTime.parse("2020-01-20T10:30:00Z"));

        assertThat(userRepository.save(user)).isEqualTo(user);
    }

    @Test
    @Sql(scripts = "classpath:clearTables.sql", statements = {
            "INSERT INTO users(id, name, date_of_birth, email, type, cpf_cnpj, sex, address_id, created_at) VALUES (1, 'Joao', '1978-3-23','joao@joao.com', 'FISICAL', '45230929-04', 'MALE', null, '2020-01-20T10:30:00-3:00')"
    })
    public void updateWithoutAddress() {
        final User user = new User(1, "João", LocalDate.of(1979, 3, 24),
                "joao@gmail.com", FISICAL, "45230929-04", MALE, null, OffsetDateTime.parse("2020-01-20T10:30:00Z"));

        assertThat(userRepository.save(user)).isEqualTo(user);
    }

}
