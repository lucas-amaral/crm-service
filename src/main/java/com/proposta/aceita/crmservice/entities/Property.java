package com.proposta.aceita.crmservice.entities;

import com.proposta.aceita.crmservice.entities.enums.PropertyType;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Entity(name = "properties")
@TypeDef(name = "list-array", typeClass = StringArrayType.class)
public class Property {
    @Id
    private Integer id;
    @ManyToOne
    private User user;
    @Column(nullable = false)
    private String description;
    @Enumerated(EnumType.STRING)
    private PropertyType type;
    private BigDecimal area;
    private Integer registration;
    @ManyToOne(cascade = CascadeType.ALL)
    private Address address;
    private Integer iptu;
    private Integer dorms;
    private Integer suites;
    private Integer bathrooms;
    private Boolean pool;
    private Boolean balcony;
    private Boolean elevator;
    private Boolean barbecueGrill;
    @OneToMany(mappedBy="property", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Garage> garages;
    @Type(type = "list-array")
    @Column(columnDefinition = "STRING ARRAY")
    private List<String> images;
    private Boolean enable;
    @CreationTimestamp
    private OffsetDateTime createdAt;

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

    public Integer getRegistration() {
        return registration;
    }

    public void setRegistration(Integer registration) {
        this.registration = registration;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getIptu() {
        return iptu;
    }

    public void setIptu(Integer iptu) {
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

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

@Entity(name = "garages")
class Garage {
    @Id
    private Integer id;
    @ManyToOne
    private Property property;
    private Integer box;
    private String registration;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
}

