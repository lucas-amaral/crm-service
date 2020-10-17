package com.proposta.aceita.crmservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proposta.aceita.crmservice.entities.enums.BarterType;
import com.proposta.aceita.crmservice.entities.req.BarterRequestBody;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import static javax.persistence.GenerationType.*;

@Entity(name = "barters")
public class Barter {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    @ManyToOne
    private Interest interest;
    @Enumerated(EnumType.STRING)
    private BarterType type;
    private BigDecimal value;

    public Barter() {
    }

    public Barter(Integer id, Interest interest, BarterType type, BigDecimal value) {
        this.id = id;
        this.interest = interest;
        this.type = type;
        this.value = value;
    }

    public static Barter of(BarterRequestBody body, Interest interest) {
        return new Barter(body.getId(), interest, body.getType(), body.getValue());
    }

    public static List<Barter> ofList(List<? extends BarterRequestBody> body, Interest interest) {
        return (CollectionUtils.isEmpty(body)) ? Collections.emptyList()
                : body.stream().map(g -> Barter.of(g, interest)).collect(Collectors.toList());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonIgnore
    public Interest getInterest() {
        return interest;
    }

    public void setInterest(Interest interest) {
        this.interest = interest;
    }

    public BarterType getType() {
        return type;
    }

    public void setType(BarterType type) {
        this.type = type;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Barter barter = (Barter) o;
        return Objects.equals(id, barter.id) &&
                type == barter.type &&
                Objects.equals(value, barter.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, interest, type, value);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Barter.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("type=" + type)
                .add("value=" + value)
                .toString();
    }
}
