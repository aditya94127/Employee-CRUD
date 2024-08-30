package com.example.employee.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.employee.entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
@Repository  //it tells the class is responsible for communicating with the database
public class EmployeeDAOImpl implements EmployeeDAO {
	private EntityManager entityManager;
	@Autowired
	public EmployeeDAOImpl(EntityManager theEntityManager) {
		entityManager=theEntityManager;
	}
	
	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		
		return entityManager.createQuery("From Employee",Employee.class).getResultList();
		
	}

	@Override
	public Employee findById(int theId) {
		Employee theEmployee= entityManager.find(Employee.class,theId);
		return theEmployee;
	}

	@Override
	public Employee save(Employee theEmployee) {
		return entityManager.merge(theEmployee);
	}

	@Override
	public void deleteById(int theId) {
		Employee theEmployee= entityManager.find(Employee.class,theId);
		entityManager.remove(theEmployee);
	}

	@Override
	public Employee updateEmployeeById(int theId, Employee theEmployee) {
		// TODO Auto-generated method stub
		Employee dbEmployee=entityManager.find(Employee.class,theId);
		return entityManager.merge(dbEmployee);
	}

}
