package com.example.Lab1.model;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Route {
    @Min(1)
    private Long id;

    @NotNull
    @NotEmpty
    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Route(String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }



    public Route() {
    }

}
