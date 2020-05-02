package com.proposta.aceita.crmservice.entities.req.intergrations;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.StringJoiner;


public class NegotiationRequestBody {
    private final String id;
    private final InterestRequestBody interest;
    private final SaleRequestBody sale;
    private final LocalDateTime createdAt;

    public NegotiationRequestBody(String id, InterestRequestBody interest, SaleRequestBody sale, LocalDateTime createdAt) {
        this.id = id;
        this.interest = interest;
        this.sale = sale;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public InterestRequestBody getInterest() {
        return interest;
    }

    public SaleRequestBody getSale() {
        return sale;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NegotiationRequestBody that = (NegotiationRequestBody) o;
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
        return new StringJoiner(", ", NegotiationRequestBody.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("interest=" + interest)
                .add("sale=" + sale)
                .add("createdAt=" + createdAt)
                .toString();
    }
}

