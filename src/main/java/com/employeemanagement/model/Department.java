package com.employeemanagement.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Department implements Serializable {
  private static final long serialVersionUID = 1L;

  private long departmentId;
  private String name;
  private double salaryFactor;
  private List<Employee> employees; // List to track employees in this department

  // Empty Constructor
  public Department() {
    this.employees = new ArrayList<>(); // Initialize list to avoid NullPointerException
  }

  // Parameterized Constructor
  public Department(long departmentId, String name, double salaryFactor) {
    this.departmentId = departmentId;
    this.name = name;
    this.salaryFactor = salaryFactor;
    this.employees = new ArrayList<>(); // Initialize list
  }

  // Getters and Setters
  public long getDepartmentId() {
    return departmentId;
  }

  public void setDepartmentId(long departmentId) {
    this.departmentId = departmentId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getSalaryFactor() {
    return salaryFactor;
  }

  public void setSalaryFactor(double salaryFactor) {
    this.salaryFactor = salaryFactor;
  }

  public List<Employee> getEmployees() {
    return employees;
  }

  public void setEmployees(List<Employee> employees) {
    this.employees = employees;
  }

  @Override
  public String toString() {
    return "Department{" +
        "id=" + departmentId +
        ", name='" + name + '\'' +
        ", factor=" + salaryFactor +
        ", employeeCount=" + (employees != null ? employees.size() : 0) +
        '}';
  }
}