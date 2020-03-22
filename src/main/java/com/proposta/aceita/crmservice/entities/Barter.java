package com.proposta.aceita.crmservice.entities;

import com.proposta.aceita.crmservice.entities.enums.BarterType;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

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
}
