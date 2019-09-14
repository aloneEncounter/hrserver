package com.base.bean;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class Position {
    private Long id;
    private String name;
    private Timestamp createDate;

    public Position() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        return name != null ? name.equals(position.name) : position.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    public Position(String name) {

        this.name = name;
    }

}
