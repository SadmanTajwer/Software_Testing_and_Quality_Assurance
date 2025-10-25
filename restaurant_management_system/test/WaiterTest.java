import org.junit.Before;
import org.junit.Test;

import com.mycompany.restaurant_management_system.Customer;
import com.mycompany.restaurant_management_system.Order;
import com.mycompany.restaurant_management_system.Table;
import com.mycompany.restaurant_management_system.Waiter;


import java.util.List;

import static org.junit.Assert.*;

public class WaiterTest {

    private Waiter waiter;
    private Table table1;
    private Table table2;
    private Order order1;

    @Before
    public void setUp() {
        waiter = new Waiter("W001", "Sadman");
        table1 = new Table("1", 4, "Near Window"); // Use numeric string for tableId
        table2 = new Table("2", 2, "Corner");

        Customer customer1 = new Customer("C001", "Alice", "alice@example.com", "1234567890");
        

        order1 = new Order("O001", customer1, table1, waiter);
       
    }


//    @Test
//    public void testTakeOrder() {
//        waiter.takeOrder(order1);
//        List<Order> orders = waiter.getOrdersHandled();
//        assertTrue(orders.contains(order1));
//    }

    @Test
    public void testServeOrder() {
        waiter.takeOrder(order1);
        order1.setOrderStatus("Prepared");
        waiter.serveOrder("O001");
        assertEquals("Served", order1.getStatus());
    }

    @Test
    public void testServeOrder_NotPrepared() {
        waiter.takeOrder(order1);
        waiter.serveOrder("O001");
        assertNotEquals("Served", order1.getStatus());
    }

    @Test
    public void testUpdateOrderStatus() {
        waiter.takeOrder(order1);
        waiter.updateOrderStatus("O001", "Delivered");
        assertEquals("Delivered", order1.getStatus());
    }

    @Test
    public void testCalculateTotalTips() {
        double totalTips = waiter.calculateTotalTips(10, 5.0);
        assertEquals(50.0, totalTips, 0.01);
    }

    @Test
    public void testCalculateCustomerSatisfaction() {
        double satisfaction = waiter.calculateCustomerSatisfaction(10, 8);
        assertEquals(0.8, satisfaction, 0.01);
    }

    @Test
    public void testCalculateAverageBillAmount() {
        double averageBill = waiter.calculateAverageBillAmount(10, 500);
        assertEquals(50.0, averageBill, 0.01);
    }

    @Test
    public void testCalculateWorkload() {
        double workload = waiter.calculateWorkload(10, 5);
        assertEquals(2.0, workload, 0.01);
    }

    @Test
    public void testCalculateWaiterPerformance() {
        double performance = waiter.calculateWaiterPerformance(20, 500);
        assertEquals(0.04, performance, 0.01);
    }
    public void testHandleCustomerRequest() {
        waiter.assignTable(table1);
        waiter.handleCustomerRequest(1, "Need a glass of water");
    }
    @Test
    public void testProcessBill() {
        waiter.assignTable(table1); 
        waiter.processBill(1, 100.0, 10.0);
        assertEquals(10.0, waiter.getTipsReceived(), 0.01); 
    }


    @Test
    public void testViewAssignedTables() {
        waiter.assignTable(table1);
        waiter.assignTable(table2);
        List<Table> tables = waiter.getAssignedTables();
        assertTrue(tables.contains(table1));
        assertTrue(tables.contains(table2));
    }

    @Test
    public void testToggleDutyStatus() {
        waiter.toggleDutyStatus(true);
        assertTrue(waiter.isOnDuty());
        waiter.toggleDutyStatus(false);
        assertFalse(waiter.isOnDuty());
    }

    @Test
    public void testAssignTable() {
        waiter.assignTable(table1);
        assertTrue(waiter.getAssignedTables().contains(table1));
    }
}