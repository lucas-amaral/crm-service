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
    private Integer id;
    @Column(nullable = false)
    private String name;
    private LocalDate dateOfBirth;
    @Column(nullable = false)
    private String email;
    @Enumerated(EnumType.STRING)
    private UserType type;
    private String cpfCnpj;
    @Enumerated(EnumType.STRING)
    private Sex sex;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
    @CreationTimestamp
    private LocalDateTime createdAt;

    public User() {
    }

    public User(Integer id, String name, LocalDate dateOfBirth, String email, UserType type, String cpfCnpj, Sex sex, Address address, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.type = type;
        this.cpfCnpj = cpfCnpj;
        this.sex = sex;
        this.address = address;
        this.createdAt = createdAt;
    }

    public static User from(UserRequestBody body, Address address) {
        return new User(body.getId(), body.getName(), body.getDateOfBirth(), body.getEmail(), body.getType(),
                body.getCpfCnpj(), body.getSex(), address, null);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(dateOfBirth, user.dateOfBirth) &&
                Objects.equals(email, user.email) &&
                type == user.type &&
                Objects.equals(cpfCnpj, user.cpfCnpj) &&
                sex == user.sex &&
                Objects.equals(address, user.address) &&
                Objects.equals(createdAt, user.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dateOfBirth, email, type, cpfCnpj, sex, address, createdAt);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("dateOfBirth=" + dateOfBirth)
                .add("email='" + email + "'")
                .add("type=" + type)
                .add("cpfCnpj='" + cpfCnpj + "'")
                .add("sex=" + sex)
                .add("address=" + address)
                .add("createdAt=" + createdAt)
                .toString();
    }
}
