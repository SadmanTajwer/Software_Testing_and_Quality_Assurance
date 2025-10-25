package com.mycompany.restaurant_management_system;

public class Kitchen {
    private String kitchenId;
    private int numberOfStaff;
    private int ordersInProgress;
    private double electricityUsagePerHour; // in kWh
    private double totalHoursWorkedPerDay;
    private double ingredientCostPerOrder;

    
    public Kitchen(String kitchenId, int numberOfStaff, int ordersInProgress, double electricityUsagePerHour, double totalHoursWorkedPerDay, double ingredientCostPerOrder) {
        this.kitchenId = kitchenId;
        this.numberOfStaff = numberOfStaff;
        this.ordersInProgress = ordersInProgress;
        this.electricityUsagePerHour = electricityUsagePerHour;
        this.totalHoursWorkedPerDay = totalHoursWorkedPerDay;
        this.ingredientCostPerOrder = ingredientCostPerOrder;
    }

    public double calculateDailyElectricityCost(double electricityRatePerKWh) {
        double totalElectricityUsed = electricityUsagePerHour * totalHoursWorkedPerDay;
        return totalElectricityUsed * electricityRatePerKWh;
    }

    public double calculateTotalIngredientCost() {
        return ordersInProgress * ingredientCostPerOrder;
    }

  
    public double predictOrderCompletionTime(double averageCookingTimePerOrder) {
        return ordersInProgress * averageCookingTimePerOrder / numberOfStaff; 
    }


    public double calculateStaffEfficiency(double idealOrdersPerHour) {
        double actualOrdersPerHour = ordersInProgress / totalHoursWorkedPerDay;
        return (actualOrdersPerHour / idealOrdersPerHour) * 100;
    }

   
    public double estimateMonthlyElectricityCost(double electricityRatePerKWh, int operationalDays) {
        return calculateDailyElectricityCost(electricityRatePerKWh) * operationalDays;
    }

 
    public double calculateProfitabilityPerOrder(double orderSellingPrice) {
        double costPerOrder = ingredientCostPerOrder + (electricityUsagePerHour * totalHoursWorkedPerDay / ordersInProgress);
        return orderSellingPrice - costPerOrder;
    }

    public void displayKitchenDetails() {
        System.out.println("Kitchen ID: " + kitchenId);
        System.out.println("Number of Staff: " + numberOfStaff);
        System.out.println("Orders in Progress: " + ordersInProgress);
        System.out.println("Daily Electricity Cost: $" + calculateDailyElectricityCost(0.12)); 
        System.out.println("Total Ingredient Cost: $" + calculateTotalIngredientCost());
        System.out.println("Predicted Order Completion Time: " + predictOrderCompletionTime(30) + " minutes"); 
        System.out.println("Staff Efficiency: " + calculateStaffEfficiency(5) + "%"); 
        System.out.println("Estimated Monthly Electricity Cost: $" + estimateMonthlyElectricityCost(0.12, 30)); 
    }
}
