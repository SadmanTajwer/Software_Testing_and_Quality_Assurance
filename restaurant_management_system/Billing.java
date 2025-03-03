package com.mycompany.restaurant_management_system;

public class Billing {
    
    private String customerName;
    private String[] itemsOrdered;
    private double[] itemPrices;
    private double totalAmount;
    private double discount;
    private double taxRate;

  
    public Billing(String customerName, String[] itemsOrdered, double[] itemPrices, double taxRate) {
        this.customerName = customerName;
        this.itemsOrdered = itemsOrdered;
        this.itemPrices = itemPrices;
        this.taxRate = taxRate;
        this.totalAmount = calculateTotalAmount();
        this.discount = calculateDiscount();
    }

    private double calculateTotalAmount() {
        double total = 0;
        for (double price : itemPrices) {
            total += price;
        }
     
        total += total * (taxRate / 100); // Adding tax
        total += total * 0.05; // Adding 5% service charge
        return total;
    }

 
    public double calculateDiscount() {
        double discount = 0;
        
       
        if (totalAmount > 1500) {
            discount = totalAmount * 0.15; // 15% discount for large orders
        } else if (totalAmount > 1000) {
            discount = totalAmount * 0.10; // 10% discount for medium orders
        } else if (totalAmount > 500) {
            discount = totalAmount * 0.05; // 5% discount for smaller orders
        }

      
        if (itemsOrdered.length > 3) {
            discount += 50; // Fixed additional discount of 50 if more than 3 items are ordered
        }

        return discount;
    }

  
    public double calculateFinalBill() {
        return totalAmount - discount;
    }

   
    public void printReceipt() {
        System.out.println("Customer: " + customerName);
        System.out.println("Items Ordered:");
        for (int i = 0; i < itemsOrdered.length; i++) {
            System.out.println(itemsOrdered[i] + " - Price: " + itemPrices[i]);
        }
        System.out.println("Subtotal: " + calculateTotalAmount() / (1 + (taxRate / 100) + 0.05)); // Subtotal without tax/service charge
        System.out.println("Tax (Rate " + taxRate + "%): " + calculateTotalAmount() * (taxRate / 100) / (1 + 0.05)); // Tax amount
        System.out.println("Service Charge: " + (calculateTotalAmount() * 0.05) / (1 + (taxRate / 100))); // Service charge
        System.out.println("Discount Applied: " + discount);
        System.out.println("Final Bill: " + calculateFinalBill());
    }

 
    public void applyCustomDiscount(double customDiscountPercentage) {
        discount = totalAmount * (customDiscountPercentage / 100);
        System.out.println("Custom Discount Applied: " + discount);
        System.out.println("New Final Bill: " + calculateFinalBill());
    }

    
    public void applyLoyaltyDiscount(double previousSpend) {
       
        if (previousSpend > 5000) {
            discount += totalAmount * 0.10; // Additional 10% off for high-spending customers
        } else if (previousSpend > 2000) {
            discount += totalAmount * 0.05; // Additional 5% off for mid-tier spenders
        }
        System.out.println("Loyalty Discount Applied: " + discount);
        System.out.println("New Final Bill with Loyalty: " + calculateFinalBill());
    }
}
