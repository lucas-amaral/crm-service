package com.proposta.aceita.crmservice.entities.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.proposta.aceita.crmservice.entities.enums.Sex;
import com.proposta.aceita.crmservice.entities.enums.UserType;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;
import java.util.StringJoiner;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRequestBody {
    private final Integer id;
    @NotBlank
    private final String name;
    private final LocalDate dateOfBirth;
    @NotBlank
    @Email
    private final String email;
    @NotNull
    private final UserType type;
    @NotBlank
    private final String cpfCnpj;
    private final Sex sex;
    private final AddressRequestBody address;

    public UserRequestBody(@JsonProperty("id") Integer id,
                           @JsonProperty("name") String name,
                           @JsonProperty("dateOfBirth") LocalDate dateOfBirth,
                           @JsonProperty("email") String email,
                           @JsonProperty("type") UserType type,
                           @JsonProperty("cpfCnpj") String cpfCnpj,
                           @JsonProperty("sex") Sex sex,
                           @JsonProperty("address") AddressRequestBody address) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.type = type;
        this.cpfCnpj = cpfCnpj;
        this.sex = sex;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public UserType getType() {
        return type;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public Sex getSex() {
        return sex;
    }

    public AddressRequestBody getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRequestBody that = (UserRequestBody) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(dateOfBirth, that.dateOfBirth) &&
                Objects.equals(email, that.email) &&
                type == that.type &&
                Objects.equals(cpfCnpj, that.cpfCnpj) &&
                sex == that.sex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dateOfBirth, email, type, cpfCnpj, sex);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserRequestBody.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("dateOfBirth=" + dateOfBirth)
                .add("email='" + email + "'")
                .add("type=" + type)
                .add("cpfCnpj='" + cpfCnpj + "'")
                .add("sex=" + sex)
                .toString();
    }
}
