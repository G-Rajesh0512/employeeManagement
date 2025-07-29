package com.Employee.Management.Interface;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Employee.Management.Entity.Employeeinfo;

public interface EmployeeRepo extends JpaRepository<Employeeinfo, Long>{
}
