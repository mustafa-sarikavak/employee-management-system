package com.employeemanagement.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Employee implements Serializable {
  private static final long serialVersionUID = 1L;

  private String employeeId;   // Changed from long to String (e.g., "E001")
  private String name;
  private int age;
  private double salary;       // New attribute
  private LocalDate joiningDate;

  // Empty Constructor
  public Employee() {
  }

  // Parameterized Constructor
  public Employee(String employeeId, String name, int age, double salary, LocalDate joiningDate) {
    this.employeeId = employeeId;
    this.name = name;
    this.age = age;
    this.salary = salary;
    this.joiningDate = joiningDate;
  }

  // Getters and Setters
  public String getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(String employeeId) {
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

  public double getSalary() {
    return salary;
  }

  public void setSalary(double salary) {
    this.salary = salary;
  }

  public LocalDate getJoiningDate() {
    return joiningDate;
  }

  public void setJoiningDate(LocalDate joiningDate) {
    this.joiningDate = joiningDate;
  }

  @Override
  public String toString() {
    return "Employee{" +
        "id='" + employeeId + '\'' +
        ", name='" + name + '\'' +
        ", age=" + age +
        ", salary=" + salary +
        ", joined=" + joiningDate +
        '}';
  }
}