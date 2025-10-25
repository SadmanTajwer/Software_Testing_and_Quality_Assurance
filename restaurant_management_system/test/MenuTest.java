package com.mycompany.restaurant_management_system;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

public class MenuTest {
    private Menu menu;
    private MenuItem item1;
    private MenuItem item2;
    private MenuItem item3;
    
    private static final String MENU_ID = "M001";
    private static final String CATEGORY = "Main Course";
    private static final double DELTA = 0.001;

    @Before
    public void setUp() {
        menu = new Menu(MENU_ID, CATEGORY);
        item1 = new MenuItem("Pizza", 15.99, "Italian");
        item2 = new MenuItem("Burger", 12.99, "American");
        item3 = new MenuItem("Pasta", 13.99, "Italian");
    }

    @Test
    public void testMenuInitialization() {
        assertEquals(MENU_ID, menu.getMenuId());
        assertEquals(CATEGORY, menu.getCategory());
        assertTrue(menu.isAvailable());
        assertTrue(menu.getMenuItems().isEmpty());
        assertEquals("", menu.getSpecialOffer());
    }

    @Test
    public void testAddMenuItem() {
        menu.addMenuItem(item1);
        assertEquals(1, menu.getMenuItems().size());
        assertTrue(menu.getMenuItems().contains(item1));
    }

    @Test
    public void testAddMultipleMenuItems() {
        menu.addMenuItem(item1);
        menu.addMenuItem(item2);
        menu.addMenuItem(item3);
        assertEquals(3, menu.getMenuItems().size());
        assertTrue(menu.getMenuItems().containsAll(List.of(item1, item2, item3)));
    }

    @Test
    public void testRemoveMenuItem_ExistingItem() {
        menu.addMenuItem(item1);
        assertTrue(menu.removeMenuItem(item1));
        assertFalse(menu.getMenuItems().contains(item1));
        assertEquals(0, menu.getMenuItems().size());
    }

    @Test
    public void testRemoveMenuItem_NonExistingItem() {
        assertFalse(menu.removeMenuItem(item1));
    }

    @Test
    public void testUpdateMenuItemPrice_ExistingItem() {
        menu.addMenuItem(item1);
        double newPrice = 18.99;
        menu.updateMenuItemPrice(item1, newPrice);
        assertEquals(newPrice, item1.getPrice(), DELTA);
    }

    @Test
    public void testUpdateMenuItemPrice_NonExistingItem() {
        double originalPrice = item1.getPrice();
        menu.updateMenuItemPrice(item1, 18.99);
        assertEquals(originalPrice, item1.getPrice(), DELTA);
    }

//    @Test
//    public void testSearchItemByCategory() {
//        menu.addMenuItem(item1); // Italian
//        menu.addMenuItem(item2); // American
//        menu.addMenuItem(item3); // Italian
//
//        List<MenuItem> italianItems = menu.searchItemByCategory("Italian");
//        assertEquals(2, italianItems.size());
//        assertTrue(italianItems.contains(item1));
//        assertTrue(italianItems.contains(item3));
//    }

//    @Test
//    public void testSearchItemByCategory_NonExistingCategory() {
//        menu.addMenuItem(item1);
//        menu.addMenuItem(item2);
//        
//        List<MenuItem> chineseItems = menu.searchItemByCategory("Chinese");
//        assertTrue(chineseItems.isEmpty());
//    }

//    @Test
//    public void testSearchItemByCategory_CaseInsensitive() {
//        menu.addMenuItem(item1);
//        List<MenuItem> italianItems = menu.searchItemByCategory("iTaLiAn");
//        assertEquals(1, italianItems.size());
//        assertTrue(italianItems.contains(item1));
//    }

    @Test
    public void testDisplayMenu() {
        menu.addMenuItem(item1);
        menu.addMenuItem(item2);
        // Testing if method executes without throwing exception
        menu.displayMenu();
    }

    @Test
    public void testGetPopularDishes_LessThanThreeItems() {
        menu.addMenuItem(item1);
        menu.addMenuItem(item2);
        
        List<MenuItem> popularDishes = menu.getPopularDishes();
        assertEquals(2, popularDishes.size());
        assertTrue(popularDishes.contains(item1));
        assertTrue(popularDishes.contains(item2));
    }

    @Test
    public void testGetPopularDishes_ThreeOrMoreItems() {
        menu.addMenuItem(item1);
        menu.addMenuItem(item2);
        menu.addMenuItem(item3);
        MenuItem item4 = new MenuItem("Salad", 8.99, "Appetizer");
        menu.addMenuItem(item4);

        List<MenuItem> popularDishes = menu.getPopularDishes();
        assertEquals(3, popularDishes.size());
        assertTrue(popularDishes.contains(item1));
        assertTrue(popularDishes.contains(item2));
        assertTrue(popularDishes.contains(item3));
        assertFalse(popularDishes.contains(item4));
    }

    @Test
    public void testGetPopularDishes_EmptyMenu() {
        List<MenuItem> popularDishes = menu.getPopularDishes();
        assertTrue(popularDishes.isEmpty());
    }

    @Test
    public void testSetAndGetSpecialOffer() {
        String offer = "20% off on all items";
        menu.setSpecialOffer(offer);
        assertEquals(offer, menu.getSpecialOffer());
    }

    @Test
    public void testSetAndGetAvailable() {
        menu.setAvailable(false);
        assertFalse(menu.isAvailable());
        
        menu.setAvailable(true);
        assertTrue(menu.isAvailable());
    }

    @Test
    public void testGetMenuItems_Modifiable() {
        menu.addMenuItem(item1);
        List<MenuItem> items = menu.getMenuItems();
        items.add(item2);
        assertEquals(2, menu.getMenuItems().size());
    }
}