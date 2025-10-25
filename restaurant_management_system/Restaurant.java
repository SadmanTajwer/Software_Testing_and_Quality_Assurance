package com.mycompany.restaurant_management_system;

import java.util.HashMap;
import java.util.Map;

public class Restaurant {
    private String name;
    private String address;
    private String contactNumber;
    private Map<String, Employee> employees;
    private Map<String, MenuItem> menuItems;
    private double totalRevenue;
    private double costOfGoodsSold;

    public Restaurant(String name, String address, String contactNumber) {
        this.setName(name);
        this.setAddress(address);
        this.setContactNumber(contactNumber);
        this.employees = new HashMap<>();
        this.menuItems = new HashMap<>();
        this.totalRevenue = 0.0;
        this.costOfGoodsSold = 0.0;
    }

    public void addOrUpdateEmployee(Employee employee) {
        employees.put(employee.getId(), employee);
    }

    public boolean removeEmployee(String employeeId) {
        if (employees.containsKey(employeeId)) {
            employees.remove(employeeId);
            return true;
        }
        return false;
    }

    public void addOrUpdateMenuItem(MenuItem menuItem) {
        menuItems.put(menuItem.getItemId(), menuItem);
    }

    public boolean removeMenuItem(String menuItemId) {
        if (menuItems.containsKey(menuItemId)) {
            menuItems.remove(menuItemId);
            return true;
        }
        return false;
    }

    public int getMenuItemCount() {
        return menuItems.size();
    }

    public void updateRestaurantDetails(String newName, String newAddress, String newContactNumber) {
        this.setName(newName);
        this.setAddress(newAddress);
        this.setContactNumber(newContactNumber);
    }

    public double calculateTotalRevenue() {
        return totalRevenue;
    }

    public void addRevenue(double amount) {
        this.totalRevenue += amount;
    }

    
    

    public void generateMenuReport() {
        for (MenuItem menuItem : menuItems.values()) {
            System.out.println(menuItem);
        }
    }

    public void generateEmployeeReport() {
        for (Employee employee : employees.values()) {
            System.out.println(employee);
        }
    }

    public double calculateGrossProfit() {
        return totalRevenue - costOfGoodsSold;
    }

    public double calculateNetProfit(double operatingExpenses) {
        double grossProfit = calculateGrossProfit();
        return grossProfit - operatingExpenses;
    }

    public double calculateAverageOrderValue() {
        double totalOrders = menuItems.size();
        return totalOrders == 0 ? 0 : totalRevenue / totalOrders;
    }

    

    public void trackCostOfGoodsSold(double amount) {
        this.costOfGoodsSold += amount;
    }

    public double calculateEmployeeCompensation(String employeeId, double hourlyRate, double hoursWorked) {
        Employee employee = employees.get(employeeId);
        if (employee != null) {
            return hourlyRate * hoursWorked;
        }
        return 0.0;
    }

    public double calculateRevenuePerEmployee() {
        int totalEmployees = employees.size();
        return totalEmployees == 0 ? 0 : totalRevenue / totalEmployees;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
}
