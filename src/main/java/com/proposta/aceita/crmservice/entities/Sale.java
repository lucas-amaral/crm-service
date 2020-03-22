package com.proposta.aceita.crmservice.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.time.LocalDate;

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
}
