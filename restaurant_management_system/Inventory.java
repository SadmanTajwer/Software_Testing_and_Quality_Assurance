package com.mycompany.restaurant_management_system;

public class Inventory {
    public String itemName;
    public int quantity;
    public double unitPrice;
    public double reorderThreshold;
    public double discountPercentage;
    public double taxPercentage;

    public Inventory(String itemName, int quantity, double unitPrice, double reorderThreshold, double discountPercentage, double taxPercentage) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.reorderThreshold = reorderThreshold;
        this.discountPercentage = discountPercentage;
        this.taxPercentage = taxPercentage;
    }

    public double calculateBulkDiscountedValue() {
        double bulkDiscount = quantity > 100 ? 0.15 : 0.0; // 15% bulk discount for quantities over 100
        double discountedPricePerUnit = unitPrice - (unitPrice * (discountPercentage + bulkDiscount) / 100);
        return discountedPricePerUnit * quantity;
    }

    public double calculateTotalCostWithSurcharge() {
        double baseCost = calculateBulkDiscountedValue();
        double taxAmount = baseCost * taxPercentage / 100;
        double lowStockSurcharge = quantity < reorderThreshold ? baseCost * 0.05 : 0; // 5% surcharge if low stock
        return baseCost + taxAmount + lowStockSurcharge;
    }

    public double estimateMonthlyDemand(double lastMonthDemand, double smoothingFactor) {

        smoothingFactor = Math.min(Math.max(smoothingFactor, 0), 1); // Ensure α is between 0 and 1
        double currentSales = quantity * 0.8; // Assume 80% of current inventory sells in a month
        return (smoothingFactor * currentSales) + ((1 - smoothingFactor) * lastMonthDemand);
    }

   
    public int calculateReorderAmount(double averageDailySales, int leadTimeDays) {
        double requiredStock = averageDailySales * leadTimeDays;
        double reorderAmount = requiredStock - quantity;
        return (int) Math.max(reorderAmount, 0); // Avoid negative reorder amounts
    }

 
    public double calculateDepreciatedValue(int ageInDays) {
        double depreciationRate = 0.02; // 2% per day
        double depreciatedValue = quantity * unitPrice * Math.pow(1 - depreciationRate, ageInDays);
        return Math.max(depreciatedValue, 0); // Ensure value does not go negative
    }

   
    public double optimizeRestockingCost(int restockQuantity, double bulkDiscountThreshold, double bulkDiscountRate) {
        double pricePerUnit = unitPrice;
        if (restockQuantity > bulkDiscountThreshold) {
            pricePerUnit -= (pricePerUnit * bulkDiscountRate / 100);
        }
        return pricePerUnit * restockQuantity;
    }


    public void displayInventoryDetails() {
        System.out.println("Item Name: " + itemName);
        System.out.println("Quantity: " + quantity);
        System.out.println("Unit Price: $" + unitPrice);
        System.out.println("Bulk Discounted Value: $" + calculateBulkDiscountedValue());
        System.out.println("Total Cost with Tax and Surcharge: $" + calculateTotalCostWithSurcharge());
        System.out.println("Depreciated Value (30 days): $" + calculateDepreciatedValue(30));
        System.out.println("Needs Restocking: " + (quantity < reorderThreshold ? "Yes" : "No"));
    }
}
