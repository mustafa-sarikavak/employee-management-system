package com.employeemanagement.service;

import com.employeemanagement.model.Role;
import com.employeemanagement.model.Department;
import java.util.ArrayList;
import java.util.List;

public class RoleService {
  // In-memory list to store roles
  private List<Role> roleList = new ArrayList<>();

  // Helper Method: Find Role by ID
  public Role getRoleById(long roleId) {
    for (Role role : roleList) {
      if (role.getRoleId() == roleId) {
        return role;
      }
    }
    return null; // Return null if not found
  }

  // Method to create and add a new role
  public void createRole(long roleId, String name, String level, Department department) {
    // Check if roleId is unique
    if (getRoleById(roleId) != null) {
      System.out.println("Error: Role with ID " + roleId + " already exists. Operation failed.");
      return;
    }

    // If unique, create and add
    Role newRole = new Role(roleId, name, level, department);
    roleList.add(newRole);
    System.out.println("Role created successfully: " + newRole.getName());
  }

  // Getter for the list
  public List<Role> getAllRoles() {
    return roleList;
  }
}