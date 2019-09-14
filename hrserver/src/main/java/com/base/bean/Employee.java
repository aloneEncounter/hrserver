package com.base.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private Long id;
    private String name;
    private String gender;
    private Date birthday;
    private String idCard;
    private String wedlock;
    private Long nationId;
    private String nationName;
    private String nativePlace;
    private String politicName;
    private Long politicId;
    private String email;
    private String phone;
    private String address;
    private Long departmentId;
    private String departmentName;
    private Long jobLevelId;
    private String jobLevelName;
    private Long posId;
    private String posName;
    private String engageForm;
    private String tiptopDegree;
    private String specialty;
    private String school;
    private Date beginDate;
    private String workState;
    private String workID;
    private Double contractTerm;
    private Date conversionTime;
    private Date notWorkDate;
    private Date beginContract;
    private Date endContract;
    private Integer workAge;
    private Department department;
    private Nation nation;
    private JobLevel jobLevel;
    private Position position;
    private PoliticsStatus politicsStatus;
    private Salary salary;

    @JsonIgnore
    public Long getNationId() {
        return nationId;
    }

    public void setNationId(Long nationId) {
        this.nationId = nationId;
    }

    @JsonIgnore
    public String getNationName() {
        return nationName;
    }

    public void setNationName(String nationName) {
        this.nationName = nationName;
    }


    @JsonIgnore
    public String getPoliticName() {
        return politicName;
    }

    public void setPoliticName(String politicName) {
        this.politicName = politicName;
    }

    @JsonIgnore
    public Long getPoliticId() {
        return politicId;
    }

    public void setPoliticId(Long politicId) {
        this.politicId = politicId;
    }



    @JsonIgnore
    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    @JsonIgnore
    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @JsonIgnore
    public Long getJobLevelId() {
        return jobLevelId;
    }

    public void setJobLevelId(Long jobLevelId) {
        this.jobLevelId = jobLevelId;
    }

    @JsonIgnore
    public String getJobLevelName() {
        return jobLevelName;
    }

    public void setJobLevelName(String jobLevelName) {
        this.jobLevelName = jobLevelName;
    }

    @JsonIgnore
    public Long getPosId() {
        return posId;
    }

    public void setPosId(Long posId) {
        this.posId = posId;
    }


}