package com.proposta.aceita.crmservice.entities.req.match;

import com.proposta.aceita.crmservice.entities.Barter;
import com.proposta.aceita.crmservice.entities.enums.BarterType;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.StringJoiner;

public class MatchBarterRequestBody {
    private final BarterType type;
    private final BigDecimal value;


    public MatchBarterRequestBody(BarterType type, BigDecimal value) {
        this.type = type;
        this.value = value;
    }

    public static MatchBarterRequestBody of(Barter barter) {
        return new MatchBarterRequestBody(barter.getType(), barter.getValue());
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
        MatchBarterRequestBody that = (MatchBarterRequestBody) o;
        return type == that.type &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, value);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MatchBarterRequestBody.class.getSimpleName() + "[", "]")
                .add("type=" + type)
                .add("value=" + value)
                .toString();
    }
}
