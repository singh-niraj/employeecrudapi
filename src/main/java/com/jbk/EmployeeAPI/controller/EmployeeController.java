package com.jbk.EmployeeAPI.controller;

import com.jbk.EmployeeAPI.EmployeeCache;
import com.jbk.EmployeeAPI.repository.EmployeeRepo;
import com.jbk.EmployeeAPI.entity.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {
    @Autowired
   private EmployeeRepo repo;
    @Autowired
    private EmployeeCache employeeCache;




    //API for add Employee
    @PostMapping(value = "/addemp")
    public Employee addEmployee(@RequestBody Employee employee)
    {
        Employee emp = repo.save(employee);
        return emp;
    }

  //  API for fetch Employee by their id
    @GetMapping(value = "/getemp/{id}")
  //  public Optional<Employee> getEmployee(@PathVariable long id)
    public Employee getEmployee(@PathVariable long id)
    {
       //Optional<Employee> emp = repo.findById(id);
       //return emp;
            employeeCache.loadCache();
         return employeeCache.hashMap.get(id);
    }

    //API for fetch all Employee
    @GetMapping(value = "/getallemp")
    public List<Employee> getallEmployee()
    {
      List<Employee> list =  repo.findAll();
      return list;
    }

    //API for upadate Employee Details
    @PutMapping(value = "/updateemp")
    public Employee updateEmp(@RequestBody Employee employee)
    {
        long tempId = employee.getId();
        Employee oldEmployee = repo.getById(tempId);
        if(oldEmployee.getId() !=0)
        {
            oldEmployee.setEName(employee.getEName());
            oldEmployee.setEAddress(employee.getEAddress());
            return repo.save(oldEmployee);
        }else
        {
            return null;
        }
    }

    //API for delete Employee by their id
    @DeleteMapping("/deleteemp/{id}")
        public String deleteEmp(@PathVariable long id)
    {
            repo.deleteById(id);
        return "deleted Successfully";
    }
}
