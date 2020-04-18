package com.proposta.aceita.crmservice.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Objects;
import java.util.StringJoiner;

@Document(collection = "barter_images")
public class BarterImage {

    @Id
    private String id;
    private final Integer barterId;
    private final String contentType;
    private final String data;

    public BarterImage(String id, Integer barterId, String contentType, String data) {
        this.id = id;
        this.barterId = barterId;
        this.contentType = contentType;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public Integer getBarterId() {
        return barterId;
    }

    public String getContentType() {
        return contentType;
    }

    public String getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BarterImage barterImage = (BarterImage) o;
        return Objects.equals(id, barterImage.id) &&
                Objects.equals(barterId, barterImage.barterId) &&
                Objects.equals(contentType, barterImage.contentType) &&
                Objects.equals(data, barterImage.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, barterId, contentType, data);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BarterImage.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("barterId='" + barterId + "'")
                .add("contentType='" + contentType + "'")
                .add("data='" + data + "'")
                .toString();
    }
}
