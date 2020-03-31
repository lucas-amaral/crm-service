package com.proposta.aceita.crmservice.entities.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.StringJoiner;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EditSaleRequestBody implements SaleRequestBody {
    @NotNull
    private final Integer id;
    @NotNull
    private final Integer propertyId;
    @NotNull
    private final BigDecimal value;
    private final boolean financing;
    private final BigDecimal financingValue;
    private final boolean barterVehicle;
    private final BigDecimal barterVehicleValue;
    private final boolean barterProperty;
    private final BigDecimal barterPropertyValue;
    private final LocalDate agencying;

    public EditSaleRequestBody(@JsonProperty("id") Integer id,
                               @JsonProperty("propertyId") Integer propertyId,
                               @JsonProperty("value") BigDecimal value,
                               @JsonProperty("financing") boolean financing,
                               @JsonProperty("financingValue") BigDecimal financingValue,
                               @JsonProperty("barterVehicle") boolean barterVehicle,
                               @JsonProperty("barterVehicleValue") BigDecimal barterVehicleValue,
                               @JsonProperty("barterProperty") boolean barterProperty,
                               @JsonProperty("barterPropertyValue") BigDecimal barterPropertyValue,
                               @JsonProperty("agencying") LocalDate agencying) {
        this.id = id;
        this.propertyId = propertyId;
        this.value = value;
        this.financing = financing;
        this.financingValue = financingValue;
        this.barterVehicle = barterVehicle;
        this.barterVehicleValue = barterVehicleValue;
        this.barterProperty = barterProperty;
        this.barterPropertyValue = barterPropertyValue;
        this.agencying = agencying;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public Integer getPropertyId() {
        return propertyId;
    }

    @Override
    public BigDecimal getValue() {
        return value;
    }

    public boolean isFinancing() {
        return financing;
    }

    @Override
    public BigDecimal getFinancingValue() {
        return financingValue;
    }

    public boolean isBarterVehicle() {
        return barterVehicle;
    }

    @Override
    public BigDecimal getBarterVehicleValue() {
        return barterVehicleValue;
    }

    public boolean isBarterProperty() {
        return barterProperty;
    }

    @Override
    public BigDecimal getBarterPropertyValue() {
        return barterPropertyValue;
    }

    @Override
    public LocalDate getAgencying() {
        return agencying;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EditSaleRequestBody that = (EditSaleRequestBody) o;
        return financing == that.financing &&
                barterVehicle == that.barterVehicle &&
                barterProperty == that.barterProperty &&
                Objects.equals(id, that.id) &&
                Objects.equals(propertyId, that.propertyId) &&
                Objects.equals(value, that.value) &&
                Objects.equals(financingValue, that.financingValue) &&
                Objects.equals(barterVehicleValue, that.barterVehicleValue) &&
                Objects.equals(barterPropertyValue, that.barterPropertyValue) &&
                Objects.equals(agencying, that.agencying);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, propertyId, value, financing, financingValue, barterVehicle, barterVehicleValue, barterProperty, barterPropertyValue, agencying);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", EditSaleRequestBody.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("propertyId=" + propertyId)
                .add("value=" + value)
                .add("financing=" + financing)
                .add("financingValue=" + financingValue)
                .add("barterVehicle=" + barterVehicle)
                .add("barterVehicleValue=" + barterVehicleValue)
                .add("barterProperty=" + barterProperty)
                .add("barterPropertyValue=" + barterPropertyValue)
                .add("agencying=" + agencying)
                .toString();
    }
}
