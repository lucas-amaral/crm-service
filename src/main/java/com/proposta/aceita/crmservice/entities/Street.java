package com.proposta.aceita.crmservice.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Objects;
import java.util.StringJoiner;

@Entity(name = "streets")
public class Street {
    @Id
    @Column(length = 2)
    private String zipCode;
    private String name;
    @OneToOne
    private Neighborhood neighborhood;

    public Street() {
    }

    public Street(String zipCode, String name, Neighborhood neighborhood) {
        this.zipCode = zipCode;
        this.name = name;
        this.neighborhood = neighborhood;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Neighborhood getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(Neighborhood neighborhood) {
        this.neighborhood = neighborhood;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Street street = (Street) o;
        return Objects.equals(zipCode, street.zipCode) &&
                Objects.equals(name, street.name) &&
                Objects.equals(neighborhood, street.neighborhood);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zipCode, name, neighborhood);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Street.class.getSimpleName() + "[", "]")
                .add("zipCode='" + zipCode + "'")
                .add("name='" + name + "'")
                .add("neighborhood=" + neighborhood)
                .toString();
    }
}
