package com.employee.curd.service.cache;

import com.employee.curd.service.entity.Employee;
import com.employee.curd.service.repository.EmployeeRepo;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Slf4j
@Component
public class EmployeeCache {

    private final EmployeeRepo repo;
    private final Map<Long, Employee> map = new ConcurrentHashMap<>();

    @Autowired
    public EmployeeCache(EmployeeRepo repo) {
        this.repo = repo;
    }

    @PostConstruct
    public void loadCache() {
        List<Employee> allEmployees = repo.findAll();
        map.clear();
        allEmployees.forEach(emp -> map.put(emp.getId(), emp));
        log.info("Employee cache loaded with {} entries. ", map.size());
    }


    public Employee getEmployeeById(Long id) {
        return map.get(id);
    }

    public List<Employee> getEmployeesByAddress(String address) {
        return map.values().stream()
                .filter(emp -> address.equalsIgnoreCase(emp.getEAddress()))
                .toList();
    }

    public Map<Long, Employee> getCache() {
        return Collections.unmodifiableMap(map);
    }

}
