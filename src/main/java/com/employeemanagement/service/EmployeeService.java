package com.employeemanagement.service;

import com.employeemanagement.model.Employee;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
  // In-memory list to store employees
  private List<Employee> employeeList = new ArrayList<>();

  // Method to check if an Employee ID already exists
  private boolean isEmployeeIdUnique(String employeeId) {
    for (Employee emp : employeeList) {
      if (emp.getEmployeeId().equalsIgnoreCase(employeeId)) {
        return false; // Duplicate found
      }
    }
    return true; // Unique
  }

  // Method to add a new employee
  public void addEmployee(Employee employee) {
    // Check for unique ID
    if (!isEmployeeIdUnique(employee.getEmployeeId())) {
      System.out.println("Error: Employee with ID " + employee.getEmployeeId() + " already exists. Operation failed.");
      return;
    }

    // If unique, add to list
    employeeList.add(employee);

    // Also add to the Department's list (Maintaining the relationship)
    if (employee.getDepartment() != null) {
      employee.getDepartment().getEmployees().add(employee);
    }

    System.out.println("Employee added successfully: " + employee.getName());
  }

  // Getter for the list
  public List<Employee> getAllEmployees() {
    return employeeList;
  }
}