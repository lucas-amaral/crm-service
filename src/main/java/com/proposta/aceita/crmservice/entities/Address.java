package com.proposta.aceita.crmservice.entities;

import com.proposta.aceita.crmservice.entities.req.AddressRequestBody;

import javax.persistence.*;
import java.util.Objects;
import java.util.StringJoiner;

import static javax.persistence.GenerationType.*;

@Entity(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    @OneToOne
    private Street street;
    @Column(length = 10, nullable = false)
    private String number;
    private String complement;

    public Address() {
    }

    public Address(Integer id, Street street, String number, String complement) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.complement = complement;
    }

    public static Address of(AddressRequestBody body, Street street) {
        return new Address(body.getId(), street, body.getNumber(), body.getComplement());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id) &&
                Objects.equals(street, address.street) &&
                Objects.equals(number, address.number) &&
                Objects.equals(complement, address.complement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, street, number, complement);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Address.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("street=" + street)
                .add("number='" + number + "'")
                .add("complement='" + complement + "'")
                .toString();
    }
}
