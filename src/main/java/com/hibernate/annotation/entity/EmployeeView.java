package com.hibernate.annotation.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by amanurat on 4/1/15 AD.
 */
@Entity
public class EmployeeView  {


    @Id
    private Integer id;


    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "DEPARTMENT")
    private String department;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
