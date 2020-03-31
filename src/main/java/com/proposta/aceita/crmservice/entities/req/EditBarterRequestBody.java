package com.proposta.aceita.crmservice.entities.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.proposta.aceita.crmservice.entities.enums.BarterType;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.StringJoiner;

public class EditBarterRequestBody implements BarterRequestBody{
    @NotNull
    private final Integer id;
    @NotNull
    private final Integer interestId;
    @NotNull
    private final BarterType type;
    @NotNull
    private final BigDecimal value;

    public EditBarterRequestBody(@JsonProperty("id") Integer id, @JsonProperty("interestId") Integer interestId, @JsonProperty("type") BarterType type, @JsonProperty("value") BigDecimal value) {
        this.id = id;
        this.interestId = interestId;
        this.type = type;
        this.value = value;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public Integer getInterestId() {
        return interestId;
    }

    public BarterType getType() {
        return type;
    }

    public BigDecimal getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EditBarterRequestBody that = (EditBarterRequestBody) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(interestId, that.interestId) &&
                type == that.type &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, interestId, type, value);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", EditBarterRequestBody.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("interestId=" + interestId)
                .add("type=" + type)
                .add("value=" + value)
                .toString();
    }
}
