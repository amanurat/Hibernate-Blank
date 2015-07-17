package com.hibernate.annotation.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "EMPLOYEE")
@NamedQueries( {
        @NamedQuery(name = "FIND_ALL_EMPLOYEE", query = "from Employee"),
        @NamedQuery(name = "FIND_EMPLOYEE_BY_TONY", query = "from Employee e where e.firstName = :FIRST_NAME ")
})
@NamedNativeQueries({
        @NamedNativeQuery(name = "VIEW_EMPLOYEE", query = "select e.id as ID, e.FIRST_NAME as FIRST_NAME , d.name as DEPARTMENT  from EMPLOYEE  e LEFT JOIN DEPARTMENT d on (e.DEPARTMENT_ID = d.ID)", resultClass = EmployeeView.class)
})
//@AttributeOverride( name="createdDate", column = @Column(name="CREATED_DATE_TIME") )
public class Employee implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;


    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "EMPLOYEE_MEETING",
                joinColumns = { @JoinColumn(name = "EMPLOYEE_ID") },
                inverseJoinColumns = { @JoinColumn(name = "MEETING_ID")}
    )
    private Set<Meeting> meetings = new HashSet<Meeting>();


    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "AGE")
    private Integer age;

    @Column(name = "SEX")
    private String sex;

    @Column(name = "BIRTHDAY")
    private Date birthDay;

    @Column(name = "TEL")
    private String tel;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "SALARY")
    private Long salary;

    @Column(name = "CREATE_DATE")
    private Date createDateTime;


    @Column(name = "UPDATE_DATE")
    private Date updateDateTime;

    public Employee() {
    }

    public Employee(String firstName,String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public Employee(String firstName,String lastName, Department department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
    }

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Date getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(Date updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public Set<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(Set<Meeting> meetings) {
        this.meetings = meetings;
    }
}
