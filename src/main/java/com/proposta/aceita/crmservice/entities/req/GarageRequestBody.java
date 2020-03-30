package com.proposta.aceita.crmservice.entities.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.StringJoiner;


@JsonIgnoreProperties(ignoreUnknown = true)
public class GarageRequestBody {
    private final Integer id;
    @NotNull
    private final Integer box;
    private final String registration;

    public GarageRequestBody(@JsonProperty("id") Integer id, @JsonProperty("box") Integer box, @JsonProperty("registration") String registration) {
        this.id = id;
        this.box = box;
        this.registration = registration;
    }

    public Integer getId() {
        return id;
    }

    public Integer getBox() {
        return box;
    }

    public String getRegistration() {
        return registration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GarageRequestBody that = (GarageRequestBody) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(box, that.box) &&
                Objects.equals(registration, that.registration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, box, registration);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", GarageRequestBody.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("box=" + box)
                .add("registration='" + registration + "'")
                .toString();
    }
}
