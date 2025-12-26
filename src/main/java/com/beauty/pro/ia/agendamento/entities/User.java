package com.beauty.pro.ia.agendamento.entities;

import com.beauty.pro.ia.agendamento.enums.UserPlan;
import com.beauty.pro.ia.agendamento.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "tb_user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Email
    @Column(nullable = false)
    private String email;


    @Column(nullable = false)
    private String password_hash;

    @CPF
    @Column(length = 11, nullable = false, unique = true)
    private String cpf;

    private Integer userRole;
    private Integer userPlan;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant created_at;

    public User() {
    }

    public User(Long id, String name, String email, String password_hash, String cpf, UserRole userRole, UserPlan userPlan, Instant created_at) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password_hash = password_hash;
        this.cpf = cpf;
        setUserRoleCode(userRole);
        setUserPlanCode(userPlan);
        this.created_at = created_at;
    }

    public void setUserRoleCode(UserRole userRole) {
        if (userRole != null) {
            this.userRole = userRole.getCode();
        }
    }

    public void setUserPlanCode(UserPlan userPlan) {
        if (userPlan != null) {
            this.userPlan = userPlan.getCode();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    public Integer getUserPlan() {
        return userPlan;
    }

    public void setUserPlan(Integer userPlan) {
        this.userPlan = userPlan;
    }

    public Instant getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Instant created_at) {
        this.created_at = created_at;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
