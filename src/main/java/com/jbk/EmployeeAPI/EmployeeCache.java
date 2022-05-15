package com.jbk.EmployeeAPI;

import com.jbk.EmployeeAPI.entity.Employee;
import com.jbk.EmployeeAPI.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class EmployeeCache {

    @Autowired
   private EmployeeRepo repo;

    //HashMap

    public HashMap<Long, Employee> hashMap = new HashMap<Long, Employee>();
    public void loadCache()
    {
        List<Employee> list = repo.findAll();
        for(Employee employee : list)
        {
            hashMap.put(employee.getId(), employee);
        }
    }
}
