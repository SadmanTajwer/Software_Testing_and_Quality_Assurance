package com.mycompany.restaurant_management_system;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {
    private String reservationId;
    private Customer customer;
    private Date reservationDate;
    private Date reservationTime;
    private int numberOfGuests;
    private String status;

    public Reservation(String reservationId, Customer customer, Date reservationDate, Date reservationTime, int numberOfGuests) {
        this.reservationId = reservationId;
        this.customer = customer;
        this.reservationDate = reservationDate;
        this.reservationTime = reservationTime;
        this.numberOfGuests = numberOfGuests;
        this.status = "Pending";
    }

    public void createReservation() {
        if (checkAvailability()) {
            this.status = "Confirmed";
            System.out.println("Reservation confirmed: " + reservationId);
        } else {
            System.out.println("Sorry, no availability for the requested time.");
        }
    }

    public void cancelReservation() {
        if (status.equals("Confirmed")) {
            status = "Cancelled";
            System.out.println("Reservation " + reservationId + " has been cancelled.");
        } else {
            System.out.println("Reservation cannot be cancelled because it was not confirmed.");
        }
    }

    public boolean checkAvailability() {
        System.out.println("Checking availability for reservation on " + reservationDate + " at " + reservationTime);
        return true;
    }

    public void updateReservationTime(Date newReservationTime) {
        this.reservationTime = newReservationTime;
        System.out.println("Reservation time for " + reservationId + " updated to: " + reservationTime);
    }

    public void sendConfirmationMessage() {
        if (status.equals("Confirmed")) {
            System.out.println("Sending confirmation message to " + customer.getName() + " for reservation: " + reservationId);
        } else {
            System.out.println("Cannot send confirmation. Reservation is not confirmed.");
        }
    }

    public void viewReservationDetails() {
        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Customer: " + customer.getName());
        System.out.println("Reservation Date: " + reservationDate);
        System.out.println("Reservation Time: " + reservationTime);
        System.out.println("Number of Guests: " + numberOfGuests);
        System.out.println("Reservation Status: " + status);
    }

    public long calculateReservationDuration() {
        long duration = reservationTime.getTime() - reservationDate.getTime();
        return TimeUnit.MILLISECONDS.toMinutes(duration);
    }

    public double calculateEstimatedBill(double pricePerGuest) {
        return numberOfGuests * pricePerGuest;
    }

    public int calculateWaitingTime(Date currentDate) {
        long timeDifference = reservationTime.getTime() - currentDate.getTime();
        return (int) TimeUnit.MILLISECONDS.toMinutes(timeDifference);
    }

    public double applyDiscount(double discountPercentage) {
        double bill = calculateEstimatedBill(25.0); // Assuming 25 is the base price per guest
        return bill - (bill * discountPercentage / 100);
    }

    public String getReservationId() {
        return reservationId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public Date getReservationTime() {
        return reservationTime;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
