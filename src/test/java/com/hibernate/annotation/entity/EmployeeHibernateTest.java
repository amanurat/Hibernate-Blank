package com.hibernate.annotation.entity;

import com.hibernate.annotation.utils.MyInterceptor;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.assertEquals;


public class EmployeeHibernateTest {


    private SessionFactory sessionFactory;

    private Session session;

    @Before
    public void setup() throws Exception {
        Configuration configuration = new Configuration().configure();
        sessionFactory = configuration
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Meeting.class)
                .addAnnotatedClass(Department.class)
                .addAnnotatedClass(EmployeeView.class)
                .setInterceptor(new MyInterceptor())
                .buildSessionFactory();

        new SchemaExport(configuration).create(true, true);
        this.session = sessionFactory.openSession();
    }

    @After
    public void tearDown() throws Exception {
        this.session.close();
    }


    @Test
    @Ignore
    public void testCreateEmployee() throws Exception {
        session.getTransaction().begin();

        session.save(new Department("IT"));
        session.save(new Department("HR"));


        session.save(new Meeting("Java1"));
        session.save(new Meeting("Java2"));
        session.save(new Meeting(".Net"));
        session.save(new Meeting("SAP"));


        List<Meeting> meetingJava = new ArrayList<Meeting>();
        meetingJava.add((Meeting)session.get(Meeting.class, 1));
        meetingJava.add((Meeting)session.get(Meeting.class, 2));

        List<Meeting> meetingNetAndSap = session.createQuery("from Meeting m where m.subject like '%Net%' or m.subject like 'SAP'").list();



        Employee employee = new Employee();
        employee.setFirstName("Tony");
        employee.setLastName("Ja");
        employee.setAge(30);
        employee.setEmail("callme@ja.com");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date birthDay = formatter.parse("01/10/1980");
        employee.setBirthDay(birthDay);
        employee.setCreateDateTime(new Date());

        Department department1 = (Department) session.get(Department.class, 1);
        employee.setDepartment(department1);

        Set<Meeting> meetings = new HashSet<Meeting>();
        meetings.add(new Meeting("Java"));
        meetings.add(new Meeting(".Net"));
//        employee.setMeetings(meetings);

        employee.setMeetings(new HashSet<Meeting>(meetingJava));
        session.save(employee);

        Employee employee2 = new Employee();
        employee2.setFirstName("Jeng");
        employee2.setLastName("kung");
        employee2.setAge(30);
        employee2.setEmail("callyou@jeng.com");
        Date birthDay2 = formatter.parse("01/10/1960");
        employee2.setBirthDay(birthDay2);
        employee2.setCreateDateTime(new Date());
        Department department2 = (Department) session.get(Department.class, 2);
        employee2.setDepartment(department2);

        Set<Meeting> meetings2 = new HashSet<Meeting>(meetingNetAndSap);
//        meetings2.add(new Meeting("C++"));
//        meetings2.add(new Meeting("C#"));

//        employee2.setMeetings(meetings2);
        employee2.setMeetings(new HashSet<Meeting>(meetings2));
        session.saveOrUpdate(employee2);

        session.getTransaction().commit();

    }

    @Test
    @Ignore
    public void readRelationManyToMany() throws Exception {

        Employee employee = (Employee) session.get(Employee.class, 1);

        Set<Meeting> meetings = employee.getMeetings();

        for (Meeting meeting : meetings) {
            System.out.println(meeting.getSubject());
        }
    }

    @Test
    public void updateDepartmentValueInTableEmployee() {

        session.getTransaction().begin();
//        Department department = (Department) session.get(Department.class, 1);
//        Employee employee1 = (Employee) session.get(Employee.class, 1);
//        employee1.setDepartment(department);
//        session.update(employee1);
//

//
//        Department department2 = (Department) session.get(Department.class, 2);
//        System.out.println(department2);
//        Employee employee2 = (Employee) session.get(Employee.class, 2);
//        employee2.setDepartment(department2);
//        session.update(employee2);


        Employee employee1 = new Employee("Net", "Augmentis", new Department("IT"));
        Employee employee2 = new Employee("Chery", "Augmentis", new Department("HR"));
        Employee employee3 = new Employee("Wan", "Augmentis", new Department("Sale"));
        Employee employee4 = new Employee("June", "Augmentis", new Department("Sale"));

        session.saveOrUpdate(employee1);
        session.saveOrUpdate(employee2);
        session.saveOrUpdate(employee3);
        session.saveOrUpdate(employee4);

        session.getTransaction().commit();

    }


    @Test
    public void updateEmployeeIncludeDepartmentValue() {

        //select * from Employee em, Department d where em.department_id = d.id

        Employee employee = (Employee) session.get(Employee.class, 1);

        String employeeName = employee.getFirstName() +"  "+ employee.getLastName();
        String department = employee.getDepartment().getName();

        System.out.println("Employee name : "+ employeeName);
        System.out.println("Department :"+ department);

    }

    @Test
    public void viewEmployee() throws Exception {

        Query query = session.getNamedQuery("VIEW_EMPLOYEE");
        List<EmployeeView> employees = query.list();

        printView(employees);

    }

    @Test
    public void loadAllEmployee() throws Exception {
        List<Employee> employeeList = session.createQuery("FROM Employee").list();

        print(employeeList);
    }

    @Test
    public void findEmployeeByHSQL() throws Exception {
        List<Employee> employees = session.createQuery("FROM Employee e where e.firstName = 'Tony' ").list();
        print(employees);
        assertEquals(1, employees.size());
    }

    @Test
    public void findEmployeeByNativeSQL() throws Exception {

        SQLQuery sqlQuery = session.createSQLQuery("SELECT * FROM EMPLOYEE");
        sqlQuery.addEntity(Employee.class);

        List<Employee> employees = sqlQuery.list();
        assertEquals(2, employees.size());
    }

    @Test
    public void findEmployeeByNativeSQLWithParameter() throws Exception {

        SQLQuery sqlQuery = session.createSQLQuery("SELECT * FROM EMPLOYEE where FIRST_NAME = :FIRST_NAME");
        sqlQuery.addEntity(Employee.class);
        sqlQuery.setParameter("FIRST_NAME", "Tony");

        List<Employee> employees = sqlQuery.list();
        assertEquals(1, employees.size());
    }

    @Test
    public void findEmployeeByNamedQuery() {
        Query query = session.getNamedQuery("FIND_ALL_EMPLOYEE");
        List<Employee> employees = query.list();
        print(employees);
    }

    @Test
    public void findEmployeeByNamedQueryByTony() {
        Query query = session.getNamedQuery("FIND_EMPLOYEE_BY_TONY");
        query.setParameter("FIRST_NAME", "Wan");
        List<Employee> employees = query.list();
        print(employees);
    }
    @Test
    public void findCriteria() {
        Criteria criteria = session.createCriteria(Employee.class);
        List<Employee> employees = criteria.list();
        print(employees);
        assertEquals(2, employees.size());
    }

    @Test
    public void findCriteriaByFirstName() {
        Criteria criteria = session.createCriteria(Employee.class);
        criteria.add(Restrictions.eq("firstName", "Tony"));
        List<Employee> employees = criteria.list();
        assertEquals(1, employees.size());

        Employee employee = employees.get(0);
        assertEquals("Tony", employee.getFirstName());
    }

    @Test
    public void findCriteriaBySalary() {
        Criteria criteria = session.createCriteria(Employee.class);
        criteria.add(Restrictions.gt("salary", 10000L));
        List<Employee> employees = criteria.list();
        print(employees);

        assertEquals(1, employees.size());
    }
    @Test
    public  void findHQLByFirstNameParameter() {

        List<Employee> results = findByFirstName("Tony", "Jeng");
        assertEquals(1, results.size());

    }

    private List<Employee> findByFirstName(String firstName, String lastName) {

        Query query = session.createQuery("From Employee e where e.firstName = :FIRST_NAME and e.lastName = :LAST_NAME ");
        query.setParameter("FIRST_NAME", firstName);
        query.setParameter("LAST_NAME", lastName);

        List<Employee> employees = query.list();

        return employees;

    }

    @Test
    public void findById() {
        Employee employee = (Employee) session.get(Employee.class, 1);
        assertEquals("Tony", employee.getFirstName());
    }


    @Test
    public void updateEmployee() {
        session.getTransaction().begin();
        Employee employee = (Employee) session.get(Employee.class, 1);
        employee.setFirstName("David");

        session.update(employee);
        session.getTransaction().commit();
    }

    @Test
    @Ignore
    public void deleteEmployee() {
        session.getTransaction().begin();
        Employee employee = (Employee) session.get(Employee.class, 2);

        session.delete(employee);
        session.getTransaction().commit();
    }


    private void print(List<Employee> employees) {
        for (Employee employee : employees) {
            System.out.println("First Name : "+ employee.getFirstName() + ", Last Name : "+ employee.getLastName());
        }
    }

    private void printView(List<EmployeeView> employees) {
        for (EmployeeView employee : employees) {
            System.out.println("First Name : "+ employee.getFirstName() + ", Department Name : "+ employee.getDepartment());
        }
    }



}
