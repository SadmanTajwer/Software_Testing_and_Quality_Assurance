import org.junit.Before;
import org.junit.Test;

import com.mycompany.restaurant_management_system.Manager;

import java.util.List;

import static org.junit.Assert.*;

public class ManagerTest {

    private Manager manager;

    @Before
    public void setUp() {
        manager = new Manager("M001", "John Doe", "john.doe@example.com");
    }

    @Test
    public void testAddEmployee() {
        manager.addEmployee("E001");
        assertTrue(manager.getEmployeesSupervised().contains("E001"));
    }

    @Test
    public void testRemoveEmployee() {
        manager.addEmployee("E001");
        manager.addEmployee("E002");
        assertTrue(manager.removeEmployee("E001"));
        assertFalse(manager.getEmployeesSupervised().contains("E001"));
    }

    @Test
    public void testAddShift() {
        manager.addShift("S001");
        assertTrue(manager.getShiftsManaged().contains("S001"));
    }

    @Test
    public void testRemoveShift() {
        manager.addShift("S001");
        manager.addShift("S002");
        assertTrue(manager.removeShift("S001"));
        assertFalse(manager.getShiftsManaged().contains("S001"));
    }

    @Test
    public void testCalculateAverageEmployeesPerShift() {
        manager.addEmployee("E001");
        manager.addEmployee("E002");
        manager.addShift("S001");
        manager.addShift("S002");
        double average = manager.calculateAverageEmployeesPerShift();
        assertEquals(1.0, average, 0.01);
    }

    @Test
    public void testEstimateTotalWorkHoursPerDay() {
        manager.addEmployee("E001");
        manager.addEmployee("E002");
        int totalHours = manager.estimateTotalWorkHoursPerDay(8);
        assertEquals(16, totalHours);
    }

    @Test
    public void testCalculateShiftCoverage() {
        manager.addShift("S001");
        manager.addShift("S002");
        double coverage = manager.calculateShiftCoverage(5);
        assertEquals(40.0, coverage, 0.01);
    }

    @Test
    public void testCalculateTotalWages() {
        manager.addEmployee("E001");
        manager.addEmployee("E002");
        double totalWages = manager.calculateTotalWages(15.0, 8);
        assertEquals(240.0, totalWages, 0.01);
    }

    @Test
    public void testOptimizeShiftAllocation() {
        manager.addEmployee("E001");
        manager.addEmployee("E002");
        manager.addEmployee("E003");
        manager.addShift("S001");
        manager.addShift("S002");

        List<List<String>> allocation = manager.optimizeShiftAllocation();
        assertEquals(2, allocation.size());
        assertEquals(2, allocation.get(0).size());
        assertEquals(1, allocation.get(1).size());
    }

    @Test
    public void testCalculateOvertimeHours() {
        int overtime = manager.calculateOvertimeHours(50, 40);
        assertEquals(10, overtime);
    }

    @Test
    public void testToggleDutyStatus() {
        manager.toggleDutyStatus();
        assertTrue(manager.isOnDuty());
        manager.toggleDutyStatus();
        assertFalse(manager.isOnDuty());
    }

    @Test
    public void testGetManagerDetails() {
        assertEquals("M001", manager.getManagerId());
        assertEquals("John Doe", manager.getName());
        assertEquals("john.doe@example.com", manager.getEmail());
    }
}
