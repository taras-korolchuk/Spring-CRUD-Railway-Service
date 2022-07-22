package com.example.Lab1.model;
import com.example.Lab1.model.enums.RolesEnum;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

public class User {
    @Min(1)
    private Long id;

    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    private String password;

    @NotEmpty
    @NotNull
    private boolean active;

    @NotNull
    @NotEmpty
    private Set<RolesEnum> roles;

    public User (){}

    public User(@NotNull @NotEmpty String username,
                @NotNull @NotEmpty String password,
                @NotNull @NotEmpty Boolean active,
                @NotNull @NotEmpty Set<RolesEnum> roles) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<RolesEnum> getRoles() {
        return roles;
    }

    public void setRoles(Set<RolesEnum> roles) {
        this.roles = roles;
    }

}