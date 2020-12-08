package com.proposta.aceita.crmservice.repositories;

import com.proposta.aceita.crmservice.entities.*;
import com.proposta.aceita.crmservice.entities.enums.State;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.proposta.aceita.crmservice.entities.enums.PropertyType.APARTMENT;
import static com.proposta.aceita.crmservice.entities.enums.PropertyType.HOUSE;
import static com.proposta.aceita.crmservice.entities.enums.Sex.MALE;
import static com.proposta.aceita.crmservice.entities.enums.UserType.FISICAL;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PropertyRepositoryTest {

    @Autowired
    private PropertyRepository propertyRepository;

    @Test
    @Sql(scripts = "classpath:clearTables.sql", statements = { "INSERT INTO addresses(id, street_zip_code, number, complement) VALUES (1, '97110-564', '43', 'Apartamento 23')",
            "INSERT INTO users(username, name, date_of_birth, type, cpf_cnpj, sex, address_id, created_at) VALUES ('joao@joao.com', 'Joao', '1978-3-23', 'FISICAL', '45230929-04', 'MALE', 1, '2020-01-20T10:30:00')",
            "INSERT INTO properties(id, username, description, type, area, registration, address_id, iptu, dorms, suites, bathrooms, pool, balcony, elevator, barbecue_grill, enable, created_at) " +
                    "VALUES (43, 'joao@joao.com', 'Casa bacana', 'HOUSE', 3423.23, 13133, null, 12414, 2, 3, 1, false, false, false, true, true, '2020-01-20T10:30:00')",
            "INSERT INTO properties(id, username, description, type, area, registration, address_id, iptu, dorms, suites, bathrooms, pool, balcony, elevator, barbecue_grill, enable, created_at) " +
                    "VALUES (76, 'joao@joao.com', 'Apartamento centro', 'APARTMENT', 267.14, 14536, null, 1432, 3, 0, 2, true, false, true, true,  true, '2020-01-20T13:45:30')"
    })
    void findByUsername() {
        var city = new City(1, "Santa Maria", State.RS);
        var neighborhood = new Neighborhood(1, "Pé de Plátano", city);
        var street = new Street("97110-564", "Rua A Quatro (Vl Almeida)", neighborhood);
        var address = new Address(1, street, "43", "Apartamento 23");
        var user = new User("joao@joao.com", "1234","Joao", LocalDate.of(1978, 3, 23),
                FISICAL, "45230929-04", MALE, address, true);


        var property43 = new Property(43, user, "Casa bacana", HOUSE, BigDecimal.valueOf(3423.23), "13133", null, "12414",
                2, 3, 1, false, false, false, true, null, true);
        var property76 = new Property(76, user, "Apartamento centro", APARTMENT, BigDecimal.valueOf(267.14), "14536", null, "1432",
                3, 0, 2,true, false, true, true, null, true);

        assertThat(propertyRepository.findByUserUsername("joao@joao.com")).containsOnly(property43, property76);
    }

}
