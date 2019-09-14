package com.base.bean;

import lombok.Data;

import java.sql.Timestamp;

/**
 * 职级
 */
@Data
public class JobLevel {
    private Long id;
    private String name;
    private String titleLevel;
    private Timestamp createDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JobLevel jobLevel = (JobLevel) o;

        return name != null ? name.equals(jobLevel.name) : jobLevel.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    public JobLevel() {

    }

    public JobLevel(String name) {

        this.name = name;
    }

}
