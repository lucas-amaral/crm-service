package com.proposta.aceita.crmservice.entities;

import com.proposta.aceita.crmservice.entities.enums.BarterType;
import com.proposta.aceita.crmservice.entities.req.BarterRequestBody;
import com.proposta.aceita.crmservice.util.CheckUtils;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@Entity(name = "barters")
@TypeDef(name = "str-array", typeClass = StringArrayType.class)
public class Barter {
    @Id
    private Integer id;
    @ManyToOne
    private Interest interest;
    @Enumerated(EnumType.STRING)
    private BarterType type;
    private BigDecimal value;
    @Type(type = "str-array")
    @Column(columnDefinition = "STRING ARRAY")
    private List<String> images;

    public Barter() {
    }

    public Barter(Integer id, Interest interest, BarterType type, BigDecimal value) {
        this.id = id;
        this.interest = interest;
        this.type = type;
        this.value = value;
    }

    public static Barter from(BarterRequestBody body, Interest interest) {
        return new Barter(body.getId(), interest, body.getType(), body.getValue());
    }

    public static List<Barter> fromList(List<BarterRequestBody> body, Interest interest) {
        return (CheckUtils.listIsNullOrEmpty(body)) ? Collections.emptyList()
                : body.stream().map(g -> Barter.from(g, interest)).collect(Collectors.toList());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Barter barter = (Barter) o;
        return Objects.equals(id, barter.id) &&
                type == barter.type &&
                Objects.equals(value, barter.value) &&
                Objects.equals(images, barter.images);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, interest, type, value, images);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Barter.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("type=" + type)
                .add("value=" + value)
                .add("images=" + images)
                .toString();
    }
}
