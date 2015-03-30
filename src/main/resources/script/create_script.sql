# DROP TABLE Department;
CREATE TABLE Department ( DEPARTMENT_ID int NOT NULL AUTO_INCREMENT, DEPARTMENT_NAME varchar(255), PRIMARY KEY (DEPARTMENT_ID), CONSTRAINT DEPARTMENT_NAME UNIQUE (DEPARTMENT_NAME) ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
insert into Department (DEPARTMENT_ID, DEPARTMENT_NAME) values (2, 'HR');
insert into Department (DEPARTMENT_ID, DEPARTMENT_NAME) values (1, 'IT');
# DROP TABLE EMPLOYEE;
CREATE TABLE EMPLOYEE ( EMPLOYEE_ID int NOT NULL AUTO_INCREMENT, ADDRESS varchar(255), AGE int, BIRTH_DATE datetime, CREATE_DATE datetime, EMAIL varchar(255), FIRST_NAME varchar(255), LAST_NAME varchar(255), PHONE_NUMBER varchar(255), POSITION varchar(255), SALARY int, SEX int, UPDATE_DATE datetime, DEPARTMENT_ID int, PRIMARY KEY (EMPLOYEE_ID), CONSTRAINT FK75C8D6AEC6B00FAF FOREIGN KEY (DEPARTMENT_ID) REFERENCES Department (DEPARTMENT_ID), INDEX FK75C8D6AEC6B00FAF (DEPARTMENT_ID) ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
insert into EMPLOYEE (EMPLOYEE_ID, ADDRESS, AGE, BIRTH_DATE, CREATE_DATE, EMAIL, FIRST_NAME, LAST_NAME, PHONE_NUMBER, POSITION, SALARY, SEX, UPDATE_DATE, DEPARTMENT_ID) values (1, 'USA', 20, '2014-11-09 00:44:04', '2014-11-09 00:44:04', 'john@gmail.com', 'John', 'Sutton', '08912345678', 'Director', 200000, 0, null, 1);
insert into EMPLOYEE (EMPLOYEE_ID, ADDRESS, AGE, BIRTH_DATE, CREATE_DATE, EMAIL, FIRST_NAME, LAST_NAME, PHONE_NUMBER, POSITION, SALARY, SEX, UPDATE_DATE, DEPARTMENT_ID) values (2, 'Bangkok', 20, '2014-11-09 00:44:04', '2014-11-09 00:44:04', 'damian@gmail.com', 'Damian', 'Sutton', '08187654321', 'Manager', 100000, 0, null, 2);
# DROP TABLE STUDENT;


CREATE TABLE STUDENT ( STUDENT_ID bigint NOT NULL AUTO_INCREMENT, STUDENT_NAME varchar(100) NOT NULL, EMAIL varchar(100) NOT NULL, PRIMARY KEY (STUDENT_ID) ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
insert into STUDENT (STUDENT_ID, STUDENT_NAME, EMAIL) values (1, 'first', 'first@gmail.com');
insert into STUDENT (STUDENT_ID, STUDENT_NAME, EMAIL) values (2, 'second', 'second@gmail.com');

