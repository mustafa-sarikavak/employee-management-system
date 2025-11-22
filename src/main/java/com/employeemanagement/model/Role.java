package com.employeemanagement.model;

import java.io.Serializable;

public class Role implements Serializable {
  // Serialization version ID
  private static final long serialVersionUID = 1L;

  private long roleId;
  private String name;
  private double salaryFactor;

  // Constructor
  public Role(long roleId, String name, double salaryFactor) {
    this.roleId = roleId;
    this.name = name;
    this.salaryFactor = salaryFactor;
  }

  // Getters and Setters
  public long getRoleId() {
    return roleId;
  }

  public void setRoleId(long roleId) {
    this.roleId = roleId;
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

  // toString method for easy printing
  @Override
  public String toString() {
    return "Role{id=" + roleId + ", name='" + name + "', factor=" + salaryFactor + "}";
  }
}