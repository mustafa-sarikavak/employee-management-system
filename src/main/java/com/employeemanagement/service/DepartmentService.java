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