import org.junit.Before;
import org.junit.Test;

import com.mycompany.restaurant_management_system.Customer;

import static org.junit.Assert.*;

import java.util.List;

public class CustomerTest {

    private Customer customer;

    @Before
    public void setUp() {
        customer = new Customer("C001", "Alice", "alice@example.com", "1234567890");
    }

    @Test
    public void testPlaceOrder() {
        customer.placeOrder("O001", 100.0, "Completed");
        assertTrue(customer.getOrderHistory().contains("Order ID: O001 | Total: $100.0"));
        assertEquals(100.0, customer.getTotalAmountSpent(), 0.01);
    }

    @Test
    public void testViewOrderHistory() {
        customer.placeOrder("O001", 100.0, "Completed");
        customer.placeOrder("O002", 50.0, "Pending");
        assertEquals(2, customer.getOrderHistory().size());
    }

    @Test
    public void testGiveFeedback() {
        customer.giveFeedback("O001", "Excellent service!");
        // Verify by checking output manually or with mock console output (if required).
    }

    @Test
    public void testGetRecentOrdersSummary() {
        customer.placeOrder("O001", 100.0, "Completed");
        customer.placeOrder("O002", 50.0, "Pending");
        customer.placeOrder("O003", 75.0, "Completed");
        customer.getRecentOrdersSummary();
        // Verify the last 3 orders are displayed correctly.
    }

    @Test
    public void testFindOrdersByStatus() {
        customer.placeOrder("O001", 100.0, "Completed");
        customer.placeOrder("O002", 50.0, "Pending");
        customer.placeOrder("O003", 75.0, "Completed");
        customer.findOrdersByStatus("Completed");
        // Verify orders with "Completed" status are displayed correctly.
    }

    @Test
    public void testDisplayOrderStatusCount() {
        customer.placeOrder("O001", 100.0, "Completed");
        customer.placeOrder("O002", 50.0, "Pending");
        customer.placeOrder("O003", 75.0, "Completed");
        customer.displayOrderStatusCount();
        // Verify the status counts are accurate.
    }

    @Test
    public void testCalculateLoyaltyPoints() {
        customer.placeOrder("O001", 100.0, "Completed");
        customer.placeOrder("O002", 200.0, "Completed");
        assertEquals(30, customer.calculateLoyaltyPoints()); // 300/10 = 30 points
    }

    @Test
    public void testMakeReservation() {
        customer.makeReservation("T001", "2023-12-19 18:00");
        List<String> reservations = customer.getReservations();
        assertTrue(reservations.contains("Table ID: T001 | Reservation Time: 2023-12-19 18:00"));
    }

    @Test
    public void testCancelReservation() {
        customer.makeReservation("T001", "2023-12-19 18:00");
        customer.cancelReservation("T001", "2023-12-19 18:00");
        List<String> reservations = customer.getReservations();
        assertFalse(reservations.contains("Table ID: T001 | Reservation Time: 2023-12-19 18:00"));
    }

    @Test
    public void testCheckPremiumMembership() {
        customer.placeOrder("O001", 600.0, "Completed");
        customer.checkPremiumMembership();
        assertTrue(customer.isPremiumMember());
    }
}