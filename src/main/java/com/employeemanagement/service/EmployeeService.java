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
  public void filterEmployeesByRoleId(long roleId) {
    List<Employee> filteredList = new ArrayList<>();

    for (Employee emp : employeeList) {
      // Check if role exists and matches the ID
      if (emp.getRole() != null && emp.getRole().getRoleId() == roleId) {
        filteredList.add(emp);
      }
    }

    if (filteredList.isEmpty()) {
      System.out.println("No employees found with Role ID: " + roleId);
    } else {
      System.out.println("\n=== Employees with Role ID " + roleId + " ===");
      printEmployeeTable(filteredList);
    }
  }

  // Helper method to print table (Refactored to avoid code duplication)
  private void printEmployeeTable(List<Employee> listToPrint) {
    System.out.printf("%-10s %-20s %-5s %-12s %-15s %-15s%n", "ID", "Name", "Age", "Salary", "Role", "Department");
    System.out.println("-----------------------------------------------------------------------------------------");

    for (Employee emp : listToPrint) {
      String roleName = (emp.getRole() != null) ? emp.getRole().getName() : "None";
      String deptName = (emp.getDepartment() != null) ? emp.getDepartment().getName() : "None";

      System.out.printf("%-10s %-20s %-5d %-12.2f %-15s %-15s%n",
          emp.getEmployeeId(),
          emp.getName(),
          emp.getAge(),
          emp.getSalary(),
          roleName,
          deptName);
    }
    System.out.println("-----------------------------------------------------------------------------------------");
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