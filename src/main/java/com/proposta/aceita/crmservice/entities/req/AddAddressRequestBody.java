package com.proposta.aceita.crmservice.entities.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Objects;
import java.util.StringJoiner;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddAddressRequestBody implements AddressRequestBody {
    @Pattern(regexp = "\\d{5}-\\d{3}")
    private String streetId;
    @NotEmpty
    private String number;
    private String complement;

    public AddAddressRequestBody(@JsonProperty("streetId") String streetId, @JsonProperty("number") String number, @JsonProperty("complement") String complement) {
        this.streetId = streetId;
        this.number = number;
        this.complement = complement;
    }

    public Integer getId() {
        return null;
    }

    public String getStreetId() {
        return streetId;
    }

    public String getNumber() {
        return number;
    }

    public String getComplement() {
        return complement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddAddressRequestBody that = (AddAddressRequestBody) o;
        return Objects.equals(streetId, that.streetId) &&
                Objects.equals(number, that.number) &&
                Objects.equals(complement, that.complement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(streetId, number, complement);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AddAddressRequestBody.class.getSimpleName() + "[", "]")
                .add("streetId=" + streetId)
                .add("number='" + number + "'")
                .add("complement='" + complement + "'")
                .toString();
    }
}
