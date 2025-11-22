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

  // --- Method to Update Role ---
  public void updateRole(long roleId, String newName, String newLevel, Department newDepartment) {
    // Step 1: Find the role
    Role role = getRoleById(roleId);

    // Step 2: Check if it exists
    if (role == null) {
      System.out.println("Error: Role with ID " + roleId + " not found.");
      return;
    }

    // Step 3: Update details
    role.setName(newName);
    role.setLevel(newLevel);
    role.setDepartment(newDepartment);
    System.out.println("Role updated successfully: " + role.getName());
  }

  // --- Delete Role ---
  public void deleteRole(long roleId) {
    Role role = getRoleById(roleId);

    if (role == null) {
      System.out.println("Error: Role with ID " + roleId + " not found.");
      return;
    }

    roleList.remove(role);
    System.out.println("Role deleted successfully.");
  }

  // --- List All Roles Formatted ---
  public void listAllRoles() {
    if (roleList.isEmpty()) {
      System.out.println("No roles available.");
      return;
    }

    System.out.println("\n=== Role List ===");
    // Header: ID (5), Name (20), Level (10), Dept (20)
    System.out.printf("%-5s %-20s %-15s %-20s%n", "ID", "Name", "Level", "Department");
    System.out.println("----------------------------------------------------------------");

    for (Role role : roleList) {
      String deptName = (role.getDepartment() != null) ? role.getDepartment().getName() : "None";
      System.out.printf("%-5d %-20s %-15s %-20s%n",
          role.getRoleId(),
          role.getName(),
          role.getLevel(),
          deptName);
    }
    System.out.println("----------------------------------------------------------------");
  }

  // Getter for the list
  public List<Role> getAllRoles() {
    return roleList;
  }
}