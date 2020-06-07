package com.proposta.aceita.crmservice.entities;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;

import java.util.Objects;
import java.util.StringJoiner;

import static com.proposta.aceita.crmservice.entities.enums.Authority.USER;
import static javax.persistence.EnumType.STRING;

@Entity(name = "authorities")
@IdClass(AuthorityId.class)
public class Authority {
    @Id
    private String username;
    @Id
    @Enumerated(STRING)
    private com.proposta.aceita.crmservice.entities.enums.Authority authority;

    public Authority() {
    }

    public Authority(String username, com.proposta.aceita.crmservice.entities.enums.Authority authority) {
        this.username = username;
        this.authority = authority;
    }

    public static Authority ofUser(String username) {
        return new Authority(username, USER);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public com.proposta.aceita.crmservice.entities.enums.Authority getAuthority() {
        return authority;
    }

    public void setAuthority(com.proposta.aceita.crmservice.entities.enums.Authority authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authority authority1 = (Authority) o;
        return Objects.equals(username, authority1.username) &&
                authority == authority1.authority;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, authority);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Authority.class.getSimpleName() + "[", "]")
                .add("username='" + username + "'")
                .add("authority=" + authority)
                .toString();
    }
}
