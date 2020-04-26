package com.proposta.aceita.crmservice.entities.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.proposta.aceita.crmservice.entities.Interest;
import com.proposta.aceita.crmservice.entities.Neighborhood;
import com.proposta.aceita.crmservice.entities.enums.PropertyType;
import com.proposta.aceita.crmservice.util.CheckUtils;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class InterestResponseBody {
    private final Integer id;
    private final BigDecimal value;
    private final Boolean financing;
    private final BigDecimal financingValue;
    private final List<PropertyType> types;
    private final List<Integer> neighborhoodIds;
    private final Integer dorms;
    private final Integer suites;
    private final Integer bathrooms;
    private final Integer garages;
    private final Boolean pool;
    private final Boolean balcony;
    private final Boolean elevator;
    private final Boolean barbecueGrill;
    private final List<BarterResponseBody> barters;


    public InterestResponseBody(@JsonProperty("id") Integer id,
                                @JsonProperty("value") BigDecimal value,
                                @JsonProperty("financing") Boolean financing,
                                @JsonProperty("financingValue") BigDecimal financingValue,
                                @JsonProperty("types") List<PropertyType> types,
                                @JsonProperty("neighborhoodIds") List<Integer> neighborhoodIds,
                                @JsonProperty("dorms") Integer dorms,
                                @JsonProperty("suites") Integer suites,
                                @JsonProperty("bathrooms") Integer bathrooms,
                                @JsonProperty("garages") Integer garages,
                                @JsonProperty("pool") Boolean pool,
                                @JsonProperty("balcony") Boolean balcony,
                                @JsonProperty("elevator") Boolean elevator,
                                @JsonProperty("barbecueGrill") Boolean barbecueGrill,
                                @JsonProperty("barters") List<BarterResponseBody> barters) {
        this.id = id;
        this.value = value;
        this.financing = financing;
        this.financingValue = financingValue;
        this.types = types;
        this.neighborhoodIds = neighborhoodIds;
        this.dorms = dorms;
        this.suites = suites;
        this.bathrooms = bathrooms;
        this.garages = garages;
        this.pool = pool;
        this.balcony = balcony;
        this.elevator = elevator;
        this.barbecueGrill = barbecueGrill;
        this.barters = barters;
    }

    public static InterestResponseBody of(Interest interest) {
        List<BarterResponseBody> barters = CheckUtils.listIsNullOrEmpty(interest.getBarters()) ? Collections.emptyList()
                : interest.getBarters().stream().map(BarterResponseBody::of).collect(Collectors.toList());

        List<Integer> neighborhoodIs = CheckUtils.listIsNullOrEmpty(interest.getNeighborhoods()) ? Collections.emptyList()
                : interest.getNeighborhoods().stream().map(Neighborhood::getId).collect(Collectors.toList());

        return new InterestResponseBody(interest.getId(),
                interest.getValue(),
                interest.getFinancing(),
                interest.getFinancingValue(),
                interest.getTypes(),
                neighborhoodIs,
                interest.getDorms(),
                interest.getSuites(),
                interest.getBathrooms(),
                interest.getGarages(),
                interest.getPool(),
                interest.getBalcony(),
                interest.getElevator(),
                interest.getBarbecueGrill(),
                barters);
    }

    public Integer getId() {
        return id;
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

    public Integer getGarages() {
        return garages;
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

    public List<BarterResponseBody> getBarters() {
        return barters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InterestResponseBody that = (InterestResponseBody) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(value, that.value) &&
                Objects.equals(financing, that.financing) &&
                Objects.equals(financingValue, that.financingValue) &&
                Objects.equals(types, that.types) &&
                Objects.equals(neighborhoodIds, that.neighborhoodIds) &&
                Objects.equals(dorms, that.dorms) &&
                Objects.equals(suites, that.suites) &&
                Objects.equals(bathrooms, that.bathrooms) &&
                Objects.equals(garages, that.garages) &&
                Objects.equals(pool, that.pool) &&
                Objects.equals(balcony, that.balcony) &&
                Objects.equals(elevator, that.elevator) &&
                Objects.equals(barbecueGrill, that.barbecueGrill) &&
                Objects.equals(barters, that.barters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, financing, financingValue, types, neighborhoodIds, dorms, suites, bathrooms, garages, pool, balcony, elevator, barbecueGrill, barters);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", InterestResponseBody.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("value=" + value)
                .add("financing=" + financing)
                .add("financingValue=" + financingValue)
                .add("types=" + types)
                .add("neighborhoodIds=" + neighborhoodIds)
                .add("dorms=" + dorms)
                .add("suites=" + suites)
                .add("bathrooms=" + bathrooms)
                .add("garages=" + garages)
                .add("pool=" + pool)
                .add("balcony=" + balcony)
                .add("elevator=" + elevator)
                .add("barbecueGrill=" + barbecueGrill)
                .add("barters=" + barters)
                .toString();
    }
}
