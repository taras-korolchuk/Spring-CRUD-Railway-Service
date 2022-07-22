package com.example.Lab1.entity;

import com.example.Lab1.model.User;
import com.example.Lab1.model.enums.RolesEnum;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class UserEntity extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Override
    public Long getId() {
        return super.getId();
    }

    public UserEntity() {
    }

    public UserEntity(@NotNull @NotEmpty String username,
                      @NotNull @NotEmpty String password,
                      @NotNull @NotEmpty Boolean active,
                      @NotNull @NotEmpty Set<RolesEnum> roles) {
        super(username, password, active, roles);
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }

    @Column(length = 60, nullable = false, unique = true)
    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public void setUsername(String username) {
        super.setUsername(username);
    }

    @Column(nullable = false)
    @Range(min = 6, max = 24)
    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    @Column(nullable = false)
    @Override
    public boolean isActive() {
        return super.isActive();
    }

    @Override
    public void setActive(boolean active) {
        super.setActive(active);
    }

    @ElementCollection(targetClass = RolesEnum.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Override
    public Set<RolesEnum> getRoles() {
        return super.getRoles();
    }

    @Override
    public void setRoles(Set<RolesEnum> roles) {
        super.setRoles(roles);
    }
}
