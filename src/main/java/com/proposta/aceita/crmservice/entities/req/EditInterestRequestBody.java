package com.proposta.aceita.crmservice.entities.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.proposta.aceita.crmservice.entities.enums.PropertyType;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class EditInterestRequestBody implements InterestRequestBody{
    @NotNull
    private final Integer id;
    @NotNull
    private final Integer userId;
    @NotNull
    private final BigDecimal value;
    private final Boolean financing;
    private final BigDecimal financingValue;
    private final List<PropertyType> types;
    private final List<Integer> neighborhoodIds;
    private final Integer dorms;
    private final Integer suites;
    private final Integer bathrooms;
    private final Boolean pool;
    private final Boolean balcony;
    private final Boolean elevator;
    private final Boolean barbecueGrill;
    private final List<BarterRequestBody> barters;
    @NotNull
    private final LocalDateTime createdAt;

    public EditInterestRequestBody(@JsonProperty("id") Integer id,
                                   @JsonProperty("userId") Integer userId,
                                   @JsonProperty("value") BigDecimal value,
                                   @JsonProperty("financing") Boolean financing,
                                   @JsonProperty("financingValue") BigDecimal financingValue,
                                   @JsonProperty("types") List<PropertyType> types,
                                   @JsonProperty("neighborhoodIds") List<Integer> neighborhoodIds,
                                   @JsonProperty("dorms") Integer dorms,
                                   @JsonProperty("suites") Integer suites,
                                   @JsonProperty("bathrooms") Integer bathrooms,
                                   @JsonProperty("pool") Boolean pool,
                                   @JsonProperty("balcony") Boolean balcony,
                                   @JsonProperty("elevator") Boolean elevator,
                                   @JsonProperty("barbecueGrill") Boolean barbecueGrill,
                                   @JsonProperty("garages") List<BarterRequestBody> barters,
                                   @JsonProperty("createdAt") LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.value = value;
        this.financing = financing;
        this.financingValue = financingValue;
        this.types = types;
        this.neighborhoodIds = neighborhoodIds;
        this.dorms = dorms;
        this.suites = suites;
        this.bathrooms = bathrooms;
        this.pool = pool;
        this.balcony = balcony;
        this.elevator = elevator;
        this.barbecueGrill = barbecueGrill;
        this.barters = barters;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
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

    public List<Integer> getNeighborhoodIds() {
        return neighborhoodIds;
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

    public List<BarterRequestBody> getBarters() {
        return barters;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EditInterestRequestBody that = (EditInterestRequestBody) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(value, that.value) &&
                Objects.equals(financing, that.financing) &&
                Objects.equals(financingValue, that.financingValue) &&
                Objects.equals(types, that.types) &&
                Objects.equals(neighborhoodIds, that.neighborhoodIds) &&
                Objects.equals(dorms, that.dorms) &&
                Objects.equals(suites, that.suites) &&
                Objects.equals(bathrooms, that.bathrooms) &&
                Objects.equals(pool, that.pool) &&
                Objects.equals(balcony, that.balcony) &&
                Objects.equals(elevator, that.elevator) &&
                Objects.equals(barbecueGrill, that.barbecueGrill) &&
                Objects.equals(barters, that.barters) &&
                Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, value, financing, financingValue, types, neighborhoodIds, dorms, suites, bathrooms, pool, balcony, elevator, barbecueGrill, barters, createdAt);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", EditInterestRequestBody.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("userId=" + userId)
                .add("value=" + value)
                .add("financing=" + financing)
                .add("financingValue=" + financingValue)
                .add("types=" + types)
                .add("neighborhoodIds=" + neighborhoodIds)
                .add("dorms=" + dorms)
                .add("suites=" + suites)
                .add("bathrooms=" + bathrooms)
                .add("pool=" + pool)
                .add("balcony=" + balcony)
                .add("elevator=" + elevator)
                .add("barbecueGrill=" + barbecueGrill)
                .add("barters=" + barters)
                .add("createdAt=" + createdAt)
                .toString();
    }
}
