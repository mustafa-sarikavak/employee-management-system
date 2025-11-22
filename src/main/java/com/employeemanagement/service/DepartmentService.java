package com.employeemanagement.service;

import com.employeemanagement.model.Department;
import java.util.ArrayList;
import java.util.List;

public class DepartmentService {
  // In-memory list to store departments
  private List<Department> departmentList = new ArrayList<>();

  // Method to create and add a new department
  public void createDepartment(String name, double salaryFactor) {
    long newId = departmentList.size() + 1;
    Department newDept = new Department(newId, name, salaryFactor);
    departmentList.add(newDept);
    System.out.println("Department created successfully: " + newDept.getName());
  }

  // Helper Method: Find Department by ID
  public Department getDepartmentById(long departmentId) {
    for (Department dept : departmentList) {
      if (dept.getDepartmentId() == departmentId) {
        return dept;
      }
    }
    return null; // Return null if not found
  }

  // Update Department
  public void updateDepartment(long departmentId, String newName, double newSalaryFactor) {
    // Step 1: Find the department
    Department dept = getDepartmentById(departmentId);

    // Step 2: Check if it exists
    if (dept == null) {
      System.out.println("Error: Department with ID " + departmentId + " not found.");
      return;
    }

    // Step 3: Update details
    dept.setName(newName);
    dept.setSalaryFactor(newSalaryFactor);
    System.out.println("Department updated successfully: " + dept.getName());
  }

  // --- Delete Department ---
  public void deleteDepartment(long departmentId) {
    Department dept = getDepartmentById(departmentId);

    if (dept == null) {
      System.out.println("Error: Department with ID " + departmentId + " not found.");
      return;
    }

    departmentList.remove(dept);
    System.out.println("Department deleted successfully.");
  }

  // --- List All Departments Formatted ---
  public void listAllDepartments() {
    if (departmentList.isEmpty()) {
      System.out.println("No departments available.");
      return;
    }

    System.out.println("\n=== Department List ===");
    // Header format: ID (5 chars), Name (20 chars), Factor (10 chars), Emp Count (10 chars)
    System.out.printf("%-5s %-20s %-10s %-15s%n", "ID", "Name", "Factor", "Employees");
    System.out.println("------------------------------------------------------");

    for (Department dept : departmentList) {
      System.out.printf("%-5d %-20s %-10.2f %-15d%n",
          dept.getDepartmentId(),
          dept.getName(),
          dept.getSalaryFactor(),
          dept.getEmployees().size());
    }
    System.out.println("------------------------------------------------------");
  }

  // Method to get a department by name (Helper for future tasks)
  public Department getDepartmentByName(String name) {
    for (Department dept : departmentList) {
      if (dept.getName().equalsIgnoreCase(name)) {
        return dept;
      }
    }
    return null;
  }

  // Getter for the list
  public List<Department> getAllDepartments() {
    return departmentList;
  }
}