package com.example.Lab1.model;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Station {
    @Min(1)
    private Long id;

    @NotNull
    @NotEmpty
    private String name;

    public Station() {
    }

    public Station(@NotNull @NotEmpty String name) {
        this.name = name;
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
}
