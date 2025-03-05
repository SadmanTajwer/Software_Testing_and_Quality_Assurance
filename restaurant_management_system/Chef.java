package com.mycompany.restaurant_management_system;

import java.util.ArrayList;
import java.util.List;

public class Chef {
    private String chefId;
    private String name;
    List<Order> assignedOrders;
    private String specialty;
    private boolean isAvailable;
    private int totalDishesPrepared;

    
    public Chef(String chefId, String name, String specialty) {
        this.setChefId(chefId);
        this.name = name;
        this.specialty = specialty;
        this.assignedOrders = new ArrayList<>();
        this.setAvailable(true); // Chef starts as available
        this.setTotalDishesPrepared(0);
    }

   
    public List<String> createSpecialMenu(List<String> menuItems) {
        List<String> specialMenu = new ArrayList<>();
        for (String item : menuItems) {
            if (item.contains(specialty)) {
                specialMenu.add(item);
            }
        }
        System.out.println("Special menu created by " + name + ": " + specialMenu);
        return specialMenu;
    }

    // Update inventory requirement based on orders
    public void updateInventoryRequirement() {
        System.out.println("Updating inventory requirements for the assigned orders:");
        for (Order order : assignedOrders) {
            System.out.println("- Ingredients for order ID " + order.getOrderId() + ": " + order.getIngredientsRequired());
        }
    }

   
    public void prepareOrder(String orderId) {
        for (Order order : assignedOrders) {
            if (order.getOrderId().equals(orderId)) {
                System.out.println("Preparing order: " + orderId);
                order.setOrderStatus("Completed");
                setTotalDishesPrepared(getTotalDishesPrepared() + 1);
                System.out.println("Order " + orderId + " prepared by " + name);
                return;
            }
        }
        System.out.println("Order ID " + orderId + " not found in assigned orders.");
    }

   
    public void viewAssignedOrders() {
        System.out.println("Orders assigned to Chef " + name + ":");
        for (Order order : assignedOrders) {
            System.out.println("- Order ID: " + order.getOrderId() + ", Status: " + order.getStatus());
        }
    }

    
    public void suggestMenuChanges(List<String> popularItems) {
        System.out.println("Chef " + name + " suggests adding the following popular items:");
        for (String item : popularItems) {
            System.out.println("- " + item);
        }
    }

  
    public int checkDishPreparationTime(String orderId) {
        for (Order order : assignedOrders) {
            if (order.getOrderId().equals(orderId)) {
                int prepTime = order.getPreparationTime();
                System.out.println("Preparation time for order " + orderId + ": " + prepTime + " minutes");
                return prepTime;
            }
        }
        System.out.println("Order ID " + orderId + " not found.");
        return 0;
    }


	public String getChefId() {
		return chefId;
	}


	public void setChefId(String chefId) {
		this.chefId = chefId;
	}


	public boolean isAvailable() {
		return isAvailable;
	}


	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}


	public int getTotalDishesPrepared() {
		return totalDishesPrepared;
	}


	public void setTotalDishesPrepared(int totalDishesPrepared) {
		this.totalDishesPrepared = totalDishesPrepared;
	}
}
