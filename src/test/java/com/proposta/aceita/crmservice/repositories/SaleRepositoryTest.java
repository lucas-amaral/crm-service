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

import static com.proposta.aceita.crmservice.entities.enums.PropertyType.HOUSE;
import static com.proposta.aceita.crmservice.entities.enums.Sex.MALE;
import static com.proposta.aceita.crmservice.entities.enums.UserType.FISICAL;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SaleRepositoryTest {

    @Autowired
    private SaleRepository saleRepository;

    @Test
    @Sql(scripts = "classpath:clearTables.sql", statements = { "INSERT INTO addresses(id, street_zip_code, number, complement) VALUES (1, '97110-564', '43', 'Apartamento 23')",
            "INSERT INTO users(id, name, date_of_birth, email, type, cpf_cnpj, sex, address_id, created_at) VALUES (1, 'Joao', '1978-3-23', 'joao@joao.com', 'FISICAL', '45230929-04', 'MALE', 1, '2020-01-20T10:30:00')",
            "INSERT INTO properties(id, user_id, description, type, area, registration, address_id, iptu, dorms, suites, bathrooms, pool, balcony, elevator, barbecue_grill, enable, created_at) " +
                    "VALUES (43, 1, 'Casa bacana', 'HOUSE', 3423.23, 13133, null, 12414, 2, 3, 1, false, false, false, true, true, '2020-01-20T10:30:00')",
            "INSERT INTO sales(id, property_id, value, financing, financing_value, barter_vehicle, barter_vehicle_value, barter_property, barter_property_value, agencying) VALUES (2131, 43, 230.35, true, 100, false, null, false, null, null)",
            "INSERT INTO sales(id, property_id, value, financing, financing_value, barter_vehicle, barter_vehicle_value, barter_property, barter_property_value, agencying) VALUES (2132, 43, 380.53, true, 200, false, null, false, null, null)"
    })
    public void findByPropertyId() {
        var city = new City(1, "Santa Maria", State.RS);
        var neighborhood = new Neighborhood(1, "Pé de Plátano", city);
        var street = new Street("97110-564", "Rua A Quatro (Vl Almeida)", neighborhood);
        var address = new Address(1, street, "43", "Apartamento 23");
        var user = new User(1, "Joao", LocalDate.of(1978, 3, 23),
                "joao@joao.com", FISICAL, "45230929-04", MALE, address, LocalDateTime.of(2020,1,20,10,30));
        var property43 = new Property(43, user, "Casa bacana", HOUSE, BigDecimal.valueOf(3423.23), 13133, null, 12414,
                2, 3, 1, false, false, false, true, null, true, LocalDateTime.of(2020, 1, 20, 10, 30));

        var sale2231 = new Sale(2131, property43, BigDecimal.valueOf(230.35), true, BigDecimal.valueOf(10000, 2), false, null, false, null, null);
        var sale2232 = new Sale(2132, property43, BigDecimal.valueOf(380.53), true, BigDecimal.valueOf(20000, 2), false, null, false, null, null);

        assertThat(saleRepository.findByPropertyId(43)).containsOnly(sale2231, sale2232);
    }

}
