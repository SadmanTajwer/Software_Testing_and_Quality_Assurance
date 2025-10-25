package com.mycompany.restaurant_management_system;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RestaurantTest {
    private Restaurant restaurant;
    private Employee employee1;
    private Employee employee2;
    private MenuItem menuItem1;
    private MenuItem menuItem2;

    @Before
    public void setUp() {
        restaurant = new Restaurant("Test Restaurant", "123 Test St", "555-0123");
        
        // Create test employees
        employee1 = new Employee("John Doe", "E1", "john@test.com");
        employee2 = new Employee("Jane Smith", "E2", "jane@test.com");
        
        // Create test menu items
        menuItem1 = new MenuItem("Pizza", "Main Course", 15.99, "Delicious pizza", 20);
        menuItem2 = new MenuItem("Salad", "Appetizer", 8.99, "Fresh salad", 10);
    }

    @Test
    public void testRestaurantCreation() {
        assertEquals("Test Restaurant", restaurant.getName());
        assertEquals("123 Test St", restaurant.getAddress());
        assertEquals("555-0123", restaurant.getContactNumber());
    }

    @Test
    public void testUpdateRestaurantDetails() {
        restaurant.updateRestaurantDetails("New Name", "New Address", "New Contact");
        
        assertEquals("New Name", restaurant.getName());
        assertEquals("New Address", restaurant.getAddress());
        assertEquals("New Contact", restaurant.getContactNumber());
    }

//    @Test
//    public void testAddAndRemoveEmployee() {
//        restaurant.addOrUpdateEmployee(employee1);
//        restaurant.addOrUpdateEmployee(employee2);
//        
//        // Test removing existing employee
//        assertTrue(restaurant.removeEmployee("E1"));
//        
//        // Test removing non-existent employee
//        assertFalse(restaurant.removeEmployee("E3"));
//    }

//    @Test
//    public void testAddAndRemoveMenuItem() {
//        restaurant.addOrUpdateMenuItem(menuItem1);
//        restaurant.addOrUpdateMenuItem(menuItem2);
//        
//        assertEquals(2, restaurant.getMenuItemCount());
//        
//        // Test removing existing menu item
//        assertTrue(restaurant.removeMenuItem(menuItem1.getItemId()));
//        assertEquals(1, restaurant.getMenuItemCount());
//        
//        // Test removing non-existent menu item
//        assertFalse(restaurant.removeMenuItem("nonexistent"));
//    }

    @Test
    public void testUpdateMenuItem() {
        restaurant.addOrUpdateMenuItem(menuItem1);
        MenuItem updatedItem = new MenuItem("Pizza", "Main Course", 17.99, "Updated pizza", 25);
        updatedItem.setItemId(menuItem1.getItemId());
        
        restaurant.addOrUpdateMenuItem(updatedItem);
        assertEquals(1, restaurant.getMenuItemCount());
    }

    @Test
    public void testRevenueCalculations() {
        restaurant.addRevenue(100.0);
        restaurant.addRevenue(150.0);
        
        assertEquals(250.0, restaurant.calculateTotalRevenue(), 0.01);
    }

    @Test
    public void testProfitCalculations() {
        restaurant.addRevenue(1000.0);
        restaurant.trackCostOfGoodsSold(400.0);
        
        // Test gross profit
        assertEquals(600.0, restaurant.calculateGrossProfit(), 0.01);
        
        // Test net profit
        double operatingExpenses = 200.0;
        assertEquals(400.0, restaurant.calculateNetProfit(operatingExpenses), 0.01);
    }

//    @Test
//    public void testAverageOrderValue() {
//        restaurant.addOrUpdateMenuItem(menuItem1);
//        restaurant.addOrUpdateMenuItem(menuItem2);
//        restaurant.addRevenue(1000.0);
//        
//        assertEquals(500.0, restaurant.calculateAverageOrderValue(), 0.01);
//    }

//    @Test
//    public void testEmployeeCompensation() {
//        restaurant.addOrUpdateEmployee(employee1);
//        
//        double compensation = restaurant.calculateEmployeeCompensation("E1", 20.0, 40.0);
//        assertEquals(800.0, compensation, 0.01);
//        
//        // Test non-existent employee
//        compensation = restaurant.calculateEmployeeCompensation("E3", 20.0, 40.0);
//        assertEquals(0.0, compensation, 0.01);
//    }

//    @Test
//    public void testRevenuePerEmployee() {
//        restaurant.addOrUpdateEmployee(employee1);
//        restaurant.addOrUpdateEmployee(employee2);
//        restaurant.addRevenue(1000.0);
//        
//        assertEquals(500.0, restaurant.calculateRevenuePerEmployee(), 0.01);
//        
//        // Test with no employees
//        Restaurant emptyRestaurant = new Restaurant("Empty", "Address", "Phone");
//        emptyRestaurant.addRevenue(1000.0);
//        assertEquals(0.0, emptyRestaurant.calculateRevenuePerEmployee(), 0.01);
//    }

    @Test
    public void testCostOfGoodsSoldTracking() {
        restaurant.trackCostOfGoodsSold(100.0);
        restaurant.trackCostOfGoodsSold(150.0);
        
        // Verify through gross profit calculation
        restaurant.addRevenue(500.0);
        assertEquals(250.0, restaurant.calculateGrossProfit(), 0.01);
    }
}
