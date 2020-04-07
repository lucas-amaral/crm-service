package com.proposta.aceita.crmservice.entities.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.proposta.aceita.crmservice.entities.enums.PropertyType;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class AddInterestRequestBody implements InterestRequestBody{
    @NotNull
    @Email
    private final String username;
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

    public AddInterestRequestBody(@JsonProperty("username") String username,
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
                                  @JsonProperty("garages") List<BarterRequestBody> barters) {
        this.username = username;
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
    }

    public Integer getId() {
        return null;
    }

    public String getUsername() {
        return username;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddInterestRequestBody that = (AddInterestRequestBody) o;
        return Objects.equals(username, that.username) &&
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
                Objects.equals(barters, that.barters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, value, financing, financingValue, types, neighborhoodIds, dorms, suites, bathrooms, pool, balcony, elevator, barbecueGrill, barters);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AddInterestRequestBody.class.getSimpleName() + "[", "]")
                .add("username=" + username)
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
                .toString();
    }
}
