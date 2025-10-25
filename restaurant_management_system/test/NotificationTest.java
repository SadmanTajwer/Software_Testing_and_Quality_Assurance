package com.mycompany.restaurant_management_system;

import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class NotificationTest {

    @Before
    public void setUp() {
        // Clear all notifications before each test
        Notification.getNotificationsForRecipient("All").clear();
    }

    @Test
    public void testNotificationCreation() {
        Notification notification = new Notification("1", "Test Message", "User1", "Info", 1);
        assertEquals("1", notification.getNotificationId());
        assertEquals("Test Message", notification.getMessage());
        assertEquals("User1", notification.getRecipient());
        assertEquals("Info", notification.getType());
        assertEquals(1, notification.getPriority());
        assertFalse(notification.isRead());
    }

    @Test
    public void testMarkAsRead() {
        Notification notification = new Notification("2", "Another Message", "User2", "Warning", 2);
        assertFalse(notification.isRead());
        notification.markAsRead();
        assertTrue(notification.isRead());
    }

//    @Test
//    public void testGetUnreadNotifications() {
//        new Notification("3", "Message 1", "User3", "Info", 1);
//        new Notification("4", "Message 2", "User3", "Info", 1);
//        new Notification("5", "Message 3", "All", "Info", 1);
//
//        List<Notification> unreadNotifications = Notification.getUnreadNotifications("User3");
//        assertEquals(3, unreadNotifications.size());
//
//        unreadNotifications.get(0).markAsRead();
//        List<Notification> updatedUnreadNotifications = Notification.getUnreadNotifications("User3");
//        assertEquals(2, updatedUnreadNotifications.size());
//    }
//
//    @Test
//    public void testGetNotificationsByType() {
//        new Notification("6", "Type Info", "User4", "Info", 1);
//        new Notification("7", "Type Warning", "User4", "Warning", 2);
//        new Notification("8", "Another Type Info", "User4", "Info", 3);
//
//        List<Notification> infoNotifications = Notification.getNotificationsByType("Info");
//        assertEquals(2, infoNotifications.size());
//    }

    @Test
    public void testDeleteNotification() {
        Notification notification = new Notification("9", "Message to delete", "User5", "Info", 1);
        assertTrue(Notification.deleteNotification("9"));
        assertFalse(Notification.getNotificationsForRecipient("User5").contains(notification));
    }

//    @Test
//    public void testCountNotificationsByPriority() {
//        new Notification("10", "Priority 1", "User6", "Info", 1);
//        new Notification("11", "Priority 1 Again", "User6", "Warning", 1);
//        new Notification("12", "Priority 2", "User6", "Info", 2);
//
//        assertEquals(2, Notification.countNotificationsByPriority(1));
//        assertEquals(1, Notification.countNotificationsByPriority(2));
//    }

    @Test
    public void testGetNotificationsForRecipient() {
        new Notification("13", "Message for User7", "User7", "Info", 1);
        new Notification("14", "Message for All", "All", "Warning", 1);

        List<Notification> recipientNotifications = Notification.getNotificationsForRecipient("User7");
        assertEquals(2, recipientNotifications.size());
    }
}
