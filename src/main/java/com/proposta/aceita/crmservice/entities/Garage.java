package com.proposta.aceita.crmservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proposta.aceita.crmservice.entities.req.GarageRequestBody;
import com.proposta.aceita.crmservice.util.CheckUtils;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import static javax.persistence.GenerationType.*;

@Entity(name = "garages")
public class Garage {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    @ManyToOne
    private Property property;
    private Integer box;
    private String registration;

    public Garage() {
    }

    public Garage(Integer id, Property property, Integer box, String registration) {
        this.id = id;
        this.property = property;
        this.box = box;
        this.registration = registration;
    }

    public static Garage of(GarageRequestBody body, Property property) {
        return new Garage(body.getId(), property, body.getBox(), body.getRegistration());
    }

    public static List<Garage> ofList(List<GarageRequestBody> body, Property property) {
        return (CheckUtils.listIsNullOrEmpty(body)) ? Collections.emptyList()
                : body.stream().map(g -> Garage.of(g, property)).collect(Collectors.toList());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonIgnore
    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Integer getBox() {
        return box;
    }

    public void setBox(Integer box) {
        this.box = box;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Garage garage = (Garage) o;
        return Objects.equals(id, garage.id) &&
                Objects.equals(property, garage.property) &&
                Objects.equals(box, garage.box) &&
                Objects.equals(registration, garage.registration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, property, box, registration);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Garage.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("box=" + box)
                .add("registration='" + registration + "'")
                .toString();
    }
}
