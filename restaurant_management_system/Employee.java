package com.mycompany.restaurant_management_system;
import java.util.HashMap;
import java.util.Map;

public class Employee {
    private String id;
    private String name;
    private String role;
    private double salary;
    private String contactNumber;
    private boolean isPresent;
    private Map<String, Double> performanceMetrics; 
    private int totalHoursWorked; 


    public Employee(String id, String name, String role, double salary, String contactNumber) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.salary = salary;
        this.contactNumber = contactNumber;
        this.isPresent = false; 
        this.performanceMetrics = new HashMap<>();
        this.totalHoursWorked = 0; 
    }

    
    public Employee(String string, String string2, String string3) {
		// TODO Auto-generated constructor stub
	}


	public void markAttendance(boolean present) {
        this.isPresent = present;
        System.out.println(name + " has " + (present ? "marked present" : "marked absent") + " for today.");
    }

   
    public void assignRole(String newRole) {
        if (!newRole.equalsIgnoreCase(this.role)) {
            System.out.println("Role updated from " + this.role + " to " + newRole);
            this.role = newRole;
        } else {
            System.out.println("The employee already holds the role of " + this.role);
        }
    }

  
    public void updateEmployeeDetails(String newName, String newContactNumber, double newSalary) {
        boolean updated = false;

        if (!newName.equals(this.name)) {
            System.out.println("Name updated from " + this.name + " to " + newName);
            this.name = newName;
            updated = true;
        }
        if (!newContactNumber.equals(this.contactNumber)) {
            System.out.println("Contact updated from " + this.contactNumber + " to " + newContactNumber);
            this.contactNumber = newContactNumber;
            updated = true;
        }
        if (newSalary != this.salary) {
            System.out.println("Salary updated from " + this.salary + " to " + newSalary);
            this.salary = newSalary;
            updated = true;
        }

        if (!updated) {
            System.out.println("No changes made to employee details.");
        }
    }

    
    public double calculateSalary(int workingDays, int daysPresent, double overtimeHours, double vacationDays) {
        double attendanceBonus = (daysPresent / (double) workingDays) * 0.1 * salary; 
        double performanceBonus = performanceMetrics.values().stream().mapToDouble(Double::doubleValue).sum() * 0.05; 
        double overtimeBonus = calculateOvertimeBonus(overtimeHours); 
        double vacationDeduction = calculateVacationDeduction(vacationDays); 
        double salaryAfterTax = calculateTax(); 

        double finalSalary = salary + attendanceBonus + performanceBonus + overtimeBonus - vacationDeduction - salaryAfterTax;

        System.out.println("Base Salary: " + salary);
        System.out.println("Attendance Bonus: " + attendanceBonus);
        System.out.println("Performance Bonus: " + performanceBonus);
        System.out.println("Overtime Bonus: " + overtimeBonus);
        System.out.println("Vacation Deduction: " + vacationDeduction);
        System.out.println("Tax Deduction: " + salaryAfterTax);
        System.out.println("Final Salary: " + finalSalary);

        return finalSalary;
    }
    public double calculateOvertimeBonus(double overtimeHours) {
        double overtimeRate = 15.0; 
        return overtimeHours * overtimeRate;
    }

   
    public double calculateVacationDeduction(double vacationDays) {
        double dailyRate = salary / 30; 
        return dailyRate * vacationDays; 
    }

   
    public double calculateTax() {
        double taxRate;
        if (salary < 3000) {
            taxRate = 0.05; 
        } else if (salary < 7000) {
            taxRate = 0.10; 
        } else {
            taxRate = 0.15; 
        }
        return salary * taxRate;
    }

  
    public double calculateEfficiency() {
        double ordersHandled = performanceMetrics.getOrDefault("ordersHandled", 0.0);
        return (ordersHandled / totalHoursWorked) * 100; // Efficiency as percentage
    }

    
    public void getEmployeePerformance() {
        System.out.println("Performance Metrics for " + name + ":");
        for (Map.Entry<String, Double> entry : performanceMetrics.entrySet()) {
            System.out.println("- " + entry.getKey() + ": " + entry.getValue());
        }
    }

   
    public void addPerformanceMetric(String metric, double value) {
        performanceMetrics.put(metric, performanceMetrics.getOrDefault(metric, 0.0) + value);
        System.out.println("Updated performance metric '" + metric + "' to " + performanceMetrics.get(metric));
    }

   
    public void viewSchedule() {
        System.out.println("Viewing schedule for " + name + ":");
       
        if (role.equalsIgnoreCase("Waiter")) {
            System.out.println("- Monday to Friday: 9:00 AM to 5:00 PM");
        } else if (role.equalsIgnoreCase("Chef")) {
            System.out.println("- Tuesday to Saturday: 1:00 PM to 9:00 PM");
        } else {
            System.out.println("- General schedule: Monday to Saturday: 9:00 AM to 5:00 PM");
        }
    }

  
    public void setTotalHoursWorked(int hoursWorked) {
        this.totalHoursWorked = hoursWorked;
    }

   
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public double getSalary() {
        return salary;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public Map<String, Double> getPerformanceMetrics() {
        return performanceMetrics;
    }

    public int getTotalHoursWorked() {
        return totalHoursWorked;
    }
}
