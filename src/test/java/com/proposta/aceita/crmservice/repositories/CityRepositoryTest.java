package com.proposta.aceita.crmservice.repositories;

import com.proposta.aceita.crmservice.entities.City;
import com.proposta.aceita.crmservice.entities.enums.State;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CityRepositoryTest {

    @Autowired
    private CityRepository cityRepository;

    @Test
    @Sql(scripts = "classpath:clearTables.sql", statements = { "INSERT INTO cities(id, name, state) VALUES (2, 'Caxias', 'RJ')"})
    void update() {
        var city = new City(2, "Caxias do Sul", State.RS);

        assertThat(cityRepository.save(city)).isEqualTo(city);
    }

    @Test
    @Sql(scripts = "classpath:clearTables.sql", statements = { "INSERT INTO cities(id, name, state) VALUES (2, 'Caxias do Sul', 'RS')"})
    void findByState() {
        var citySm = new City(1, "Santa Maria", State.RS);
        var cityCx = new City(2, "Caxias do Sul", State.RS);

        assertThat(cityRepository.findByState(State.RS)).containsOnly(citySm, cityCx);
    }

}
