package com.employeemanagement.service;

import com.employeemanagement.model.Department;
import java.util.ArrayList;
import java.util.List;

public class DepartmentService {
  // In-memory list to store departments
  private List<Department> departmentList = new ArrayList<>();

  // Method to create and add a new department
  public void createDepartment(String name, double salaryFactor) {
    // Generate a simple ID based on list size (1, 2, 3...)
    long newId = departmentList.size() + 1;
    Department newDept = new Department(newId, name, salaryFactor);
    departmentList.add(newDept);
    System.out.println("Department created successfully: " + newDept.getName());
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