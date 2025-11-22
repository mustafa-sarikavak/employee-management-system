package com.employeemanagement.service;

import com.employeemanagement.model.Department;
import com.employeemanagement.model.Employee;
import com.employeemanagement.model.Role;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
  // In-memory list to store employees
  private List<Employee> employeeList = new ArrayList<>();

  // Dependencies to fetch Department and Role objects
  private DepartmentService departmentService;
  private RoleService roleService;

  // Constructor Injection: We need other services to link data
  public EmployeeService(DepartmentService departmentService, RoleService roleService) {
    this.departmentService = departmentService;
    this.roleService = roleService;
  }

  // Method to check if an Employee ID already exists
  private boolean isEmployeeIdUnique(String employeeId) {
    for (Employee emp : employeeList) {
      if (emp.getEmployeeId().equalsIgnoreCase(employeeId)) {
        return false; // Duplicate found
      }
    }
    return true; // Unique
  }

  // --- NEW METHOD: Create Employee using IDs ---
  public void createEmployee(String employeeId, String name, int age, double salary,
      LocalDate joiningDate, long departmentId, long roleId) {

    // 1. Check ID Uniqueness
    if (!isEmployeeIdUnique(employeeId)) {
      System.out.println("Error: Employee ID " + employeeId + " already exists.");
      return;
    }

    // 2. Find Department by ID
    Department dept = departmentService.getDepartmentById(departmentId);
    if (dept == null) {
      System.out.println("Error: Department with ID " + departmentId + " not found. Cannot create employee.");
      return;
    }

    // 3. Find Role by ID
    Role role = roleService.getRoleById(roleId);
    if (role == null) {
      System.out.println("Error: Role with ID " + roleId + " not found. Cannot create employee.");
      return;
    }

    // 4. Create Employee Object
    Employee newEmployee = new Employee(employeeId, name, age, salary, joiningDate, role, dept);

    // 5. Add to Employee List
    employeeList.add(newEmployee);

    // 6. Add to Department's Employee List (Maintain Relationship)
    dept.getEmployees().add(newEmployee);

    System.out.println("Employee created successfully: " + newEmployee.getName());
  }

  // Getter for the list
  public List<Employee> getAllEmployees() {
    return employeeList;
  }

  // Helper: Find Employee by ID (Will be used in update/delete tasks)
  public Employee getEmployeeById(String employeeId) {
    for (Employee emp : employeeList) {
      if (emp.getEmployeeId().equalsIgnoreCase(employeeId)) {
        return emp;
      }
    }
    return null;
  }
}