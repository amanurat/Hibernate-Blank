
    alter table EMPLOYEE 
        drop 
        foreign key FK75C8D6AEC6B00FAF;

    alter table EMPLOYEE_MEETING 
        drop 
        foreign key FK1897C58A90B120C5;

    alter table EMPLOYEE_MEETING 
        drop 
        foreign key FK1897C58A6B114B6F;

    drop table if exists DEPARTMENT;

    drop table if exists EMPLOYEE;

    drop table if exists EMPLOYEE_MEETING;

    drop table if exists EmployeeView;

    drop table if exists MEETING;

    drop table if exists STUDENT;

    create table DEPARTMENT (
        ID integer not null auto_increment,
        name varchar(255),
        primary key (ID)
    );

    create table EMPLOYEE (
        id integer not null auto_increment,
        AGE integer,
        BIRTHDAY datetime,
        CREATE_DATE datetime,
        EMAIL varchar(255),
        FIRST_NAME varchar(255) not null,
        LAST_NAME varchar(255) not null,
        SALARY bigint,
        SEX varchar(255),
        TEL varchar(255),
        UPDATE_DATE datetime,
        DEPARTMENT_ID integer,
        primary key (id)
    );

    create table EMPLOYEE_MEETING (
        EMPLOYEE_ID integer not null,
        MEETING_ID integer not null,
        primary key (EMPLOYEE_ID, MEETING_ID)
    );

    create table EmployeeView (
        id integer not null,
        DEPARTMENT varchar(255),
        FIRST_NAME varchar(255),
        primary key (id)
    );

    create table MEETING (
        MEETING_ID integer not null auto_increment,
        MEETING_DATE datetime,
        SUBJECT varchar(255),
        primary key (MEETING_ID)
    );

    create table STUDENT (
        STUDENT_ID bigint not null auto_increment,
        STUDENT_NAME varchar(100) not null,
        EMAIL varchar(100) not null,
        primary key (STUDENT_ID)
    );

    alter table EMPLOYEE 
        add index FK75C8D6AEC6B00FAF (DEPARTMENT_ID), 
        add constraint FK75C8D6AEC6B00FAF 
        foreign key (DEPARTMENT_ID) 
        references DEPARTMENT (ID);

    alter table EMPLOYEE_MEETING 
        add index FK1897C58A90B120C5 (MEETING_ID), 
        add constraint FK1897C58A90B120C5 
        foreign key (MEETING_ID) 
        references MEETING (MEETING_ID);

    alter table EMPLOYEE_MEETING 
        add index FK1897C58A6B114B6F (EMPLOYEE_ID), 
        add constraint FK1897C58A6B114B6F 
        foreign key (EMPLOYEE_ID) 
        references EMPLOYEE (id);
