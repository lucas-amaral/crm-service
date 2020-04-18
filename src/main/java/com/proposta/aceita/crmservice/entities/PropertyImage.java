package com.proposta.aceita.crmservice.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Objects;
import java.util.StringJoiner;

@Document(collection = "property_images")
public class PropertyImage {

    @Id
    private String id;
    private final Integer propertyId;
    private final String contentType;
    private final String data;

    public PropertyImage(String id, Integer propertyId, String contentType, String data) {
        this.id = id;
        this.propertyId = propertyId;
        this.contentType = contentType;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public Integer getPropertyId() {
        return propertyId;
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
        PropertyImage propertyImage = (PropertyImage) o;
        return Objects.equals(id, propertyImage.id) &&
                Objects.equals(propertyId, propertyImage.propertyId) &&
                Objects.equals(contentType, propertyImage.contentType) &&
                Objects.equals(data, propertyImage.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, propertyId, contentType, data);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PropertyImage.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("propertyId='" + propertyId + "'")
                .add("contentType='" + contentType + "'")
                .add("data='" + data + "'")
                .toString();
    }
}
