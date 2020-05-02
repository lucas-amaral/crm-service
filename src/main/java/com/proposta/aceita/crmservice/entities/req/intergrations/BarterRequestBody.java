package com.proposta.aceita.crmservice.entities.req.intergrations;

import com.proposta.aceita.crmservice.entities.Barter;
import com.proposta.aceita.crmservice.entities.enums.BarterType;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.StringJoiner;

public class BarterRequestBody {
    private final BarterType type;
    private final BigDecimal value;


    public BarterRequestBody(BarterType type, BigDecimal value) {
        this.type = type;
        this.value = value;
    }

    public static BarterRequestBody of(Barter barter) {
        return new BarterRequestBody(barter.getType(), barter.getValue());
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
        BarterRequestBody that = (BarterRequestBody) o;
        return type == that.type &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, value);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BarterRequestBody.class.getSimpleName() + "[", "]")
                .add("type=" + type)
                .add("value=" + value)
                .toString();
    }
}
