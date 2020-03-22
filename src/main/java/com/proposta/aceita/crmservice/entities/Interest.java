package com.proposta.aceita.crmservice.entities;

import com.proposta.aceita.crmservice.entities.enums.PropertyType;
import com.vladmihalcea.hibernate.type.array.IntArrayType;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

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
    @OneToMany(fetch = FetchType.LAZY)
    private List<Neighborhood> neighborhoods;
    private Integer dorms;
    private Integer suites;
    private Integer bathrooms;
    private Boolean pool;
    private Boolean balcony;
    private Boolean elevator;
    private Boolean barbecueGrill;
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

    public BigDecimal getValue() {
        return value;
    }

    public Boolean getFinancing() {
        return financing;
    }

    public BigDecimal getFinancingValue() {
        return financingValue;
    }

    public List<PropertyType> getTypes() {
        return types;
    }

    public List<Neighborhood> getNeighborhoods() {
        return neighborhoods;
    }

    public Integer getDorms() {
        return dorms;
    }

    public Integer getSuites() {
        return suites;
    }

    public Integer getBathrooms() {
        return bathrooms;
    }

    public Boolean getPool() {
        return pool;
    }

    public Boolean getBalcony() {
        return balcony;
    }

    public Boolean getElevator() {
        return elevator;
    }

    public Boolean getBarbecueGrill() {
        return barbecueGrill;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }
}
