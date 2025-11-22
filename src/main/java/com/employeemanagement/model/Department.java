package com.employeemanagement.model;

import java.io.Serializable;

public class Department implements Serializable {
  private static final long serialVersionUID = 1L;

  private long departmentId;
  private String name;
  private double salaryFactor;

  // Constructor
  public Department(long departmentId, String name, double salaryFactor) {
    this.departmentId = departmentId;
    this.name = name;
    this.salaryFactor = salaryFactor;
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

  @Override
  public String toString() {
    return "Department{id=" + departmentId + ", name='" + name + "', factor=" + salaryFactor + "}";
  }
}