package com.proposta.aceita.crmservice.entities.req;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.proposta.aceita.crmservice.entities.enums.State;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.StringJoiner;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CityRequestBody {
    private final Integer id;
    @NotBlank
    private final String name;
    @NotNull
    private final State state;

    @JsonCreator
    public CityRequestBody(@JsonProperty("id") Integer id, @JsonProperty("name") String name, @JsonProperty("state") State state) {
        this.id = id;
        this.name = name;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public State getState() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityRequestBody that = (CityRequestBody) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                state == that.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, state);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CityRequestBody.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("state=" + state)
                .toString();
    }
}
