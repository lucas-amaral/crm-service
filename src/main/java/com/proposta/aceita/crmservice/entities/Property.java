package com.proposta.aceita.crmservice.entities;

import com.proposta.aceita.crmservice.entities.enums.PropertyType;
import com.proposta.aceita.crmservice.entities.req.PropertyRequestBody;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

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

    public Property() {
    }

    public Property(Integer id, User user, String description, PropertyType type, BigDecimal area,
                               Integer registration, Address address, Integer iptu, Integer dorms, Integer suites,
                               Integer bathrooms, Boolean pool, Boolean balcony, Boolean elevator, Boolean barbecueGrill,
                               List<Garage> garages, Boolean enable, OffsetDateTime createdAt) {
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
        this.createdAt = createdAt;
    }

    public static Property from(PropertyRequestBody body, User user, Address address) {
        return new Property(body.getId(), user, body.getDescription(), body.getType(), body.getArea(),
                body.getRegistration(), address, body.getIptu(), body.getDorms(), body.getSuites(), body.getBathrooms(),
                body.getPool(), body.getBalcony(), body.getElevator(), body.getBarbecueGrill(), null,
                body.getEnable(), null);
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

    public List<Garage> getGarages() {
        return garages;
    }

    public void setGarages(List<Garage> garages) {
        this.garages = garages;
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
                Objects.equals(images, property.images) &&
                Objects.equals(enable, property.enable) &&
                Objects.equals(createdAt, property.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, description, type, area, registration, address, iptu, dorms, suites, bathrooms, pool, balcony, elevator, barbecueGrill, images, enable, createdAt);
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
                .add("garages=" + garages)
                .add("images=" + images)
                .add("enable=" + enable)
                .add("createdAt=" + createdAt)
                .toString();
    }
}

