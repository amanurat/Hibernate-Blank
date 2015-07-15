package com.training.spring.repositories;

import com.hibernate.annotation.entity.Employee;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;


public class EmployeeDaoImplTest extends AbstractDaoSpringContextTest {


    @Autowired
    private EmployeeDao employeeDao;
    private Serializable id;


    @Before
    @Rollback(value = false)
    public void setUp() throws Exception {
        id = employeeDao.insert(new Employee("Assanai", "Manurat"));
    }


    @After
    public void tearDown() throws Exception {
        employeeDao.deleteById(id);

    }

    @Test
    public void testFindAll() throws Exception {
        List<Employee> all = employeeDao.findAll();
        assertTrue(all.size() == 1);
    }

    @Test
    public void testGetById() throws Exception {
        Employee employee = employeeDao.findById(id);
        assertNotNull(employee);
        assertEquals("Assanai", employee.getFirstName());

    }

    @Test
    public void testUpdate() throws Exception {

        Employee employee = employeeDao.findById(id);
        employee.setFirstName("Aut");
        employeeDao.update(employee);

        Employee result = employeeDao.findById(id);

        assertEquals("Aut", result.getFirstName());
    }


    @Test
    public void testSearch() throws Exception {

        Map<String, Object> map = new HashMap<>();
        map.put("firstName", "Assanai");

        List<Employee> result = employeeDao.search(map);

        assertTrue(result.size() == 1);

        assertThat(result.size(), equalTo(1));


    }
}