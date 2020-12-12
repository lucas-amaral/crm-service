package com.proposta.aceita.crmservice.entities.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.proposta.aceita.crmservice.entities.PropertyImage;
import com.proposta.aceita.crmservice.entities.enums.PropertyType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class SaleResponseBody {
    private final Integer id;
    private final Integer propertyId;
    private final Integer neighborhoodId;
    private final PropertyType type;
    private final Integer dorms;
    private final Integer suites;
    private final Integer bathrooms;
    private final Integer garages;
    private final boolean pool;
    private final boolean balcony;
    private final boolean elevator;
    private final boolean barbecueGrill;
    private final BigDecimal value;
    private final boolean financing;
    private final BigDecimal financingValue;
    private final boolean barterVehicle;
    private final BigDecimal barterVehicleValue;
    private final boolean barterProperty;
    private final BigDecimal barterPropertyValue;

    private String description;
    private BigDecimal area;
    private String streetName;

    private List<PropertyImage> images;

    public SaleResponseBody(@JsonProperty("id") Integer id,
                            @JsonProperty("propertyId") Integer propertyId,
                            @JsonProperty("neighborhoodId") Integer neighborhoodId,
                            @JsonProperty("type") PropertyType type,
                            @JsonProperty("dorms") Integer dorms,
                            @JsonProperty("suites") Integer suites,
                            @JsonProperty("bathrooms") Integer bathrooms,
                            @JsonProperty("garages") Integer garages,
                            @JsonProperty("pool") boolean pool,
                            @JsonProperty("balcony") boolean balcony,
                            @JsonProperty("elevator") boolean elevator,
                            @JsonProperty("barbecueGrill") boolean barbecueGrill,
                            @JsonProperty("value") BigDecimal value,
                            @JsonProperty("financing") boolean financing,
                            @JsonProperty("financingValue") BigDecimal financingValue,
                            @JsonProperty("barterVehicle") boolean barterVehicle,
                            @JsonProperty("barterVehicleValue") BigDecimal barterVehicleValue,
                            @JsonProperty("barterProperty") boolean barterProperty,
                            @JsonProperty("barterPropertyValue") BigDecimal barterPropertyValue) {
        this.id = id;
        this.propertyId = propertyId;
        this.neighborhoodId = neighborhoodId;
        this.type = type;
        this.dorms = dorms;
        this.suites = suites;
        this.bathrooms = bathrooms;
        this.garages = garages;
        this.pool = pool;
        this.balcony = balcony;
        this.elevator = elevator;
        this.barbecueGrill = barbecueGrill;
        this.value = value;
        this.financing = financing;
        this.financingValue = financingValue;
        this.barterVehicle = barterVehicle;
        this.barterVehicleValue = barterVehicleValue;
        this.barterProperty = barterProperty;
        this.barterPropertyValue = barterPropertyValue;
    }

    public Integer getId() {
        return id;
    }

    public Integer getPropertyId() {
        return propertyId;
    }

    public Integer getNeighborhoodId() {
        return neighborhoodId;
    }

    public PropertyType getType() {
        return type;
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

    public BigDecimal getValue() {
        return value;
    }

    public Boolean getFinancing() {
        return financing;
    }

    public BigDecimal getFinancingValue() {
        return financingValue;
    }

    public Boolean getBarterVehicle() {
        return barterVehicle;
    }

    public BigDecimal getBarterVehicleValue() {
        return barterVehicleValue;
    }

    public Boolean getBarterProperty() {
        return barterProperty;
    }

    public BigDecimal getBarterPropertyValue() {
        return barterPropertyValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public List<PropertyImage> getImages() {
        return images;
    }

    public void setImages(List<PropertyImage> images) {
        this.images = images;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaleResponseBody that = (SaleResponseBody) o;
        return pool == that.pool &&
                balcony == that.balcony &&
                elevator == that.elevator &&
                barbecueGrill == that.barbecueGrill &&
                financing == that.financing &&
                barterVehicle == that.barterVehicle &&
                barterProperty == that.barterProperty &&
                Objects.equals(id, that.id) &&
                Objects.equals(propertyId, that.propertyId) &&
                Objects.equals(neighborhoodId, that.neighborhoodId) &&
                type == that.type &&
                Objects.equals(dorms, that.dorms) &&
                Objects.equals(suites, that.suites) &&
                Objects.equals(bathrooms, that.bathrooms) &&
                Objects.equals(garages, that.garages) &&
                Objects.equals(value, that.value) &&
                Objects.equals(financingValue, that.financingValue) &&
                Objects.equals(barterVehicleValue, that.barterVehicleValue) &&
                Objects.equals(barterPropertyValue, that.barterPropertyValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, propertyId, neighborhoodId, type, dorms, suites, bathrooms, garages, pool, balcony, elevator, barbecueGrill, value, financing, financingValue, barterVehicle, barterVehicleValue, barterProperty, barterPropertyValue);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SaleResponseBody.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("propertyId=" + propertyId)
                .add("neighborhoodId=" + neighborhoodId)
                .add("type=" + type)
                .add("dorms=" + dorms)
                .add("suites=" + suites)
                .add("bathrooms=" + bathrooms)
                .add("garages=" + garages)
                .add("pool=" + pool)
                .add("balcony=" + balcony)
                .add("elevator=" + elevator)
                .add("barbecueGrill=" + barbecueGrill)
                .add("value=" + value)
                .add("financing=" + financing)
                .add("financingValue=" + financingValue)
                .add("barterVehicle=" + barterVehicle)
                .add("barterVehicleValue=" + barterVehicleValue)
                .add("barterProperty=" + barterProperty)
                .add("barterPropertyValue=" + barterPropertyValue)
                .toString();
    }
}

