package com.proposta.aceita.crmservice.entities.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.proposta.aceita.crmservice.entities.enums.BarterType;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.StringJoiner;

public class AddBarterRequestBody implements BarterRequestBody{
    private final Integer interestId;
    @NotNull
    private final BarterType type;
    @NotNull
    private final BigDecimal value;

    public AddBarterRequestBody(@JsonProperty("interestId") Integer interestId, @JsonProperty("type") BarterType type, @JsonProperty("value") BigDecimal value) {
        this.interestId = interestId;
        this.type = type;
        this.value = value;
    }

    @Override
    public Integer getId() {
        return null;
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
        AddBarterRequestBody that = (AddBarterRequestBody) o;
        return Objects.equals(interestId, that.interestId) &&
                type == that.type &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(interestId, type, value);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AddBarterRequestBody.class.getSimpleName() + "[", "]")
                .add("interestId=" + interestId)
                .add("type=" + type)
                .add("value=" + value)
                .toString();
    }
}
