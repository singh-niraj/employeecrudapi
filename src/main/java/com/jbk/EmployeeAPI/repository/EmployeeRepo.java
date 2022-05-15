package com.jbk.EmployeeAPI.repository;

import com.jbk.EmployeeAPI.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long>
{


}
