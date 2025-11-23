package com.employeemanagement;

import com.employeemanagement.model.Employee;
import com.employeemanagement.service.DepartmentService;
import com.employeemanagement.service.EmployeeService;
import com.employeemanagement.service.RoleService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    // 1. Initialize Services (Dependency Injection)
    DepartmentService departmentService = new DepartmentService();
    RoleService roleService = new RoleService();
    EmployeeService employeeService = new EmployeeService(departmentService, roleService);

    Scanner scanner = new Scanner(System.in);
    boolean running = true;

    // 2. Pre-populate some data for testing (Optional, but helpful)
    departmentService.createDepartment("IT", 1.5);
    departmentService.createDepartment("HR", 1.2);
    roleService.createRole(1, "Developer", "Mid", 1.5, departmentService.getDepartmentById(1));
    roleService.createRole(2, "Manager", "Senior", 2.0, departmentService.getDepartmentById(2));

    while (running) {
      System.out.println("\n=== EMPLOYEE MANAGEMENT SYSTEM ===");
      System.out.println("--- Department Operations ---");
      System.out.println("1. Add Department");
      System.out.println("2. List Departments");
      System.out.println("3. Update Department");
      System.out.println("4. Delete Department");
      System.out.println("--- Role Operations ---");
      System.out.println("5. Add Role");
      System.out.println("6. List Roles");
      System.out.println("7. Update Role");
      System.out.println("8. Delete Role");
      System.out.println("--- Employee Operations ---");
      System.out.println("9. Add Employee");
      System.out.println("10. List Employees");
      System.out.println("11. Update Employee");
      System.out.println("12. Delete Employee");
      System.out.println("13. Search Employee by Name");
      System.out.println("14. Filter Employees by Role ID");
      System.out.println("15. Filter Employees by Dept ID");
      System.out.println("16. Sort Employees by Name");
      System.out.println("0. Exit");
      System.out.print("Enter your choice: ");

      int choice = scanner.nextInt();
      scanner.nextLine(); // Consume newline

      switch (choice) {
        case 1: // Add Dept
          System.out.print("Enter Department Name: ");
          String deptName = scanner.nextLine();
          System.out.print("Enter Salary Factor (e.g., 1.5): ");
          double factor = scanner.nextDouble();
          departmentService.createDepartment(deptName, factor);
          break;

        case 2: // List Depts
          departmentService.listAllDepartments();
          break;

        case 3: // Update Dept
          System.out.print("Enter Department ID to Update: ");
          long updateDeptId = scanner.nextLong();
          scanner.nextLine();
          System.out.print("Enter New Name: ");
          String newDeptName = scanner.nextLine();
          System.out.print("Enter New Factor: ");
          double newFactor = scanner.nextDouble();
          departmentService.updateDepartment(updateDeptId, newDeptName, newFactor);
          break;

        case 4: // Delete Dept
          System.out.print("Enter Department ID to Delete: ");
          long delDeptId = scanner.nextLong();
          departmentService.deleteDepartment(delDeptId);
          break;

        case 5: // Add Role
          System.out.print("Enter Role ID: ");
          long roleId = scanner.nextLong();
          scanner.nextLine();
          System.out.print("Enter Role Name: ");
          String roleName = scanner.nextLine();
          System.out.print("Enter Role Level: ");
          String roleLevel = scanner.nextLine();
          System.out.print("Enter Salary Factor (e.g. 1.5): ");
          double roleFactor = scanner.nextDouble();
          System.out.print("Enter Department ID for this Role: ");
          long roleDeptId = scanner.nextLong();
          // Fetch dept object to pass
          roleService.createRole(roleId, roleName, roleLevel, roleFactor, departmentService.getDepartmentById(roleDeptId));
          break;

        case 6: // List Roles
          roleService.listAllRoles();
          break;

        case 7: // Update Role
          System.out.print("Enter Role ID to Update: ");
          long updateRoleId = scanner.nextLong();
          scanner.nextLine();
          System.out.print("Enter New Name: ");
          String newRoleName = scanner.nextLine();
          System.out.print("Enter New Level: ");
          String newRoleLevel = scanner.nextLine();
          System.out.print("Enter New Salary Factor: ");
          double newRoleFactor = scanner.nextDouble();
          System.out.print("Enter New Department ID: ");
          long newRoleDeptId = scanner.nextLong();
          roleService.updateRole(updateRoleId, newRoleName, newRoleLevel, newRoleFactor, departmentService.getDepartmentById(newRoleDeptId));
          break;

        case 8: // Delete Role
          System.out.print("Enter Role ID to Delete: ");
          long delRoleId = scanner.nextLong();
          roleService.deleteRole(delRoleId);
          break;

        case 9: // Add Employee
          System.out.print("Enter Employee ID (String): ");
          String empId = scanner.nextLine();
          System.out.print("Enter Name: ");
          String empName = scanner.nextLine();
          System.out.print("Enter Age: ");
          int age = scanner.nextInt();
          System.out.print("Enter Salary: ");
          double salary = scanner.nextDouble();
          scanner.nextLine(); // Consume newline
          System.out.print("Enter Joining Date (YYYY-MM-DD): ");
          String dateStr = scanner.nextLine();
          LocalDate date;
          try {
            date = LocalDate.parse(dateStr);
          } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Using today's date.");
            date = LocalDate.now();
          }
          System.out.print("Enter Department ID: ");
          long empDeptId = scanner.nextLong();
          System.out.print("Enter Role ID: ");
          long empRoleId = scanner.nextLong();

          employeeService.createEmployee(empId, empName, age, salary, date, empDeptId, empRoleId);
          break;

        case 10: // List Employees
          employeeService.listAllEmployees();
          break;

        case 11: // Update Employee
          System.out.print("Enter Employee ID to Update: ");
          String upEmpId = scanner.nextLine();
          System.out.print("Enter New Name: ");
          String upName = scanner.nextLine();
          System.out.print("Enter New Age: ");
          int upAge = scanner.nextInt();
          System.out.print("Enter New Salary: ");
          double upSalary = scanner.nextDouble();
          System.out.print("Enter New Department ID: ");
          long upDeptId = scanner.nextLong();
          System.out.print("Enter New Role ID: ");
          long upRoleId = scanner.nextLong();

          employeeService.updateEmployee(upEmpId, upName, upAge, upSalary, upDeptId, upRoleId);
          break;

        case 12: // Delete Employee
          System.out.print("Enter Employee ID to Delete: ");
          String delEmpId = scanner.nextLine();
          employeeService.deleteEmployee(delEmpId);
          break;

        case 13: // Search Employee
          System.out.print("Enter Name to Search: ");
          String searchName = scanner.nextLine();
          employeeService.searchEmployeesByName(searchName);
          break;

        case 14: // Filter by Role
          System.out.print("Enter Role ID to Filter: ");
          long filterRoleId = scanner.nextLong();
          List<Employee> roleResults = employeeService.filterEmployeesByRoleId(filterRoleId);
          employeeService.printEmployeeTable(roleResults);
          break;

        case 15: // Filter by Dept
          System.out.print("Enter Dept ID to Filter: ");
          long filterDeptId = scanner.nextLong();
          List<Employee> deptResults = employeeService.filterEmployeesByDepartmentId(filterDeptId);
          employeeService.printEmployeeTable(deptResults);
          break;

        case 16: // Sort Employees
          System.out.print("Enter sort order (asc/desc): ");
          String sortOrder = scanner.nextLine();
          employeeService.sortEmployeesByName(sortOrder);
          break;

        case 0:
          running = false;
          System.out.println("Exiting system. Goodbye!");
          break;

        default:
          System.out.println("Invalid choice. Please try again.");
      }
    }
    scanner.close();
  }
}