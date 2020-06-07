package com.proposta.aceita.crmservice.entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

public class AuthorityId implements Serializable {
    private String username;
    private com.proposta.aceita.crmservice.entities.enums.Authority authority;

    public AuthorityId() {
    }

    public AuthorityId(String username, com.proposta.aceita.crmservice.entities.enums.Authority authority) {
        this.username = username;
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorityId authority1 = (AuthorityId) o;
        return Objects.equals(username, authority1.username) &&
                authority == authority1.authority;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, authority);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AuthorityId.class.getSimpleName() + "[", "]")
                .add("username='" + username + "'")
                .add("authority=" + authority)
                .toString();
    }
}
