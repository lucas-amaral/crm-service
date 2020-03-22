package com.proposta.aceita.crmservice.entities;

import javax.persistence.*;

@Entity(name = "addresses")
public class Address {
    @Id
    private Integer id;
    @OneToOne
    private Street street;
    @Column(length = 10, nullable = false)
    private String number;
    private String complement;

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
}
