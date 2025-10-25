package com.mycompany.restaurant_management_system;

import java.util.ArrayList;
import java.util.List;

public class Notification {
    private String notificationId;
    private String message;
    private String recipient; 
    private String type; 
    private int priority; 
    private boolean isRead;


    private static List<Notification> notificationList = new ArrayList<>();


    public Notification(String notificationId, String message, String recipient, String type, int priority) {
        this.notificationId = notificationId;
        this.message = message;
        this.recipient = recipient;
        this.type = type;
        this.priority = priority;
        this.isRead = false;
        notificationList.add(this); 
    }


    public void markAsRead() {
        this.isRead = true;
    }


    public static List<Notification> getUnreadNotifications(String recipient) {
        List<Notification> unreadNotifications = new ArrayList<>();
        for (Notification notification : notificationList) {
            if (!notification.isRead && (notification.recipient.equals(recipient) || notification.recipient.equals("All"))) {
                unreadNotifications.add(notification);
            }
        }
        return unreadNotifications;
    }


    public static List<Notification> getNotificationsByType(String type) {
        List<Notification> filteredNotifications = new ArrayList<>();
        for (Notification notification : notificationList) {
            if (notification.type.equals(type)) {
                filteredNotifications.add(notification);
            }
        }
        return filteredNotifications;
    }


    public static boolean deleteNotification(String notificationId) {
        for (Notification notification : notificationList) {
            if (notification.notificationId.equals(notificationId)) {
                notificationList.remove(notification);
                return true;
            }
        }
        return false;
    }


    public static int countNotificationsByPriority(int priority) {
        int count = 0;
        for (Notification notification : notificationList) {
            if (notification.priority == priority) {
                count++;
            }
        }
        return count;
    }

   
    public static List<Notification> getNotificationsForRecipient(String recipient) {
        List<Notification> recipientNotifications = new ArrayList<>();
        for (Notification notification : notificationList) {
            if (notification.recipient.equals(recipient) || notification.recipient.equals("All")) {
                recipientNotifications.add(notification);
            }
        }
        return recipientNotifications;
    }

  
    public String getNotificationId() {
        return notificationId;
    }

    public String getMessage() {
        return message;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getType() {
        return type;
    }

    public int getPriority() {
        return priority;
    }

    public boolean isRead() {
        return isRead;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "ID='" + notificationId + '\'' +
                ", Message='" + message + '\'' +
                ", Recipient='" + recipient + '\'' +
                ", Type='" + type + '\'' +
                ", Priority=" + priority +
                ", Read=" + isRead +
                '}';
    }
}
