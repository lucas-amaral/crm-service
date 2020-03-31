package com.proposta.aceita.crmservice.entities;

import com.proposta.aceita.crmservice.entities.req.SaleRequestBody;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.StringJoiner;

@Entity(name = "sales")
public class Sale {
    @Id
    private Integer id;
    @OneToOne
    private Property property;
    private BigDecimal value;
    private Boolean financing;
    private BigDecimal financingValue;
    private Boolean barterVehicle;
    private BigDecimal barterVehicleValue;
    private Boolean barterProperty;
    private BigDecimal barterPropertyValue;
    private LocalDate agencying;

    public Sale() {
    }

    public Sale(Integer id, Property property, BigDecimal value, Boolean financing, BigDecimal financingValue, Boolean barterVehicle, BigDecimal barterVehicleValue, Boolean barterProperty, BigDecimal barterPropertyValue, LocalDate agencying) {
        this.id = id;
        this.property = property;
        this.value = value;
        this.financing = financing;
        this.financingValue = financingValue;
        this.barterVehicle = barterVehicle;
        this.barterVehicleValue = barterVehicleValue;
        this.barterProperty = barterProperty;
        this.barterPropertyValue = barterPropertyValue;
        this.agencying = agencying;
    }

    public static Sale from(SaleRequestBody body, Property property) {
        return new Sale(body.getId(), property, body.getValue(), body.isFinancing(), body.getFinancingValue(),
                body.isBarterVehicle(), body.getBarterVehicleValue(), body.isBarterProperty(), body.getBarterPropertyValue(), body.getAgencying());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
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

    public Boolean getBarterVehicle() {
        return barterVehicle;
    }

    public void setBarterVehicle(Boolean barterVehicle) {
        this.barterVehicle = barterVehicle;
    }

    public BigDecimal getBarterVehicleValue() {
        return barterVehicleValue;
    }

    public void setBarterVehicleValue(BigDecimal barterVehicleValue) {
        this.barterVehicleValue = barterVehicleValue;
    }

    public Boolean getBarterProperty() {
        return barterProperty;
    }

    public void setBarterProperty(Boolean barterProperty) {
        this.barterProperty = barterProperty;
    }

    public BigDecimal getBarterPropertyValue() {
        return barterPropertyValue;
    }

    public void setBarterPropertyValue(BigDecimal barterPropertyValue) {
        this.barterPropertyValue = barterPropertyValue;
    }

    public LocalDate getAgencying() {
        return agencying;
    }

    public void setAgencying(LocalDate agencying) {
        this.agencying = agencying;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sale sale = (Sale) o;
        return Objects.equals(id, sale.id) &&
                Objects.equals(property, sale.property) &&
                Objects.equals(value, sale.value) &&
                Objects.equals(financing, sale.financing) &&
                Objects.equals(financingValue, sale.financingValue) &&
                Objects.equals(barterVehicle, sale.barterVehicle) &&
                Objects.equals(barterVehicleValue, sale.barterVehicleValue) &&
                Objects.equals(barterProperty, sale.barterProperty) &&
                Objects.equals(barterPropertyValue, sale.barterPropertyValue) &&
                Objects.equals(agencying, sale.agencying);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, property, value, financing, financingValue, barterVehicle, barterVehicleValue, barterProperty, barterPropertyValue, agencying);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Sale.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("property=" + property)
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
