package com.mycompany.restaurant_management_system;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class OrderTest {
    private Order order;
    private Customer customer;
    private Table table;
    private Waiter waiter;
    private MenuItem menuItem1;
    private MenuItem menuItem2;

    @Before
    public void setUp() {
        customer = new Customer("John Doe", "C1", "john@example.com", null);
        table = new Table("T1", 4, "Window");
        waiter = new Waiter("Jane Smith", "W1", "jane@restaurant.com");
        order = new Order("ORD001", customer, table, waiter);
        
        // Create test menu items
        menuItem1 = new MenuItem("Pizza", "Main Course", 15.99, "Fresh and hot pizza", 20);
        menuItem2 = new MenuItem("Salad", "Appetizer", 8.99, "Fresh garden salad", 10);
    }

    @Test
    public void testOrderCreation() {
        assertNotNull(order);
        assertEquals("ORD001", order.getOrderId());
        assertEquals(customer, order.getCustomer());
        assertEquals(table, order.getTable());
        assertEquals(waiter, order.getWaiter());
        assertEquals("Pending", order.getStatus());
        assertEquals(0.0, order.getTotalPrice(), 0.01);
        assertTrue(order.getItemsOrdered().isEmpty());
    }

    @Test
    public void testCreateOrder() {
        // Add some items first
        order.addItemToOrder(menuItem1);
        order.addItemToOrder(menuItem2);
        
        // Create new order should clear everything
        order.createOrder();
        
        assertEquals(0.0, order.getTotalPrice(), 0.01);
        assertEquals("Pending", order.getStatus());
        assertTrue(order.getItemsOrdered().isEmpty());
        assertNotNull(order.getOrderTime());
    }

    @Test
    public void testAddItemToOrder() {
        order.addItemToOrder(menuItem1);
        
        assertEquals(1, order.getItemsOrdered().size());
        assertEquals(15.99, order.getTotalPrice(), 0.01);
        assertTrue(order.getItemsOrdered().contains(menuItem1));
    }

    @Test
    public void testRemoveItemFromOrder() {
        order.addItemToOrder(menuItem1);
        order.addItemToOrder(menuItem2);
        
        assertTrue(order.removeItemFromOrder(menuItem1));
        assertEquals(1, order.getItemsOrdered().size());
        assertEquals(8.99, order.getTotalPrice(), 0.01);
        assertFalse(order.getItemsOrdered().contains(menuItem1));
    }

    @Test
    public void testRemoveNonExistentItem() {
        order.addItemToOrder(menuItem1);
        
        assertFalse(order.removeItemFromOrder(menuItem2));
        assertEquals(1, order.getItemsOrdered().size());
        assertEquals(15.99, order.getTotalPrice(), 0.01);
    }

    @Test
    public void testGetOrderTotal() {
        order.addItemToOrder(menuItem1);
        order.addItemToOrder(menuItem2);
        
        assertEquals(24.98, order.getOrderTotal(), 0.01);
    }

    @Test
    public void testApplyValidDiscount() {
        order.addItemToOrder(menuItem1); // 15.99
        order.applyDiscount(20); // 20% discount
        
        assertEquals(12.792, order.getTotalPrice(), 0.01);
    }

    @Test
    public void testApplyInvalidDiscount() {
        order.addItemToOrder(menuItem1); // 15.99
        double originalPrice = order.getTotalPrice();
        
        order.applyDiscount(-10); // Invalid discount
        assertEquals(originalPrice, order.getTotalPrice(), 0.01);
        
        order.applyDiscount(110); // Invalid discount
        assertEquals(originalPrice, order.getTotalPrice(), 0.01);
    }

    @Test
    public void testSetOrderStatus() {
        order.setOrderStatus("In Progress");
        assertEquals("In Progress", order.getStatus());
        
        order.setOrderStatus("Completed");
        assertEquals("Completed", order.getStatus());
    }

    @Test
    public void testSetAndGetPreparationTime() {
        order.setPreparationTime(30);
        assertEquals(30, order.getPreparationTime());
    }

    @Test
    public void testSetAndGetIngredientsRequired() {
        List<String> ingredients = Arrays.asList("Tomato", "Cheese", "Flour");
        order.setIngredientsRequired(ingredients);
        assertEquals(ingredients, order.getIngredientsRequired());
    }

    @Test
    public void testOrderTimeIsSetOnCreation() {
        Date beforeCreation = new Date();
        Order newOrder = new Order("ORD002", customer, table, waiter);
        Date afterCreation = new Date();
        
        Date orderTime = newOrder.getOrderTime();
        assertNotNull(orderTime);
        assertTrue(orderTime.after(beforeCreation) || orderTime.equals(beforeCreation));
        assertTrue(orderTime.before(afterCreation) || orderTime.equals(afterCreation));
    }
}