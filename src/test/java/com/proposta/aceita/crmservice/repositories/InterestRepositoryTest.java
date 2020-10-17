package com.proposta.aceita.crmservice.repositories;

import com.proposta.aceita.crmservice.entities.*;
import com.proposta.aceita.crmservice.entities.enums.PropertyType;
import com.proposta.aceita.crmservice.entities.enums.State;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static com.proposta.aceita.crmservice.entities.enums.Sex.MALE;
import static com.proposta.aceita.crmservice.entities.enums.UserType.FISICAL;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class InterestRepositoryTest {

    @Autowired
    private InterestRepository interestRepository;

    @Test
    @Sql(scripts = "classpath:clearTables.sql", statements = {
            "INSERT INTO addresses(id, street_zip_code, number, complement) VALUES (1, '97110-564', '43', 'Apartamento 23')",
            "INSERT INTO users(username, name, date_of_birth, type, cpf_cnpj, sex, address_id, created_at) " +
                    "VALUES ('joao@joao.com', 'Joao', '1978-3-23', 'FISICAL', '45230929-04', 'MALE', 1, '2020-01-20T10:30:00')",
    })
    void create() {
        var city = new City(1, "Santa Maria", State.RS);
        var neighborhood = new Neighborhood(1, "Pé de Plátano", city);
        var street = new Street("97110-564", "Rua A Quatro (Vl Almeida)", neighborhood);
        var address = new Address(1, street, "43", "Apartamento 23");
        var user = new User("joao@joao.com", "1234","Joao", LocalDate.of(1978, 3, 23),
                FISICAL, "45230929-04", MALE, address, true);

        var interest = new Interest(null, user,
                BigDecimal.valueOf(121323,2),false, null,
                null,
                List.of(),
                3, 1, 3, 1, false, true, true, true);

        assertThat(interestRepository.save(interest).getCreatedAt()).isNotNull();
    }

    @Test
    @Sql(scripts = "classpath:clearTables.sql", statements = {
            "INSERT INTO addresses(id, street_zip_code, number, complement) VALUES (1, '97110-564', '43', 'Apartamento 23')",
            "INSERT INTO users(username, name, date_of_birth, type, cpf_cnpj, sex, address_id, created_at) " +
                    "VALUES ('joao@joao.com', 'Joao', '1978-3-23', 'FISICAL', '45230929-04', 'MALE', 1, '2020-01-20T10:30:00')",
    })
    void createWithNeighborhood() {
        var city = new City(1, "Santa Maria", State.RS);
        var neighborhood = new Neighborhood(1, "Pé de Plátano", city);
        var street = new Street("97110-564", "Rua A Quatro (Vl Almeida)", neighborhood);
        var address = new Address(1, street, "43", "Apartamento 23");
        var user = new User("joao@joao.com", "1234","Joao", LocalDate.of(1978, 3, 23),
                FISICAL, "45230929-04", MALE, address, true);

        var interest = new Interest(null, user, BigDecimal.valueOf(121323,2),false, null, null,
                List.of(neighborhood), 3, 1, 3, 1, false, true, true, true);

        assertThat(interestRepository.save(interest).getCreatedAt()).isNotNull();
    }

    @Test
    @Sql(scripts = "classpath:clearTables.sql", statements = {
            "INSERT INTO addresses(id, street_zip_code, number, complement) VALUES (1, '97110-564', '43', 'Apartamento 23')",
            "INSERT INTO users(username, name, date_of_birth, type, cpf_cnpj, sex, address_id, created_at) " +
                    "VALUES ('joao@joao.com',  'Joao', '1978-3-23', 'FISICAL', '45230929-04', 'MALE', 1, '2020-01-20T10:30:00-3:00')",
            "INSERT INTO interests (id, username, value, financing, types, dorms, suites, bathrooms, pool, balcony, elevator, barbecue_grill, created_at, garages)" +
                    "VALUES (1, 'joao@joao.com', 143.22, false, ARRAY['HOUSE'], 3, 1, 2, false, false, true, false, '2020-03-21T13:30:00Z', 1)",
            "INSERT INTO interest_neighborhoods (interest_id, neighborhood_id) VALUES (1, 1)"
    })
    void update() {
        var city = new City(1, "Santa Maria", State.RS);
        var neighborhood = new Neighborhood(1, "Pé de Plátano", city);
        var street = new Street("97110-564", "Rua A Quatro (Vl Almeida)", neighborhood);
        var address = new Address(1, street, "43", "Apartamento 23");
        var user = new User("joao@joao.com", "1234","Joao", LocalDate.of(1978, 3, 23),
                FISICAL, "45230929-04", MALE, address, true);

        var interest = new Interest(1, user, BigDecimal.valueOf(121323,2),false, null, null,
                List.of(neighborhood), 3, 1, 3, 1, false, true, true, true);

        assertThat(interestRepository.save(interest))
                .matches(i -> i.getId().equals(interest.getId()))
                .matches(i -> i.getValue().equals(interest.getValue()))
                .matches(i -> i.getFinancing().equals(interest.getFinancing()))
                .matches(i -> i.getTypes() == interest.getTypes())
                .matches(i -> i.getDorms().equals(interest.getDorms()))
                .matches(i -> i.getSuites().equals(interest.getSuites()))
                .matches(i -> i.getBathrooms().equals(interest.getBathrooms()))
                .matches(i -> i.getGarages().equals(interest.getGarages()))
                .matches(i -> i.getPool().equals(interest.getPool()))
                .matches(i -> i.getBalcony().equals(interest.getBalcony()))
                .matches(i -> i.getElevator().equals(interest.getElevator()))
                .matches(i -> i.getBarbecueGrill().equals(interest.getBarbecueGrill()));
    }

    @Test
    @Sql(scripts = "classpath:clearTables.sql", statements = {
            "INSERT INTO addresses(id, street_zip_code, number, complement) VALUES (1, '97110-564', '43', 'Apartamento 23')",
            "INSERT INTO users(username, name, date_of_birth, type, cpf_cnpj, sex, address_id, created_at) " +
                    "VALUES ('joao@joao.com',  'Joao', '1978-3-23', 'FISICAL', '45230929-04', 'MALE', 1, '2020-01-20T10:30:00-3:00')",
            "INSERT INTO interests (id, username, value, financing, types, dorms, suites, bathrooms, pool, balcony, elevator, barbecue_grill, created_at, garages)" +
                    "VALUES (1, 'joao@joao.com', 143.22, false, ARRAY['HOUSE'], 3, 1, 2, false, false, true, false, '2020-03-21T13:30:00Z', 1)",
            "INSERT INTO interest_neighborhoods (interest_id, neighborhood_id) VALUES (1, 1)"
    })
    void updateTypes() {
        interestRepository.updateTypes(1, "HOUSE");

        assertThat(interestRepository.findById(1))
                .isPresent()
                .get()
                .matches(i -> i.getTypes().equals(List.of(PropertyType.HOUSE)));
    }

    @Test
    @Sql(scripts = "classpath:clearTables.sql", statements = {
            "INSERT INTO addresses(id, street_zip_code, number, complement) VALUES (1, '97110-564', '43', 'Apartamento 23')",
            "INSERT INTO users(username, name, date_of_birth, type, cpf_cnpj, sex, address_id, created_at) " +
                    "VALUES ('joao@joao.com',  'Joao', '1978-3-23', 'FISICAL', '45230929-04', 'MALE', 1, '2020-01-20T10:30:00-3:00')",
            "INSERT INTO interests (id, username, value, financing, types, dorms, suites, bathrooms, pool, balcony, elevator, barbecue_grill, created_at, garages)" +
                    "VALUES (1, 'joao@joao.com', 143.22, false, ARRAY['HOUSE'], 3, 1, 2, false, false, true, false, '2020-03-21T13:30:00Z', 1)"
    })
    void delete() {
        assertThat(interestRepository.findById(1)).isNotEmpty();

        interestRepository.deleteById(1);

        assertThat(interestRepository.findById(1)).isEmpty();
    }

    @Test
    @Sql(scripts = "classpath:clearTables.sql", statements = {
            "INSERT INTO addresses(id, street_zip_code, number, complement) VALUES (1, '97110-564', '43', 'Apartamento 23')",
            "INSERT INTO users(username, name, date_of_birth, type, cpf_cnpj, sex, address_id, created_at) " +
                    "VALUES ('joao@joao.com',  'Joao', '1978-3-23', 'FISICAL', '45230929-04', 'MALE', 1, '2020-01-20T10:30:00-3:00')",
            "INSERT INTO interests (id, username, value, financing, types, dorms, suites, bathrooms, pool, balcony, elevator, barbecue_grill, created_at, garages)" +
                    "VALUES (1, 'joao@joao.com', 143.22, false, ARRAY['HOUSE'], 3, 1, 2, false, false, true, false, '2020-03-21T13:30:00Z', 1)",
            "INSERT INTO interest_neighborhoods (interest_id, neighborhood_id) VALUES (1, 1)"
    })
    void deleteWithNeighborhood() {
        assertThat(interestRepository.findById(1)).isNotEmpty();

        interestRepository.deleteById(1);

        assertThat(interestRepository.findById(1)).isEmpty();
    }

    @Test
    @Sql(scripts = "classpath:clearTables.sql", statements = {
            "INSERT INTO addresses(id, street_zip_code, number, complement) VALUES (1, '97110-564', '43', 'Apartamento 23')",
            "INSERT INTO users(username, name, date_of_birth, type, cpf_cnpj, sex, address_id, created_at) " +
                    "VALUES ('joao@joao.com',  'Joao', '1978-3-23', 'FISICAL', '45230929-04', 'MALE', 1, '2020-01-20T10:30:00-3:00')",
            "INSERT INTO interests (id, username, value, financing, types, dorms, suites, bathrooms, pool, balcony, elevator, barbecue_grill, created_at, garages)" +
                    "VALUES (1, 'joao@joao.com', 143.22, false, ARRAY['HOUSE'], 3, 1, 2, false, false, true, false, '2020-03-21T13:30:00Z', 1)",
            "INSERT INTO interest_neighborhoods (interest_id, neighborhood_id) VALUES (1, 1)",
            "INSERT INTO barters (id, interest_id, type, value) VALUES (1, 1, 'VEHICLE', 2032.3)"
    })
    void deleteWithBarters() {
        assertThat(interestRepository.findById(1)).isNotEmpty();

        interestRepository.deleteById(1);

        assertThat(interestRepository.findById(1)).isEmpty();
    }

}
