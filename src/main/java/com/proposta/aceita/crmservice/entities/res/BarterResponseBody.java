package com.proposta.aceita.crmservice.entities.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.proposta.aceita.crmservice.entities.Barter;
import com.proposta.aceita.crmservice.entities.enums.BarterType;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.StringJoiner;

public class BarterResponseBody {
    private final BarterType type;
    private final BigDecimal value;


    public BarterResponseBody(@JsonProperty("type") BarterType type, @JsonProperty("value") BigDecimal value) {
        this.type = type;
        this.value = value;
    }

    public static BarterResponseBody of(Barter barter) {
        return new BarterResponseBody(barter.getType(), barter.getValue());
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
        BarterResponseBody that = (BarterResponseBody) o;
        return type == that.type &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, value);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BarterResponseBody.class.getSimpleName() + "[", "]")
                .add("type=" + type)
                .add("value=" + value)
                .toString();
    }
}
