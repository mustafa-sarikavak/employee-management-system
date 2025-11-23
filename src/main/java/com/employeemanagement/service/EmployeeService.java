package com.employeemanagement.service;

import com.employeemanagement.model.Department;
import com.employeemanagement.model.Employee;
import com.employeemanagement.model.Role;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public class EmployeeService {
  // In-memory list to store employees
  private List<Employee> employeeList = new ArrayList<>();

  // Base Hourly Rate
  private static final double BASE_HOURLY_RATE = 100.0;

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

  // --- Create Employee using IDs ---
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

  // --- Update Employee ---
  public void updateEmployee(String employeeId, String newName, int newAge, double newSalary, long newDeptId, long newRoleId) {
    // 1. Find the employee
    Employee emp = getEmployeeById(employeeId);
    if (emp == null) {
      System.out.println("Error: Employee with ID " + employeeId + " not found.");
      return;
    }

    // 2. Validate new Department and Role
    Department newDept = departmentService.getDepartmentById(newDeptId);
    Role newRole = roleService.getRoleById(newRoleId);

    if (newDept == null) {
      System.out.println("Error: New Department ID " + newDeptId + " not found. Update failed.");
      return;
    }
    if (newRole == null) {
      System.out.println("Error: New Role ID " + newRoleId + " not found. Update failed.");
      return;
    }

    // 3. Handle Department Transfer (If department changed)
    Department oldDept = emp.getDepartment();
    if (oldDept.getDepartmentId() != newDeptId) {
      oldDept.getEmployees().remove(emp); // Remove from old department list
      newDept.getEmployees().add(emp);    // Add to new department list
      emp.setDepartment(newDept);         // Update employee's reference
      System.out.println("Department updated for employee: " + emp.getName());
    }

    // 4. Update other details
    emp.setName(newName);
    emp.setAge(newAge);
    emp.setSalary(newSalary);
    emp.setRole(newRole);

    System.out.println("Employee details updated successfully.");
  }

  // --- Delete Employee ---
  public void deleteEmployee(String employeeId) {
    Employee emp = getEmployeeById(employeeId);

    if (emp == null) {
      System.out.println("Error: Employee with ID " + employeeId + " not found.");
      return;
    }

    // 1. Remove from Department's list first (Maintain Relationship)
    if (emp.getDepartment() != null) {
      emp.getDepartment().getEmployees().remove(emp);
    }

    // 2. Remove from main Employee list
    employeeList.remove(emp);
    System.out.println("Employee deleted successfully.");
  }

  // --- List All Employees Formatted ---
  public void listAllEmployees() {
    if (employeeList.isEmpty()) {
      System.out.println("No employees available.");
      return;
    }
    printEmployeeTable(employeeList);
  }

  // --- Search Employees by Name ---
  public void searchEmployeesByName(String keyword) {
    List<Employee> searchResults = new ArrayList<>();

    // Filter employees based on name (Case-Insensitive)
    for (Employee emp : employeeList) {
      if (emp.getName().toLowerCase().contains(keyword.toLowerCase())) {
        searchResults.add(emp);
      }
    }

    if (searchResults.isEmpty()) {
      System.out.println("No employees found matching: " + keyword);
    } else {
      System.out.println("\n=== Search Results for '" + keyword + "' ===");
      printEmployeeTable(searchResults);
    }
  }

  // --- Filter Employees by Role ID ---
  public List<Employee> filterEmployeesByRoleId(long roleId) {
    List<Employee> filteredList = new ArrayList<>();

    for (Employee emp : employeeList) {
      // Check if role exists and matches the ID
      if (emp.getRole() != null && emp.getRole().getRoleId() == roleId) {
        filteredList.add(emp);
      }
    }
    return filteredList;
  }

  // --- Filter Employees by Department ID ---
  public List<Employee> filterEmployeesByDepartmentId(long departmentId) {
    List<Employee> filteredList = new ArrayList<>();

    for (Employee emp : employeeList) {
      // Check if department exists and matches the ID
      if (emp.getDepartment() != null && emp.getDepartment().getDepartmentId() == departmentId) {
        filteredList.add(emp);
      }
    }
    return filteredList;
  }

  // --- Sort Employees by Name ---
  public void sortEmployeesByName(String order) {
    // Create a copy of the list to avoid modifying the original data order
    List<Employee> sortedList = new ArrayList<>(employeeList);

    if (order.equalsIgnoreCase("asc")) {
      // Sort A to Z (Case Insensitive)
      sortedList.sort(Comparator.comparing(Employee::getName, String.CASE_INSENSITIVE_ORDER));
    } else if (order.equalsIgnoreCase("desc")) {
      // Sort Z to A (Case Insensitive)
      sortedList.sort(Comparator.comparing(Employee::getName, String.CASE_INSENSITIVE_ORDER).reversed());
    } else {
      System.out.println("Invalid sort order. Please use 'asc' or 'desc'.");
      return;
    }

    System.out.println("\n=== Sorted Employees (" + order.toUpperCase() + ") ===");
    printEmployeeTable(sortedList);
  }

  // Helper method to print table (Updated to show Role Level)
  public void printEmployeeTable(List<Employee> listToPrint) {
    // Role column width increased from 15 to 25 to fit "Name (Level)"
    System.out.printf("%-10s %-20s %-5s %-12s %-25s %-15s%n", "ID", "Name", "Age", "Salary", "Role", "Department");
    System.out.println("----------------------------------------------------------------------------------------------------");

    for (Employee emp : listToPrint) {
      String roleDetails = "None";
      if (emp.getRole() != null) {
        // Format: "Manager (Senior)" or "Developer (Mid)"
        roleDetails = emp.getRole().getName() + " (" + emp.getRole().getLevel() + ")";
      }

      String deptName = (emp.getDepartment() != null) ? emp.getDepartment().getName() : "None";

      System.out.printf("%-10s %-20s %-5d %-12.2f %-25s %-15s%n",
          emp.getEmployeeId(),
          emp.getName(),
          emp.getAge(),
          emp.getSalary(),
          roleDetails, // Now shows Name + Level
          deptName);
    }
    System.out.println("----------------------------------------------------------------------------------------------------");
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

  // --- Calculate Salary ---
  public void calculateSalary(String employeeId, double hoursWorked) {
    // 1. Find Employee
    Employee emp = getEmployeeById(employeeId);

    if (emp == null) {
      System.out.println("Error: Employee not found with ID: " + employeeId);
      return;
    }

    // 2. Get Factors (Safe check for nulls)
    double roleFactor = (emp.getRole() != null) ? emp.getRole().getSalaryFactor() : 1.0;
    double deptFactor = (emp.getDepartment() != null) ? emp.getDepartment().getSalaryFactor() : 1.0;

    // 3. Compute Salary
    // Formula: Base * Hours * RoleFactor * DeptFactor
    double totalSalary = BASE_HOURLY_RATE * hoursWorked * roleFactor * deptFactor;

    // 4. Print Result
    System.out.println("\n=== Salary Calculation Detail ===");
    System.out.printf("%-10s %-10s %-12s %-12s %-15s%n", "Emp ID", "Hours", "Role Fac.", "Dept Fac.", "Total Salary");
    System.out.println("-----------------------------------------------------------------");
    System.out.printf("%-10s %-10.1f %-12.2f %-12.2f %-15.2f%n",
        emp.getEmployeeId(),
        hoursWorked,
        roleFactor,
        deptFactor,
        totalSalary);
    System.out.println("-----------------------------------------------------------------");
  }
}