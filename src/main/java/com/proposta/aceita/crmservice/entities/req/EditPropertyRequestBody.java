package com.proposta.aceita.crmservice.entities.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.proposta.aceita.crmservice.entities.enums.PropertyType;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EditPropertyRequestBody implements PropertyRequestBody {
    @NotNull
    private final Integer id;
    @NotNull
    private final String username;
    private final String description;
    @NotNull
    private final PropertyType type;
    private final BigDecimal area;
    private final String registration;
    @NotNull
    private final AddressRequestBody address;
    private final String iptu;
    private final Integer dorms;
    private final Integer suites;
    private final Integer bathrooms;
    private final Boolean pool;
    private final Boolean balcony;
    private final Boolean elevator;
    private final Boolean barbecueGrill;
    private final List<GarageRequestBody> garages;
    private final Boolean enable;

    public EditPropertyRequestBody(@JsonProperty("id") Integer id,
                                   @JsonProperty("username") String username,
                                   @JsonProperty("description") String description,
                                   @JsonProperty("type") PropertyType type,
                                   @JsonProperty("area") BigDecimal area,
                                   @JsonProperty("registration") String registration,
                                   @JsonProperty("address") EditAddressRequestBody address,
                                   @JsonProperty("iptu") String iptu,
                                   @JsonProperty("dorms") Integer dorms,
                                   @JsonProperty("suites") Integer suites,
                                   @JsonProperty("bathrooms") Integer bathrooms,
                                   @JsonProperty("pool") Boolean pool,
                                   @JsonProperty("balcony") Boolean balcony,
                                   @JsonProperty("elevator") Boolean elevator,
                                   @JsonProperty("barbecueGrill") Boolean barbecueGrill,
                                   @JsonProperty("garages") List<GarageRequestBody> garages,
                                   @JsonProperty("enable") Boolean enable) {
        this.id = id;
        this.username = username;
        this.description = description;
        this.type = type;
        this.area = area;
        this.registration = registration;
        this.address = address;
        this.iptu = iptu;
        this.dorms = dorms;
        this.suites = suites;
        this.bathrooms = bathrooms;
        this.pool = pool;
        this.balcony = balcony;
        this.elevator = elevator;
        this.barbecueGrill = barbecueGrill;
        this.garages = garages;
        this.enable = (null == enable) ? true : enable;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getDescription() {
        return description;
    }

    public PropertyType getType() {
        return type;
    }

    public BigDecimal getArea() {
        return area;
    }

    public String getRegistration() {
        return registration;
    }

    public AddressRequestBody getAddress() {
        return address;
    }

    public String getIptu() {
        return iptu;
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

    public List<GarageRequestBody> getGarages() {
        return garages;
    }

    public Boolean getEnable() {
        return enable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EditPropertyRequestBody that = (EditPropertyRequestBody) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(username, that.username) &&
                Objects.equals(description, that.description) &&
                type == that.type &&
                Objects.equals(area, that.area) &&
                Objects.equals(registration, that.registration) &&
                Objects.equals(address, that.address) &&
                Objects.equals(iptu, that.iptu) &&
                Objects.equals(dorms, that.dorms) &&
                Objects.equals(suites, that.suites) &&
                Objects.equals(bathrooms, that.bathrooms) &&
                Objects.equals(pool, that.pool) &&
                Objects.equals(balcony, that.balcony) &&
                Objects.equals(elevator, that.elevator) &&
                Objects.equals(barbecueGrill, that.barbecueGrill) &&
                Objects.equals(garages, that.garages) &&
                Objects.equals(enable, that.enable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, description, type, area, registration, address, iptu, dorms, suites, bathrooms, pool, balcony, elevator, barbecueGrill, garages, enable);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", EditPropertyRequestBody.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("username=" + username)
                .add("description='" + description + "'")
                .add("type=" + type)
                .add("area=" + area)
                .add("registration=" + registration)
                .add("address=" + address)
                .add("iptu=" + iptu)
                .add("dorms=" + dorms)
                .add("suites=" + suites)
                .add("bathrooms=" + bathrooms)
                .add("pool=" + pool)
                .add("balcony=" + balcony)
                .add("elevator=" + elevator)
                .add("barbecueGrill=" + barbecueGrill)
                .add("garages=" + garages)
                .add("enable=" + enable)
                .toString();
    }
}
