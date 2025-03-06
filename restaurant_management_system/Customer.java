package com.mycompany.restaurant_management_system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Customer {
    private String customerId;
    private String name;
    private String email;
    private String contactNumber;
    private List<String> orderHistory; 
    private List<String> reservations; 
    private Map<String, Integer> orderStatusCount;
    private double totalSpent;
    private boolean isPremiumMember;

    public Customer(String customerId, String name, String email, String contactNumber) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.contactNumber = contactNumber;
        this.orderHistory = new ArrayList<>();
        this.reservations = new ArrayList<>();
        this.orderStatusCount = new HashMap<>();
        this.totalSpent = 0;
        this.isPremiumMember = false;
    }

 
    public void placeOrder(String orderId, double orderTotal, String orderStatus) {
        orderHistory.add("Order ID: " + orderId + " | Total: $" + orderTotal);
        totalSpent += orderTotal;
        updateOrderStatusCount(orderStatus);
        System.out.println("Order placed: " + orderId + " | Total: $" + orderTotal);
    }

   
    private void updateOrderStatusCount(String status) {
        orderStatusCount.put(status, orderStatusCount.getOrDefault(status, 0) + 1);
    }

  
    public void viewOrderHistory() {
        System.out.println(name + "'s Order History:");
        for (String order : orderHistory) {
            System.out.println(order);
        }
        System.out.println("Total spent: $" + totalSpent);
    }

 
    public void giveFeedback(String orderId, String feedback) {
        System.out.println("Feedback for Order " + orderId + ": " + feedback);
     
    }

   
    public void getRecentOrdersSummary() {
        System.out.println(name + "'s Recent Orders Summary:");
        int orderCount = 0;
        for (String order : orderHistory) {
            if (orderCount == 5) break;  
            System.out.println(order);
            orderCount++;
        }
    }

    
    public void findOrdersByStatus(String status) {
        System.out.println("Finding all orders with status: " + status);
        for (String order : orderHistory) {
            if (order.contains(status)) {
                System.out.println(order);
            }
        }
    }

  
    public void displayOrderStatusCount() {
        System.out.println(name + "'s Order Status Count:");
        for (Map.Entry<String, Integer> entry : orderStatusCount.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " orders");
        }
    }


    public double getTotalAmountSpent() {
        System.out.println(name + " has spent a total of: $" + totalSpent);
        return totalSpent;
    }

    
    public int calculateLoyaltyPoints() {
        int loyaltyPoints = (int) (totalSpent / 10);
        System.out.println(name + " has " + loyaltyPoints + " loyalty points.");
        return loyaltyPoints;
    }

   
    public void makeReservation(String tableId, String reservationTime) {
      
        boolean isAvailable = checkReservationAvailability(reservationTime);
        if (isAvailable) {
            reservations.add("Table ID: " + tableId + " | Reservation Time: " + reservationTime);
            System.out.println("Reservation made for Table " + tableId + " on " + reservationTime);
        } else {
            System.out.println("The requested time is not available. Please choose another time.");
        }
    }

   
    public void cancelReservation(String tableId, String reservationTime) {
        String reservationDetails = "Table ID: " + tableId + " | Reservation Time: " + reservationTime;
        if (reservations.remove(reservationDetails)) {
            System.out.println("Reservation for Table " + tableId + " has been canceled.");
        } else {
            System.out.println("No reservation found to cancel.");
        }
    }

  
    private boolean checkReservationAvailability(String reservationTime) {
        for (String reservation : reservations) {
            if (reservation.contains(reservationTime)) {
                return false; 
            }
        }
        return true;
    }

 
    public void checkPremiumMembership() {
        if (totalSpent > 500) {
            isPremiumMember = true;
            System.out.println(name + " has been upgraded to Premium Membership.");
        } else {
            System.out.println(name + " needs to spend more to qualify for Premium Membership.");
        }
    }

 
    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public List<String> getOrderHistory() {
        return orderHistory;
    }

    public List<String> getReservations() {
        return reservations;
    }

    public boolean isPremiumMember() {
        return isPremiumMember;
    }
}
