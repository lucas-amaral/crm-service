package com.proposta.aceita.crmservice.entities;

import com.proposta.aceita.crmservice.entities.enums.PropertyType;
import com.proposta.aceita.crmservice.entities.req.PropertyRequestBody;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "properties")
public class Property {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "username")
    private User user;
    @Column(nullable = false)
    private String description;
    @Enumerated(STRING)
    private PropertyType type;
    private BigDecimal area;
    private String registration;
    @ManyToOne(cascade = ALL)
    private Address address;
    private String iptu;
    private Integer dorms;
    private Integer suites;
    private Integer bathrooms;
    private Boolean pool;
    private Boolean balcony;
    private Boolean elevator;
    private Boolean barbecueGrill;
    @OneToMany(mappedBy="property", fetch = LAZY, cascade = ALL)
    private List<Garage> garages;
    private Boolean enable;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    public Property() {
    }

    public Property(Integer id, User user, String description, PropertyType type, BigDecimal area,
                               String registration, Address address, String iptu, Integer dorms, Integer suites,
                               Integer bathrooms, Boolean pool, Boolean balcony, Boolean elevator, Boolean barbecueGrill,
                               List<Garage> garages, Boolean enable) {
        this.id = id;
        this.user = user;
        this.description = description;
        this.type = type;
        this.area = area;
        this.registration = registration;
        this.address = address;
        this.iptu = iptu;
        this.dorms = dorms;
        this.suites = suites;
        this.bathrooms = bathrooms;
        this.pool = pool;
        this.balcony = balcony;
        this.elevator = elevator;
        this.barbecueGrill = barbecueGrill;
        this.garages = garages;
        this.enable = enable;
    }

    public static Property of(PropertyRequestBody body, User user, Address address) {
        return new Property(body.getId(), user, body.getDescription(), body.getType(), body.getArea(),
                body.getRegistration(), address, body.getIptu(), body.getDorms(), body.getSuites(), body.getBathrooms(),
                body.getPool(), body.getBalcony(), body.getElevator(), body.getBarbecueGrill(), null,
                body.getEnable());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PropertyType getType() {
        return type;
    }

    public void setType(PropertyType type) {
        this.type = type;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getIptu() {
        return iptu;
    }

    public void setIptu(String iptu) {
        this.iptu = iptu;
    }

    public Integer getDorms() {
        return dorms;
    }

    public void setDorms(Integer dorms) {
        this.dorms = dorms;
    }

    public Integer getSuites() {
        return suites;
    }

    public void setSuites(Integer suites) {
        this.suites = suites;
    }

    public Integer getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(Integer bathrooms) {
        this.bathrooms = bathrooms;
    }

    public Boolean getPool() {
        return pool;
    }

    public void setPool(Boolean pool) {
        this.pool = pool;
    }

    public Boolean getBalcony() {
        return balcony;
    }

    public void setBalcony(Boolean balcony) {
        this.balcony = balcony;
    }

    public Boolean getElevator() {
        return elevator;
    }

    public void setElevator(Boolean elevator) {
        this.elevator = elevator;
    }

    public Boolean getBarbecueGrill() {
        return barbecueGrill;
    }

    public void setBarbecueGrill(Boolean barbecueGrill) {
        this.barbecueGrill = barbecueGrill;
    }

    public List<Garage> getGarages() {
        return garages;
    }

    public void setGarages(List<Garage> garages) {
        this.garages = garages;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Property property = (Property) o;
        return Objects.equals(id, property.id) &&
                Objects.equals(user, property.user) &&
                Objects.equals(description, property.description) &&
                type == property.type &&
                Objects.equals(area, property.area) &&
                Objects.equals(registration, property.registration) &&
                Objects.equals(address, property.address) &&
                Objects.equals(iptu, property.iptu) &&
                Objects.equals(dorms, property.dorms) &&
                Objects.equals(suites, property.suites) &&
                Objects.equals(bathrooms, property.bathrooms) &&
                Objects.equals(pool, property.pool) &&
                Objects.equals(balcony, property.balcony) &&
                Objects.equals(elevator, property.elevator) &&
                Objects.equals(barbecueGrill, property.barbecueGrill) &&
                Objects.equals(enable, property.enable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, description, type, area, registration, address, iptu, dorms, suites, bathrooms, pool, balcony, elevator, barbecueGrill, enable, createdAt);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Property.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("user=" + user)
                .add("description='" + description + "'")
                .add("type=" + type)
                .add("area=" + area)
                .add("registration=" + registration)
                .add("address=" + address)
                .add("iptu=" + iptu)
                .add("dorms=" + dorms)
                .add("suites=" + suites)
                .add("bathrooms=" + bathrooms)
                .add("pool=" + pool)
                .add("balcony=" + balcony)
                .add("elevator=" + elevator)
                .add("barbecueGrill=" + barbecueGrill)
                .add("enable=" + enable)
                .add("createdAt=" + createdAt)
                .toString();
    }
}

