package com.employeemanagement.model;

import java.io.Serializable;

public class Role implements Serializable {
  private static final long serialVersionUID = 1L;

  private long roleId;
  private String name;
  private String level;        // Junior, Mid, Senior etc.
  private Department department; // Association with Department class

  // Empty Constructor (Good for serialization/future frameworks)
  public Role() {
  }

  // Parameterized Constructor
  public Role(long roleId, String name, String level, Department department) {
    this.roleId = roleId;
    this.name = name;
    this.level = level;
    this.department = department;
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

  public String getLevel() {
    return level;
  }

  public void setLevel(String level) {
    this.level = level;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  @Override
  public String toString() {
    return "Role{" +
        "id=" + roleId +
        ", name='" + name + '\'' +
        ", level='" + level + '\'' +
        ", department=" + (department != null ? department.getName() : "None") +
        '}';
  }
}