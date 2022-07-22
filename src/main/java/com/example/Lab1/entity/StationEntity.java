package com.example.Lab1.entity;




import com.example.Lab1.model.Station;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class StationEntity extends Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Override
    public Long getId() {
        return super.getId();
    }

    public StationEntity() {
        super();
    }

    public StationEntity(@NotNull @NotEmpty String name) {
        super(name);
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }

    @Column(nullable = false)
    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }
}
