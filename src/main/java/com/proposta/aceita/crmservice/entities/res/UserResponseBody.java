package com.proposta.aceita.crmservice.entities.res;

import java.util.Objects;
import java.util.StringJoiner;

public class UserResponseBody {
    private final String name;
    private final String email;


    public UserResponseBody(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserResponseBody that = (UserResponseBody) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserResponseBody.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("email='" + email + "'")
                .toString();
    }
}
