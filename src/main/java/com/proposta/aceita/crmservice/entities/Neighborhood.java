package com.proposta.aceita.crmservice.entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.StringJoiner;

import static javax.persistence.GenerationType.*;

@Entity(name = "neighborhoods")
public class Neighborhood {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    private String name;
    @OneToOne
    private City city;

    public Neighborhood() {
    }

    public Neighborhood(Integer id, String name, City city) {
        this.id = id;
        this.name = name;
        this.city = city;
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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Neighborhood that = (Neighborhood) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, city);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Neighborhood.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("city=" + city)
                .toString();
    }
}
