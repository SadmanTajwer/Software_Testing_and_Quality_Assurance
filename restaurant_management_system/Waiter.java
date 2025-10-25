package com.mycompany.restaurant_management_system;

import java.util.ArrayList;
import java.util.List;

public class Waiter {
    private String waiterId;
    private String name;
    private List<Table> assignedTables;
    private List<Order> ordersHandled;
    private int tipsReceived;
    private boolean isOnDuty;

    public Waiter(String waiterId, String name) {
        this.waiterId = waiterId;
        this.name = name;
        this.assignedTables = new ArrayList<>();
        this.ordersHandled = new ArrayList<>();
        this.tipsReceived = 0;
        this.isOnDuty = false;
    }

    public Waiter(String string, String string2, String string3) {
		// TODO Auto-generated constructor stub
	}

	public void takeOrder(Order order) {
        ordersHandled.add(order);
        System.out.println(name + " has taken order: " + order.getOrderId());
    }

    public void serveOrder(String orderId) {
        for (Order order : ordersHandled) {
            if (order.getOrderId().equals(orderId)) {
                if (order.getStatus().equals("Prepared")) {
                    order.setOrderStatus("Served");
                    System.out.println("Order " + orderId + " has been served by " + name);
                } else {
                    System.out.println("Order " + orderId + " is not ready to be served.");
                }
                return;
            }
        }
        System.out.println("Order ID " + orderId + " not found.");
    }

    public void updateOrderStatus(String orderId, String status) {
        for (Order order : ordersHandled) {
            if (order.getOrderId().equals(orderId)) {
                order.setOrderStatus(status);
                System.out.println("Order " + orderId + " status updated to: " + status);
                return;
            }
        }
        System.out.println("Order ID " + orderId + " not found.");
    }

    public double calculateTotalTips(int totalOrders, double averageTipPerOrder) {
        return totalOrders * averageTipPerOrder;
    }

    public double calculateCustomerSatisfaction(int totalOrdersHandled, double totalPositiveFeedback) {
        return totalPositiveFeedback / totalOrdersHandled;
    }

    public double calculateAverageBillAmount(int totalOrders, double totalRevenue) {
        return totalRevenue / totalOrders;
    }

    public double calculateWorkload(int totalTablesAssigned, int hoursOnDuty) {
        return (double) totalTablesAssigned / hoursOnDuty; // Tables served per hour
    }

    public double calculateWaiterPerformance(int ordersHandled, double totalSales) {
        return ordersHandled / totalSales; // Orders handled per dollar of total sales
    }

    public void handleCustomerRequest(int tableNumber, String request) {
        String tableNumberStr = String.valueOf(tableNumber); // Convert int to String
        for (Table table : assignedTables) {
            if (table.getTableId().equals(tableNumberStr)) {
                System.out.println("Handling request for table " + tableNumber + ": " + request);
                return;
            }
        }
        System.out.println("Table " + tableNumber + " is not assigned to " + name);
    }


    public void processBill(int tableNumber, double billAmount, double tipPercentage) {
        String tableNumberStr = String.valueOf(tableNumber);
        for (Table table : assignedTables) {
            if (table.getTableId().equals(tableNumberStr)) {
                double tips = (billAmount * tipPercentage) / 100;
                tipsReceived += tips;
                System.out.println("Processed bill for table " + tableNumber + ". Tips received: $" + tips);
                return;
            }
        }
        System.out.println("Table " + tableNumber + " is not assigned to " + name);
    }
    public void viewAssignedTables() {
        System.out.println(name + "'s assigned tables:");
        for (Table table : assignedTables) {
            System.out.println("- Table " + table.getTableId() + " (Seats: " + table.getSeatingCapacity() + ")");
        }
    }

    public void toggleDutyStatus(boolean isNowOnDuty) {
        this.isOnDuty = isNowOnDuty;
        System.out.println(name + " is now " + (isNowOnDuty ? "on duty." : "off duty."));
    }

    public void assignTable(Table table) {
        assignedTables.add(table);
        System.out.println("Table " + table.getTableId() + " assigned to waiter " + name);
    }

    public String getName() {
        return name;
    }

    public String getWaiterId() {
        return waiterId;
    }

    public List<Table> getAssignedTables() {
        return assignedTables;
    }

    public int getTipsReceived() {
        return tipsReceived;
    }

    public boolean isOnDuty() {
        return isOnDuty;
    }
}
