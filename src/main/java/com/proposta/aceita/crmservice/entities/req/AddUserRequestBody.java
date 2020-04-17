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
public class AddUserRequestBody implements UserRequestBody {
    @NotBlank
    @Email
    private final String username;
    @NotBlank
    private final String password;
    @NotBlank
    private final String name;
    private final LocalDate dateOfBirth;
    @NotNull
    private final UserType type;
    @NotBlank
    private final String cpfCnpj;
    private final Sex sex;
    private final AddressRequestBody address;

    public AddUserRequestBody(@JsonProperty("username") String username,
                              @JsonProperty("password") String password,
                              @JsonProperty("name") String name,
                              @JsonProperty("dateOfBirth") LocalDate dateOfBirth,
                              @JsonProperty("type") UserType type,
                              @JsonProperty("cpfCnpj") String cpfCnpj,
                              @JsonProperty("sex") Sex sex,
                              @JsonProperty("address") AddAddressRequestBody address) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.type = type;
        this.cpfCnpj = cpfCnpj;
        this.sex = sex;
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
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
        AddUserRequestBody that = (AddUserRequestBody) o;
        return Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(name, that.name) &&
                Objects.equals(dateOfBirth, that.dateOfBirth) &&
                type == that.type &&
                Objects.equals(cpfCnpj, that.cpfCnpj) &&
                sex == that.sex &&
                Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, name, dateOfBirth, type, cpfCnpj, sex, address);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AddUserRequestBody.class.getSimpleName() + "[", "]")
                .add("username='" + username + "'")
                .add("password='" + password + "'")
                .add("name='" + name + "'")
                .add("dateOfBirth=" + dateOfBirth)
                .add("type=" + type)
                .add("cpfCnpj='" + cpfCnpj + "'")
                .add("sex=" + sex)
                .add("address=" + address)
                .toString();
    }
}
