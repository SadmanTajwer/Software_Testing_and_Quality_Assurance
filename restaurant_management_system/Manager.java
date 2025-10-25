package com.mycompany.restaurant_management_system;

import java.util.ArrayList;
import java.util.List;

public class Manager {
    private String managerId;
    private String name;
    private String email;
    private boolean isOnDuty;
    private List<String> employeesSupervised; 
    private List<String> shiftsManaged; 

 
    public Manager(String managerId, String name, String email) {
        this.managerId = managerId;
        this.name = name;
        this.email = email;
        this.isOnDuty = false;
        this.employeesSupervised = new ArrayList<>();
        this.shiftsManaged = new ArrayList<>();
    }

    public void addEmployee(String employeeId) {
        employeesSupervised.add(employeeId);
    }

  
    public boolean removeEmployee(String employeeId) {
        return employeesSupervised.remove(employeeId);
    }

 
    public void addShift(String shiftId) {
        shiftsManaged.add(shiftId);
    }

    
    public boolean removeShift(String shiftId) {
        return shiftsManaged.remove(shiftId);
    }

   
    public double calculateAverageEmployeesPerShift() {
        if (shiftsManaged.isEmpty()) {
            return 0.0;
        }
        return (double) employeesSupervised.size() / shiftsManaged.size();
    }

  
    public int estimateTotalWorkHoursPerDay(int hoursPerShift) {
        return employeesSupervised.size() * hoursPerShift;
    }

   
    public double calculateShiftCoverage(int totalRequiredShifts) {
        if (totalRequiredShifts == 0) {
            return 0.0;
        }
        return ((double) shiftsManaged.size() / totalRequiredShifts) * 100;
    }

    
    public double calculateTotalWages(double hourlyRate, int hoursPerShift) {
        return employeesSupervised.size() * hoursPerShift * hourlyRate;
    }

   
    public List<List<String>> optimizeShiftAllocation() {
        List<List<String>> allocation = new ArrayList<>();
        int numShifts = shiftsManaged.size();
        if (numShifts == 0) {
            return allocation;
        }

        
        for (int i = 0; i < numShifts; i++) {
            allocation.add(new ArrayList<>());
        }

        
        for (int i = 0; i < employeesSupervised.size(); i++) {
            allocation.get(i % numShifts).add(employeesSupervised.get(i));
        }
        return allocation;
    }

  
    public int calculateOvertimeHours(int hoursWorked, int standardHours) {
        return Math.max(0, hoursWorked - standardHours);
    }

    
    public boolean isOnDuty() {
        return isOnDuty;
    }


    public void toggleDutyStatus() {
        isOnDuty = !isOnDuty;
    }

    public String getManagerId() {
        return managerId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean isIsOnDuty() {
        return isOnDuty;
    }

    public List<String> getEmployeesSupervised() {
        return employeesSupervised;
    }

    public List<String> getShiftsManaged() {
        return shiftsManaged;
    }
    
}
