package com.employeemanagement.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Employee implements Serializable {
  private static final long serialVersionUID = 1L;

  private String employeeId;
  private String name;
  private int age;
  private double salary;
  private LocalDate joiningDate;

  // New Relationships
  private Role role;             // Association: Employee has a Role
  private Department department; // Association: Employee belongs to a Department

  public Employee() {
  }

  // Updated Constructor with Role and Department
  public Employee(String employeeId, String name, int age, double salary, LocalDate joiningDate, Role role, Department department) {
    this.employeeId = employeeId;
    this.name = name;
    this.age = age;
    this.salary = salary;
    this.joiningDate = joiningDate;
    this.role = role;
    this.department = department;
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

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  @Override
  public String toString() {
    return "Employee{" +
        "id='" + employeeId + '\'' +
        ", name='" + name + '\'' +
        ", role=" + (role != null ? role.getName() : "None") +
        ", department=" + (department != null ? department.getName() : "None") +
        '}';
  }
}