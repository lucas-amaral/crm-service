package com.proposta.aceita.crmservice.entities.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.proposta.aceita.crmservice.entities.Barter;
import com.proposta.aceita.crmservice.entities.BarterImage;
import com.proposta.aceita.crmservice.entities.enums.BarterType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class BarterResponseBody {
    private final Integer id;
    private final BarterType type;
    private final BigDecimal value;

    private List<BarterImage> images;

    public BarterResponseBody(@JsonProperty("id") Integer id, @JsonProperty("type") BarterType type, @JsonProperty("value") BigDecimal value) {
        this.id = id;
        this.type = type;
        this.value = value;
    }

    public static BarterResponseBody of(Barter barter) {
        return new BarterResponseBody(barter.getId(), barter.getType(), barter.getValue());
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

    public List<BarterImage> getImages() {
        return images;
    }

    public void setImages(List<BarterImage> images) {
        this.images = images;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BarterResponseBody that = (BarterResponseBody) o;
        return Objects.equals(id, that.id) &&
                type == that.type &&
                Objects.equals(value, that.value) &&
                Objects.equals(images, that.images);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, value, images);
    }

    @Override
    public String
    toString() {
        return new StringJoiner(", ", BarterResponseBody.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("type=" + type)
                .add("value=" + value)
                .add("images=" + images)
                .toString();
    }
}
