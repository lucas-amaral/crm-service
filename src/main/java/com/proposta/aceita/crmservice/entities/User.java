package com.proposta.aceita.crmservice.entities;

import com.proposta.aceita.crmservice.entities.enums.Sex;
import com.proposta.aceita.crmservice.entities.enums.UserType;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;

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
    @OneToOne
    private Address address;
    @CreationTimestamp
    private OffsetDateTime createdAt;

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

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
