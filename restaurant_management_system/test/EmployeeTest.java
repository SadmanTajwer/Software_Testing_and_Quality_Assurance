import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.mycompany.restaurant_management_system.Employee;

import java.util.Map;

public class EmployeeTest {

    private Employee employee;

    @Before
    public void setUp() {
        
    	employee = new Employee("E001", "Rafiul Islam", "Waiter", 3500.00, "123-456-7890");
    }

    @Test
    public void testMarkAttendance() {
        employee.markAttendance(true);
        assertTrue(employee.isPresent());

        employee.markAttendance(false);
        assertFalse(employee.isPresent()); 
    }

    @Test
    public void testAssignRole() {
        Employee emp = new Employee("E01", "Sadman", "Waiter", 3000.0, "1234567890");
        emp.assignRole("Chef");
        assertEquals("Chef", emp.getRole()); 
    }



    @Test
    public void testUpdateEmployeeDetails() {
      
        employee.updateEmployeeDetails("Mim Bin Hossain", "987-654-3210", 4000.00);

        assertEquals("Mim Bin Hossain", employee.getName()); 
        assertEquals("987-654-3210", employee.getContactNumber()); 
        assertEquals(4000.00, employee.getSalary(), 0.01); 
    }

    @Test
    public void testCalculateSalary() {
       
        employee.addPerformanceMetric("ordersHandled", 120);
        
     
        employee.setTotalHoursWorked(160);

        
        double finalSalary = employee.calculateSalary(30, 20, 10, 2);
        assertTrue(finalSalary > 0); 
    }

    @Test
    public void testCalculateOvertimeBonus() {
        double overtimeBonus = employee.calculateOvertimeBonus(5); 
        assertEquals(75.0, overtimeBonus, 0.01); 
    }
    @Test
    public void testCalculateVacationDeduction() {
        Employee employee = new Employee("E01", "John Doe", "Waiter", 4000.0, "1234567890"); 
        double vacationDeduction = employee.calculateVacationDeduction(2); 
        assertEquals(266.67, vacationDeduction, 0.01); 
    }

    @Test
    public void testCalculateTax() {
        Employee emp = new Employee("E02", "Jane Smith", "Chef", 4000.0, "0987654321");
        double expectedTax = 400.0; 
        assertEquals(expectedTax, emp.calculateTax(), 0.01); 
    }

    @Test
    public void testCalculateEfficiency() {
       
        employee.addPerformanceMetric("ordersHandled", 150);
        
      
        employee.setTotalHoursWorked(160);

        double efficiency = employee.calculateEfficiency();
        assertEquals(93.75, efficiency, 0.01); 
    }

    @Test
    public void testGetEmployeePerformance() {
      
        employee.addPerformanceMetric("ordersHandled", 200);

        employee.getEmployeePerformance(); 
       
    }

    @Test
    public void testAddPerformanceMetric() {
        employee.addPerformanceMetric("ordersHandled", 100);
        Map<String, Double> metrics = employee.getPerformanceMetrics();
        
        assertEquals(100.0, metrics.get("ordersHandled"), 0.01); 
    }

    @Test
    public void testViewSchedule() {
      
        employee.assignRole("Waiter");
        employee.viewSchedule(); 

       
        employee.assignRole("Chef");
        employee.viewSchedule(); 
    }

    @Test
    public void testSetTotalHoursWorked() {
        employee.setTotalHoursWorked(120);
        assertEquals(120, employee.getTotalHoursWorked()); 
    }

    @Test
    public void testGetEmployeeDetails() {
        assertEquals("E001", employee.getId());
        assertEquals("Rafiul Islam", employee.getName());
        assertEquals("Waiter", employee.getRole());
        assertEquals(3500.00, employee.getSalary(), 0.01);
        assertEquals("123-456-7890", employee.getContactNumber());
    }
}