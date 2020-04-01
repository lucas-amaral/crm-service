package com.proposta.aceita.crmservice.entities;

import com.proposta.aceita.crmservice.entities.enums.PropertyType;
import com.proposta.aceita.crmservice.entities.req.InterestRequestBody;
import com.vladmihalcea.hibernate.type.array.IntArrayType;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Entity(name = "interests")
@TypeDef(name = "str-array", typeClass = StringArrayType.class)
@TypeDef(name = "int-array", typeClass = IntArrayType.class)
public class Interest {
    @Id
    private Integer id;
    @OneToOne
    private User user;
    private BigDecimal value;
    private Boolean financing;
    private BigDecimal financingValue;
    @Enumerated(EnumType.STRING)
    @Type(type = "str-array")
    @Column(columnDefinition = "STRING ARRAY")
    private List<PropertyType> types;
    @JoinTable(name = "interest_neighborhoods",
            joinColumns = @JoinColumn(name = "interest_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "neighborhood_id", referencedColumnName = "id"))
    @OneToMany(fetch = FetchType.LAZY)
    private List<Neighborhood> neighborhoods;
    private Integer dorms;
    private Integer suites;
    private Integer bathrooms;
    private Boolean pool;
    private Boolean balcony;
    private Boolean elevator;
    private Boolean barbecueGrill;
    @OneToMany(mappedBy="interest", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Barter> barters;
    @CreationTimestamp
    private LocalDateTime createdAt;

    public Interest() {
    }

    public Interest(Integer id, User user, BigDecimal value, Boolean financing, BigDecimal financingValue,
                    List<PropertyType> types, List<Neighborhood> neighborhoods, Integer dorms, Integer suites,
                    Integer bathrooms, Boolean pool, Boolean balcony, Boolean elevator, Boolean barbecueGrill) {
        this.id = id;
        this.user = user;
        this.value = value;
        this.financing = financing;
        this.financingValue = financingValue;
        this.types = types;
        this.neighborhoods = neighborhoods;
        this.dorms = dorms;
        this.suites = suites;
        this.bathrooms = bathrooms;
        this.pool = pool;
        this.balcony = balcony;
        this.elevator = elevator;
        this.barbecueGrill = barbecueGrill;
    }

    public static Interest from(InterestRequestBody body, User user, List<Neighborhood> neighborhoods) {
        return new Interest(body.getId(), user, body.getValue(), body.getFinancing(), body.getFinancingValue(),
                body.getTypes(), neighborhoods, body.getDorms(), body.getSuites(), body.getBathrooms(),
                body.getPool(), body.getBalcony(), body.getElevator(), body.getBarbecueGrill());
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
        return types;
    }

    public void setTypes(List<PropertyType> types) {
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
                Objects.equals(types, interest.types) &&
                Objects.equals(neighborhoods, interest.neighborhoods) &&
                Objects.equals(dorms, interest.dorms) &&
                Objects.equals(suites, interest.suites) &&
                Objects.equals(bathrooms, interest.bathrooms) &&
                Objects.equals(pool, interest.pool) &&
                Objects.equals(balcony, interest.balcony) &&
                Objects.equals(elevator, interest.elevator) &&
                Objects.equals(barbecueGrill, interest.barbecueGrill) &&
                Objects.equals(createdAt, interest.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, value, financing, financingValue, types, neighborhoods, dorms, suites, bathrooms, pool, balcony, elevator, barbecueGrill, createdAt);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Interest.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("user=" + user)
                .add("value=" + value)
                .add("financing=" + financing)
                .add("financingValue=" + financingValue)
                .add("types=" + types)
                .add("neighborhoods=" + neighborhoods)
                .add("dorms=" + dorms)
                .add("suites=" + suites)
                .add("bathrooms=" + bathrooms)
                .add("pool=" + pool)
                .add("balcony=" + balcony)
                .add("elevator=" + elevator)
                .add("barbecueGrill=" + barbecueGrill)
                .add("createdAt=" + createdAt)
                .toString();
    }
}

