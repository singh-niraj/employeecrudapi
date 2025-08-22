package com.employee.curd.service.entity;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "employee_info")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String eName;

    private String eAddress;

}