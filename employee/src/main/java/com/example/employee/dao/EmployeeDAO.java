package com.example.employee.dao;
import java.util.*;
import com.example.employee.entity.Employee;


public interface EmployeeDAO {
	List <Employee> findAll();
	Employee findById(int theId);
	Employee save(Employee theEmployee);
	void deleteById(int theId);
	Employee updateEmployeeById(int theId,Employee theEmployee);
}
