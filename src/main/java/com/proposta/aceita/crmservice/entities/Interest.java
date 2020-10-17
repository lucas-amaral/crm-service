package com.proposta.aceita.crmservice.entities;

import com.proposta.aceita.crmservice.entities.enums.PropertyType;
import com.proposta.aceita.crmservice.entities.req.InterestRequestBody;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "interests")
public class Interest {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    @OneToOne
    @JoinColumn(name = "username")
    private User user;
    private BigDecimal value;
    private Boolean financing;
    private BigDecimal financingValue;
    @Type(type = "com.proposta.aceita.crmservice.configurations.StringArrayUserType")
    private String[] types;
    @JoinTable(name = "interest_neighborhoods", joinColumns = @JoinColumn(name = "interest_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "neighborhood_id", referencedColumnName = "id"))
    @OneToMany(fetch = LAZY)
    private List<Neighborhood> neighborhoods;
    private Integer dorms;
    private Integer suites;
    private Integer bathrooms;
    private Integer garages;
    private Boolean pool;
    private Boolean balcony;
    private Boolean elevator;
    private Boolean barbecueGrill;
    @OneToMany(mappedBy="interest", fetch = LAZY, cascade = ALL)
    private List<Barter> barters;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    public Interest() {
    }

    public Interest(Integer id, User user, BigDecimal value, Boolean financing, BigDecimal financingValue,
                    List<PropertyType> types, List<Neighborhood> neighborhoods, Integer dorms, Integer suites,
                    Integer bathrooms, Integer garages, Boolean pool, Boolean balcony, Boolean elevator, Boolean barbecueGrill) {
        this.id = id;
        this.user = user;
        this.value = value;
        this.financing = financing;
        this.financingValue = financingValue;
        this.types = types == null ? null : types.stream().map(Enum::toString).collect(Collectors.toList()).toArray(new String[types.size()]);
        this.neighborhoods = neighborhoods;
        this.dorms = dorms;
        this.suites = suites;
        this.bathrooms = bathrooms;
        this.garages = garages;
        this.pool = pool;
        this.balcony = balcony;
        this.elevator = elevator;
        this.barbecueGrill = barbecueGrill;
    }

    public static Interest of(InterestRequestBody body, User user, List<Neighborhood> neighborhoods) {
        return new Interest(body.getId(), user, body.getValue(), body.getFinancing(), body.getFinancingValue(),
                null, neighborhoods, body.getDorms(), body.getSuites(), body.getBathrooms(),
                body.getGarages(), body.getPool(), body.getBalcony(), body.getElevator(), body.getBarbecueGrill());
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

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Boolean getFinancing() {
        return financing;
    }

    public void setFinancing(Boolean financing) {
        this.financing = financing;
    }

    public BigDecimal getFinancingValue() {
        return financingValue;
    }

    public void setFinancingValue(BigDecimal financingValue) {
        this.financingValue = financingValue;
    }

    public List<PropertyType> getTypes() {
        return types != null ? Arrays.stream(types).map(PropertyType::valueOf).collect(Collectors.toList()) : null;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }

    public List<Neighborhood> getNeighborhoods() {
        return neighborhoods;
    }

    public void setNeighborhoods(List<Neighborhood> neighborhoods) {
        this.neighborhoods = neighborhoods;
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

    public Integer getGarages() {
        return garages;
    }

    public void setGarages(Integer garages) {
        this.garages = garages;
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

    public List<Barter> getBarters() {
        return barters;
    }

    public void setBarters(List<Barter> barters) {
        this.barters = barters;
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
        Interest interest = (Interest) o;
        return Objects.equals(id, interest.id) &&
                Objects.equals(user, interest.user) &&
                Objects.equals(value, interest.value) &&
                Objects.equals(financing, interest.financing) &&
                Objects.equals(financingValue, interest.financingValue) &&
                Arrays.equals(types, interest.types) &&
                Objects.equals(neighborhoods, interest.neighborhoods) &&
                Objects.equals(dorms, interest.dorms) &&
                Objects.equals(suites, interest.suites) &&
                Objects.equals(bathrooms, interest.bathrooms) &&
                Objects.equals(garages, interest.garages) &&
                Objects.equals(pool, interest.pool) &&
                Objects.equals(balcony, interest.balcony) &&
                Objects.equals(elevator, interest.elevator) &&
                Objects.equals(barbecueGrill, interest.barbecueGrill) &&
                Objects.equals(barters, interest.barters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, value, financing, financingValue, types, neighborhoods, dorms, suites, bathrooms, garages, pool, balcony, elevator, barbecueGrill, createdAt);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Interest.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("user=" + user)
                .add("value=" + value)
                .add("financing=" + financing)
                .add("financingValue=" + financingValue)
                .add("types=" + Arrays.toString(types))
                .add("neighborhoods=" + neighborhoods)
                .add("dorms=" + dorms)
                .add("suites=" + suites)
                .add("bathrooms=" + bathrooms)
                .add("garages=" + garages)
                .add("pool=" + pool)
                .add("balcony=" + balcony)
                .add("elevator=" + elevator)
                .add("barbecueGrill=" + barbecueGrill)
                .toString();
    }
}

