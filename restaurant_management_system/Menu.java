package com.mycompany.restaurant_management_system;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private String menuId;
    private String category;
    private List<MenuItem> menuItems;
    private boolean isAvailable;
    private String specialOffer;

    // Constructor
    public Menu(String menuId, String category) {
        this.menuId = menuId;
        this.category = category;
        this.menuItems = new ArrayList<>();
        this.isAvailable = true; 
        this.specialOffer = "";
    }


    public void addMenuItem(MenuItem item) {
        menuItems.add(item);
        System.out.println("Menu item added: " + item.getName());
    }


    public boolean removeMenuItem(MenuItem item) {
        if (menuItems.remove(item)) {
            System.out.println("Menu item removed: " + item.getName());
            return true;
        }
        System.out.println("Menu item not found: " + item.getName());
        return false;
    }

    public void updateMenuItemPrice(MenuItem item, double newPrice) {
        if (menuItems.contains(item)) {
            item.setPrice(newPrice);
            System.out.println("Updated price for " + item.getName() + ": $" + newPrice);
        } else {
            System.out.println("Menu item not found: " + item.getName());
        }
    }

    public List<MenuItem> searchItemByCategory(String category) {
        List<MenuItem> itemsInCategory = new ArrayList<>();
        for (MenuItem item : menuItems) {
            if (item.getCategory().equalsIgnoreCase(category)) {
                itemsInCategory.add(item);
            }
        }
        return itemsInCategory;
    }

    public void displayMenu() {
        System.out.println("Menu Category: " + category);
        for (MenuItem item : menuItems) {
            System.out.println("- " + item.getName() + " | Price: $" + item.getPrice());
        }
    }

    public List<MenuItem> getPopularDishes() {
        List<MenuItem> popularDishes = new ArrayList<>();
       
        int limit = Math.min(3, menuItems.size());
        for (int i = 0; i < limit; i++) {
            popularDishes.add(menuItems.get(i));
        }
        return popularDishes;
    }

    
    public String getMenuId() {
        return menuId;
    }

    public String getCategory() {
        return category;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public String getSpecialOffer() {
        return specialOffer;
    }

    public void setSpecialOffer(String specialOffer) {
        this.specialOffer = specialOffer;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}
