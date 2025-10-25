import org.junit.Before;
import org.junit.Test;

import com.mycompany.restaurant_management_system.Shift;
import com.mycompany.restaurant_management_system.Manager;

import java.util.Date;

import static org.junit.Assert.*;

public class ShiftTest {

    private Shift shift;
    private Manager manager;

    @Before
    public void setUp() {
        manager = new Manager("M001", "Alice", "alice@example.com");
        shift = new Shift("S001", new Date(System.currentTimeMillis() - 3600000), new Date(), "Day", manager);
    }

    @Test
    public void testStartShift() {
        shift.startShift();
        assertFalse(shift.isShiftCompleted());
    }

    @Test
    public void testEndShift() {
        shift.endShift();
        assertTrue(shift.isShiftCompleted());
    }

    @Test
    public void testAssignManager() {
        Manager newManager = new Manager("M002", "Bob", "bob@example.com");
        shift.assignManager(newManager);
        assertEquals("Bob", shift.getManagerInCharge().getName());
    }

//    @Test
//    public void testUpdateShiftTimings() {
//        Date newStartTime = new Date(System.currentTimeMillis() - 7200000); 
//        Date newEndTime = new Date();
//        shift.updateShiftTimings(newStartTime, newEndTime);
//        assertEquals(newStartTime, shift.getStartTime());
//        assertEquals(newEndTime, shift.getEndTime());
//    }

    @Test
    public void testCalculateShiftDuration() {
        double duration = shift.calculateShiftDuration();
        assertTrue(duration > 0);
    }

    @Test
    public void testCalculateOvertimeHours_NoOvertime() {
        double overtime = shift.calculateOvertimeHours(8);
        assertEquals(0.0, overtime, 0.01);
    }

    @Test
    public void testCalculateOvertimeHours_WithOvertime() {
        Date longStartTime = new Date(System.currentTimeMillis() - 32400000); // 9 hours ago
        shift.setStartTime(longStartTime);
        double overtime = shift.calculateOvertimeHours(8);
        assertTrue(overtime > 0);
    }

    @Test
    public void testCalculateTotalEarnings_NoOvertime() {
        double hourlyWage = 15.0;
        double earnings = shift.calculateTotalEarnings(hourlyWage);
        assertTrue(earnings > 0);
    }

    @Test
    public void testCalculateTotalEarnings_WithOvertime() {
        Date longStartTime = new Date(System.currentTimeMillis() - 32400000); // 9 hours ago
        shift.setStartTime(longStartTime);
        double hourlyWage = 15.0;
        double earnings = shift.calculateTotalEarnings(hourlyWage);
        assertTrue(earnings > 15.0 * 8);
    }

    @Test
    public void testCalculateShiftProductivity() {
        double totalTasks = 50.0;
        double shiftDuration = shift.calculateShiftDuration();
        double productivity = shift.calculateShiftProductivity(totalTasks, shiftDuration);
        assertTrue(productivity > 0);
    }

    @Test
    public void testCalculateManagerPerformance() {
        double revenueGenerated = 500.0;
        double shiftDuration = shift.calculateShiftDuration();
        double performance = shift.calculateManagerPerformance(revenueGenerated, shiftDuration);
        assertTrue(performance > 0);
    }

    @Test
    public void testGetShiftDetails() {
        shift.getShiftDetails();
        assertEquals("Day", shift.getShiftType());
        assertEquals("Alice", shift.getManagerInCharge().getName());
    }
}
