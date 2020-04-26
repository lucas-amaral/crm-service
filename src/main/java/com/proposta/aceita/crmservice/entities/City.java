package com.proposta.aceita.crmservice.entities;

import com.proposta.aceita.crmservice.entities.enums.State;
import com.proposta.aceita.crmservice.entities.req.CityRequestBody;

import javax.persistence.*;
import java.util.Objects;
import java.util.StringJoiner;

import static javax.persistence.EnumType.*;
import static javax.persistence.GenerationType.*;

@Entity(name = "cities")
public class City {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    private String name;
    @Enumerated(STRING)
    private State state;

    public City() {
    }

    public City(Integer id, String name, State state) {
        this.id = id;
        this.name = name;
        this.state = state;
    }

    public static City of(CityRequestBody body) {
        return new City(body.getId(), body.getName(), body.getState());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(id, city.id) &&
                Objects.equals(name, city.name) &&
                Objects.equals(state, city.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, state);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", City.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("state=" + state)
                .toString();
    }
}
