package com.proposta.aceita.crmservice.entities.req.intergrations;

import com.proposta.aceita.crmservice.entities.Interest;
import com.proposta.aceita.crmservice.entities.Neighborhood;
import com.proposta.aceita.crmservice.entities.enums.PropertyType;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class InterestRequestBody {
    private final Integer id;
    private final UserRequestBody user;
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
    private final List<BarterRequestBody> barters;


    public InterestRequestBody(Integer id, UserRequestBody user, BigDecimal value, Boolean financing, BigDecimal financingValue, List<PropertyType> types, List<Integer> neighborhoodIds, Integer dorms, Integer suites, Integer bathrooms, Integer garages, Boolean pool, Boolean balcony, Boolean elevator, Boolean barbecueGrill, List<BarterRequestBody> barters) {
        this.id = id;
        this.user = user;
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

    public static InterestRequestBody of(Interest interest) {
        List<BarterRequestBody> barters = CollectionUtils.isEmpty(interest.getBarters()) ? Collections.emptyList()
                : interest.getBarters().stream().map(BarterRequestBody::of).collect(Collectors.toList());

        List<Integer> neighborhoodIs = CollectionUtils.isEmpty(interest.getNeighborhoods()) ? Collections.emptyList()
                : interest.getNeighborhoods().stream().map(Neighborhood::getId).collect(Collectors.toList());

        var user = new UserRequestBody(interest.getUser().getName(), interest.getUser().getUsername());

        return new InterestRequestBody(interest.getId(),
                user,
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

    public UserRequestBody getUser() {
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

    public List<BarterRequestBody> getBarters() {
        return barters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InterestRequestBody that = (InterestRequestBody) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(user, that.user) &&
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
        return Objects.hash(id, user, value, financing, financingValue, types, neighborhoodIds, dorms, suites, bathrooms, garages, pool, balcony, elevator, barbecueGrill, barters);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", InterestRequestBody.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("user=" + user)
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
