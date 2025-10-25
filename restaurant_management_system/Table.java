package com.mycompany.restaurant_management_system;

public class Table {
    private String tableId;
    private int seatingCapacity;
    private boolean isOccupied;
    private Customer currentCustomer;
    private Waiter assignedWaiter;
    private String location;

    public Table(String tableId, int seatingCapacity, String location) {
        this.tableId = tableId;
        this.seatingCapacity = seatingCapacity;
        this.isOccupied = false;
        this.location = location;
        this.currentCustomer = null;
        this.assignedWaiter = null;
    }

    public void assignTableToCustomer(Customer customer, Waiter waiter) {
        if (!isOccupied) {
            this.currentCustomer = customer;
            this.assignedWaiter = waiter;
            this.isOccupied = true;
        }
    }

    public void markAsVacant() {
        if (isOccupied) {
            isOccupied = false;
            currentCustomer = null;
            assignedWaiter = null;
        }
    }

    public void requestWaiter() {
        if (assignedWaiter != null) {
            System.out.println("Waiter " + assignedWaiter.getName() + " is coming to table " + tableId + ".");
        }
    }

    public void updateSeatingCapacity(int newCapacity) {
        if (newCapacity > 0) {
            this.seatingCapacity = newCapacity;
        }
    }

    public void getTableDetails() {
        System.out.println("Table ID: " + tableId);
        System.out.println("Seating Capacity: " + seatingCapacity);
        System.out.println("Occupied: " + (isOccupied ? "Yes" : "No"));
        System.out.println("Location: " + location);
        if (isOccupied) {
            System.out.println("Current Customer: " + currentCustomer.getName());
            System.out.println("Assigned Waiter: " + assignedWaiter.getName());
        } else {
            System.out.println("No customer assigned.");
        }
    }

    public double calculateRevenueGenerated(double averageBillPerCustomer, int numberOfCustomersServed) {
        return averageBillPerCustomer * numberOfCustomersServed;
    }

    public double calculateOccupancyRate(int totalTables, int occupiedTables) {
        return (double) occupiedTables / totalTables * 100;
    }

    public double calculateCustomerSatisfaction(double totalReviews, double totalRatingPoints) {
        if (totalReviews == 0) return 0;
        return totalRatingPoints / totalReviews;
    }

    public double calculateWaitTime(int numberOfCustomers, int waiterEfficiency) {
        return (double) numberOfCustomers / waiterEfficiency; // Time in minutes for the waiter to serve all customers
    }

    public double calculateTableUtilization(double revenueGenerated, int operatingHours) {
        if (operatingHours == 0) return 0;
        return revenueGenerated / operatingHours; // Revenue per hour of table utilization
    }

    public String getTableId() {
        return tableId;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public boolean isIsOccupied() {
        return isOccupied;
    }

    public Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public Waiter getAssignedWaiter() {
        return assignedWaiter;
    }

    public String getLocation() {
        return location;
    }
    
    
}
