package com.mycompany.restaurant_management_system;
import java.util.ArrayList;
import java.util.List;

public class MenuItem {
    private String itemId;
    private String name;
    private double price;
    private String category;
    private List<String> ingredients;
    private boolean isAvailable;
    
    public MenuItem(String itemId, String name, double price, String category) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.category = category;
        this.ingredients = new ArrayList<>();
        this.isAvailable = true; 
    }

  
    public MenuItem(String string, double d, String string2) {
		// TODO Auto-generated constructor stub
	}


	public MenuItem(String string, String string2, double d, String string3, int i) {
		// TODO Auto-generated constructor stub
	}


	public int calculatePreparationTime() {
        int time = 10; 

     
        if (category.equalsIgnoreCase("Main Course")) {
            time += 10; 
        } else if (category.equalsIgnoreCase("Dessert")) {
            time += 5;
        }

     
        time += ingredients.size() / 2; 
        return time;
    }

    public void updateIngredients(List<String> newIngredients) {
        this.ingredients = newIngredients;
        System.out.println("Ingredients updated for " + name);
    }

   
    public void viewItemDetails() {
        System.out.println("Item ID: " + itemId);
        System.out.println("Name: " + name);
        System.out.println("Category: " + category);
        System.out.println("Price: $" + price);
        System.out.println("Available: " + (isAvailable ? "Yes" : "No"));
        System.out.println("Ingredients: " + ingredients);
    }
    public void markAsSpecial() {
        System.out.println(name + " is now marked as a special item!");
    }

    public void changeAvailabilityStatus(boolean availability) {
        this.isAvailable = availability;
        System.out.println("Item " + name + " availability set to: " + (availability ? "Available" : "Not Available"));
    }

   
    public void getAllergensInfo() {
        System.out.println("Allergens info for " + name + ": Contains gluten, dairy.");
    }

   
    public String getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }


	public void setItemId(String itemId2) {
		// TODO Auto-generated method stub
		
	}
}
