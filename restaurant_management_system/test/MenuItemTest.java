package com.mycompany.restaurant_management_system;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class MenuItemTest {
    private MenuItem menuItem;
    private static final String ITEM_ID = "MI001";
    private static final String NAME = "Spaghetti Carbonara";
    private static final double PRICE = 15.99;
    private static final String CATEGORY = "Main Course";
    private static final double DELTA = 0.001;

    @Before
    public void setUp() {
        menuItem = new MenuItem(ITEM_ID, NAME, PRICE, CATEGORY);
    }

    @Test
    public void testMenuItemInitialization() {
        assertEquals(ITEM_ID, menuItem.getItemId());
        assertEquals(NAME, menuItem.getName());
        assertEquals(PRICE, menuItem.getPrice(), DELTA);
        assertEquals(CATEGORY, menuItem.getCategory());
        assertTrue(menuItem.isAvailable());
        assertTrue(menuItem.getIngredients().isEmpty());
    }

    @Test
    public void testCalculatePreparationTime_MainCourse() {
        List<String> ingredients = Arrays.asList("Pasta", "Eggs", "Cheese", "Pepper");
        menuItem.updateIngredients(ingredients);
        int expectedTime = 10 + 10 + (ingredients.size() / 2); // base + main course + ingredients factor
        assertEquals(expectedTime, menuItem.calculatePreparationTime());
    }

    @Test
    public void testCalculatePreparationTime_Dessert() {
        MenuItem dessertItem = new MenuItem("D001", "Tiramisu", 8.99, "Dessert");
        List<String> ingredients = Arrays.asList("Coffee", "Mascarpone", "Ladyfingers");
        dessertItem.updateIngredients(ingredients);
        int expectedTime = 10 + 5 + (ingredients.size() / 2); // base + dessert + ingredients factor
        assertEquals(expectedTime, dessertItem.calculatePreparationTime());
    }

    @Test
    public void testCalculatePreparationTime_OtherCategory() {
        MenuItem appetizerItem = new MenuItem("A001", "Salad", 6.99, "Appetizer");
        List<String> ingredients = Arrays.asList("Lettuce", "Tomato", "Cucumber");
        appetizerItem.updateIngredients(ingredients);
        int expectedTime = 10 + (ingredients.size() / 2); // base + ingredients factor only
        assertEquals(expectedTime, appetizerItem.calculatePreparationTime());
    }

    @Test
    public void testUpdateIngredients() {
        List<String> newIngredients = Arrays.asList("Pasta", "Eggs", "Cheese");
        menuItem.updateIngredients(newIngredients);
        assertEquals(newIngredients, menuItem.getIngredients());
    }

    @Test
    public void testUpdateIngredients_EmptyList() {
        List<String> emptyIngredients = new ArrayList<>();
        menuItem.updateIngredients(emptyIngredients);
        assertTrue(menuItem.getIngredients().isEmpty());
    }

    @Test
    public void testViewItemDetails() {
        // Testing if method executes without throwing exception
        menuItem.viewItemDetails();
    }

    @Test
    public void testMarkAsSpecial() {
        // Testing if method executes without throwing exception
        menuItem.markAsSpecial();
    }

    @Test
    public void testChangeAvailabilityStatus() {
        menuItem.changeAvailabilityStatus(false);
        assertFalse(menuItem.isAvailable());

        menuItem.changeAvailabilityStatus(true);
        assertTrue(menuItem.isAvailable());
    }

    @Test
    public void testGetAllergensInfo() {
        // Testing if method executes without throwing exception
        menuItem.getAllergensInfo();
    }

    @Test
    public void testSetPrice() {
        double newPrice = 18.99;
        menuItem.setPrice(newPrice);
        assertEquals(newPrice, menuItem.getPrice(), DELTA);
    }

    @Test
    public void testSetCategory() {
        String newCategory = "Appetizer";
        menuItem.setCategory(newCategory);
        assertEquals(newCategory, menuItem.getCategory());
    }

    @Test
    public void testSetPrice_ZeroPrice() {
        menuItem.setPrice(0.0);
        assertEquals(0.0, menuItem.getPrice(), DELTA);
    }

    @Test
    public void testSetPrice_NegativePrice() {
        double originalPrice = menuItem.getPrice();
        menuItem.setPrice(-10.0);
        // Assuming negative prices should be allowed (based on current implementation)
        assertEquals(-10.0, menuItem.getPrice(), DELTA);
    }

    @Test
    public void testCalculatePreparationTime_NoIngredients() {
        menuItem.updateIngredients(new ArrayList<>());
        assertEquals(20, menuItem.calculatePreparationTime()); // base + main course
    }

    @Test
    public void testCalculatePreparationTime_ManyIngredients() {
        List<String> manyIngredients = Arrays.asList(
            "Ingredient1", "Ingredient2", "Ingredient3", "Ingredient4",
            "Ingredient5", "Ingredient6", "Ingredient7", "Ingredient8"
        );
        menuItem.updateIngredients(manyIngredients);
        int expectedTime = 10 + 10 + (manyIngredients.size() / 2);
        assertEquals(expectedTime, menuItem.calculatePreparationTime());
    }

//    @Test
//    public void testGetIngredients_Modifiable() {
//        List<String> initialIngredients = Arrays.asList("Pasta", "Sauce");
//        menuItem.updateIngredients(initialIngredients);
//        
//        List<String> ingredients = menuItem.getIngredients();
//        // Test if the returned list is modifiable
//        ingredients.add("Cheese");
//        
//        assertEquals(3, menuItem.getIngredients().size());
//        assertTrue(menuItem.getIngredients().contains("Cheese"));
//    }
}