package com.proposta.aceita.crmservice.entities.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Objects;
import java.util.StringJoiner;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EditAddressRequestBody implements AddressRequestBody {
    private Integer id;
    private String streetId;
        @NotEmpty
    private String number;
    private String complement;

    public EditAddressRequestBody(@JsonProperty("id") Integer id, @JsonProperty("streetId") String streetId, @JsonProperty("number") String number, @JsonProperty("complement") String complement) {
        this.id = id;
        this.streetId = streetId;
        this.number = number;
        this.complement = complement;
    }

    public Integer getId() {
        return id;
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
        EditAddressRequestBody that = (EditAddressRequestBody) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(streetId, that.streetId) &&
                Objects.equals(number, that.number) &&
                Objects.equals(complement, that.complement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, streetId, number, complement);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", EditAddressRequestBody.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("streetId=" + streetId)
                .add("number='" + number + "'")
                .add("complement='" + complement + "'")
                .toString();
    }
}
