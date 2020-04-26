package com.proposta.aceita.crmservice.entities.res;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.StringJoiner;


public class NegotiationResponseBody {
    private final String id;
    private final InterestResponseBody interest;
    private final SaleResponseBody sale;
    private final LocalDateTime createdAt;

    public NegotiationResponseBody(@JsonProperty("id") String id,
                                   @JsonProperty("interest") InterestResponseBody interest,
                                   @JsonProperty("sale") SaleResponseBody sale,
                                   @JsonProperty("createdAt") LocalDateTime createdAt) {
        this.id = id;
        this.interest = interest;
        this.sale = sale;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public InterestResponseBody getInterest() {
        return interest;
    }

    public SaleResponseBody getSale() {
        return sale;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NegotiationResponseBody that = (NegotiationResponseBody) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(interest, that.interest) &&
                Objects.equals(sale, that.sale) &&
                Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, interest, sale, createdAt);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", NegotiationResponseBody.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("interest=" + interest)
                .add("sale=" + sale)
                .add("createdAt=" + createdAt)
                .toString();
    }
}

