package com.mycompany.restaurant_management_system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    private String orderId;
    private Customer customer;
    private List<MenuItem> itemsOrdered;
   private List<String> ingredientsRequired;
    private double totalPrice;
    private String status; 
    private Date orderTime;
    private Table table; 
    private Waiter waiter; 
    private int preparationTime; 

   
    public Order(String orderId, Customer customer, Table table, Waiter waiter) {
        this.orderId = orderId;
        this.customer = customer;
        this.table = table;
        this.waiter = waiter;
        this.itemsOrdered = new ArrayList<>();
        this.totalPrice = 0.0;
        this.status = "Pending";  
        this.orderTime = new Date();  
        this.preparationTime = 0;  
    }

 
    public Order(String string) {
		// TODO Auto-generated constructor stub
	}


	public void createOrder() {
        this.itemsOrdered.clear();
        this.totalPrice = 0.0;
        this.status = "Pending";
        this.orderTime = new Date();
        this.preparationTime = 0;  
        System.out.println("New order created: " + orderId);
    }

  
    public void addItemToOrder(MenuItem item) {
        itemsOrdered.add(item);
        totalPrice += item.getPrice();
        System.out.println("Item added to order: " + item.getName());
    }

   
    public boolean removeItemFromOrder(MenuItem item) {
        if (itemsOrdered.remove(item)) {
            totalPrice -= item.getPrice();
            System.out.println("Item removed from order: " + item.getName());
            return true;
        }
        System.out.println("Item not found in the order: " + item.getName());
        return false;
    }

    
    public double getOrderTotal() {
        return totalPrice;
    }

   
    public void applyDiscount(double discountPercentage) {
        if (discountPercentage > 0 && discountPercentage <= 100) {
            double discountAmount = (discountPercentage / 100) * totalPrice;
            totalPrice -= discountAmount;
            System.out.println("Discount of " + discountPercentage + "% applied. New total: " + totalPrice);
        } else {
            System.out.println("Invalid discount percentage.");
        }
    }

    
    public void checkOrderStatus() {
        System.out.println("Order " + orderId + " status: " + status);
    }

    
    public void setOrderStatus(String newStatus) {
        this.status = newStatus;
        System.out.println("Order " + orderId + " status updated to: " + status);
    }

   
    public void getOrderDetails() {
        System.out.println("Order ID: " + orderId);
        System.out.println("Customer: " + customer.getName());
        System.out.println("Table: " + (table != null ? table.getTableId() : "N/A"));
        System.out.println("Waiter: " + (waiter != null ? waiter.getName() : "N/A"));
        System.out.println("Order Time: " + orderTime);
        System.out.println("Status: " + status);
        System.out.println("Items Ordered:");
        for (MenuItem item : itemsOrdered) {
            System.out.println("- " + item.getName() + " | Price: " + item.getPrice());
        }
        System.out.println("Total Price: " + totalPrice);
    }

    
    public Table getTable() {
        return table;
    }

    public Waiter getWaiter() {
        return waiter;
    }

    public String getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<MenuItem> getItemsOrdered() {
        return itemsOrdered;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public Date getOrderTime() {
        return orderTime;
    }
    public List<String> getIngredientsRequired() {
        return this.ingredientsRequired;
    }

   
    public int getPreparationTime() {
        return preparationTime;
    }

   
    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }


	public void setIngredientsRequired(List<String> asList) {
		// TODO Auto-generated method stub
		
	}
}
