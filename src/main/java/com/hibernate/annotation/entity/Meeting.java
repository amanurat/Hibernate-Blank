package com.hibernate.annotation.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "MEETING")
public class Meeting implements Serializable {


    @Id
    @Column(name = "MEETING_ID")
    @GeneratedValue
    private Integer id;

    @Column(name = "SUBJECT")
    private String subject;

    @Column(name = "MEETING_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date meetingDate;


    @ManyToMany(mappedBy = "meetings")
    private Set<Employee> employees = new HashSet<Employee>();

    public Meeting(){

    }


    public Meeting(String subject) {
        this.subject = subject;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(Date meetingDate) {
        this.meetingDate = meetingDate;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}
