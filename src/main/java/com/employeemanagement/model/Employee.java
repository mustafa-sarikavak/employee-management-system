package com.employeemanagement.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Employee implements Serializable {
  private static final long serialVersionUID = 1L;

  private long employeeId;
  private String name;
  private int age;
  private LocalDate joiningDate;

  // Constructor
  public Employee(long employeeId, String name, int age, LocalDate joiningDate) {
    this.employeeId = employeeId;
    this.name = name;
    this.age = age;
    this.joiningDate = joiningDate;
  }

  // Getters and Setters
  public long getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(long employeeId) {
    this.employeeId = employeeId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public LocalDate getJoiningDate() {
    return joiningDate;
  }

  public void setJoiningDate(LocalDate joiningDate) {
    this.joiningDate = joiningDate;
  }

  @Override
  public String toString() {
    return "Employee{id=" + employeeId + ", name='" + name + "', age=" + age + ", joined=" + joiningDate + "}";
  }
}