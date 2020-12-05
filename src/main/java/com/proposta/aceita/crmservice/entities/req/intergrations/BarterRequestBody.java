package com.proposta.aceita.crmservice.entities.req.intergrations;

import com.proposta.aceita.crmservice.entities.Barter;
import com.proposta.aceita.crmservice.entities.enums.BarterType;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.StringJoiner;

public class BarterRequestBody {
    private final Integer id;
    private final BarterType type;
    private final BigDecimal value;


    public BarterRequestBody(Integer id, BarterType type, BigDecimal value) {
        this.id = id;
        this.type = type;
        this.value = value;
    }

    public static BarterRequestBody of(Barter barter) {
        return new BarterRequestBody(barter.getId(), barter.getType(), barter.getValue());
    }

    public Integer getId() {
        return id;
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
        return Objects.equals(id, that.id) &&
                type == that.type &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, value);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BarterRequestBody.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("type=" + type)
                .add("value=" + value)
                .toString();
    }
}
