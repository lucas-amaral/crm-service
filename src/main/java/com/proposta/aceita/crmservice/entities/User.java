package com.proposta.aceita.crmservice.entities;

import com.proposta.aceita.crmservice.entities.enums.Sex;
import com.proposta.aceita.crmservice.entities.enums.UserType;
import com.proposta.aceita.crmservice.entities.req.UserRequestBody;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.StringJoiner;

@Entity(name = "users")
public class User {
    @Id
    @Column(name = "username")
    private String username;
    @Column(nullable = false)
    private String name;
    private LocalDate dateOfBirth;
    @Enumerated(EnumType.STRING)
    private UserType type;
    private String cpfCnpj;
    @Enumerated(EnumType.STRING)
    private Sex sex;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
    private boolean enabled;
    @CreationTimestamp
    private LocalDateTime createdAt;

    public User() {
    }

    public User(String username, String name, LocalDate dateOfBirth, UserType type, String cpfCnpj, Sex sex, Address address, boolean enabled, LocalDateTime createdAt) {
        this.username = username;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.type = type;
        this.cpfCnpj = cpfCnpj;
        this.sex = sex;
        this.address = address;
        this.enabled = enabled;
        this.createdAt = createdAt;
    }

    public static User from(UserRequestBody body, Address address) {
        return new User(body.getUsername(), body.getName(), body.getDateOfBirth(), body.getType(),
                body.getCpfCnpj(), body.getSex(), address, true, null);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

     public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return enabled == user.enabled &&
                Objects.equals(username, user.username) &&
                Objects.equals(name, user.name) &&
                Objects.equals(dateOfBirth, user.dateOfBirth) &&
                type == user.type &&
                Objects.equals(cpfCnpj, user.cpfCnpj) &&
                sex == user.sex &&
                Objects.equals(address, user.address) &&
                Objects.equals(createdAt, user.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, name, dateOfBirth, type, cpfCnpj, sex, address, enabled, createdAt);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("username='" + username + "'")
                .add("name='" + name + "'")
                .add("dateOfBirth=" + dateOfBirth)
                .add("type=" + type)
                .add("cpfCnpj='" + cpfCnpj + "'")
                .add("sex=" + sex)
                .add("address=" + address)
                .add("enabled=" + enabled)
                .add("createdAt=" + createdAt)
                .toString();
    }
}
