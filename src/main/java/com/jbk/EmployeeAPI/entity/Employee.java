package com.jbk.EmployeeAPI.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "employee_info")
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String eName;
    private String eAddress;

}