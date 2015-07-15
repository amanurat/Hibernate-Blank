package com.hibernate.annotation.dao;

import com.hibernate.annotation.entity.Department;

import java.util.List;


public class DepartmentDao extends GenericHibernateDAO<Department, Integer> {


    public DepartmentDao() {


    }

	public List<Department> findByExample(Department exampleInstance) {
		// TODO Auto-generated method stub
		return null;
	}
}
