import org.junit.Before;
import org.junit.Test;

import com.mycompany.restaurant_management_system.Inventory;

import static org.junit.Assert.*;

public class InventoryTest {

    private Inventory inventory;

    @Before
    public void setUp() {
        inventory = new Inventory("Test Item", 50, 10.0, 30, 10.0, 5.0);
    }

    @Test
    public void testCalculateBulkDiscountedValue_NoBulkDiscount() {
        double expectedValue = 50 * (10.0 - (10.0 * 10.0 / 100)); 
        assertEquals(expectedValue, inventory.calculateBulkDiscountedValue(), 0.01);
    }
    
//    @Test
//    public void testCalculateBulkDiscountedValue_WithBulkDiscount() {
// 
//        inventory = new Inventory("Bulk Item", 150, 20.0, 50, 5.0, 8.0);
//
//      
//        double bulkDiscount = 0.15; 
//        double discountPercentage = 5.0; 
//        double unitPrice = 20.0;
//
//      
//        double discountedPricePerUnit = unitPrice - (unitPrice * ((discountPercentage + (bulkDiscount * 100)) / 100));
//        System.out.println("Discounted Price Per Unit: " + discountedPricePerUnit);
//
//    
//        double expectedValue = discountedPricePerUnit * 150;
//        System.out.println("Expected Total Value: " + expectedValue);
//
//        
//        assertEquals(expectedValue, inventory.calculateBulkDiscountedValue(), 0.01);
//    }

    @Test
    public void testCalculateTotalCostWithSurcharge_NoSurcharge() {
        inventory = new Inventory("Test Item", 50, 10.0, 30, 10.0, 5.0); 
        double baseCost = inventory.calculateBulkDiscountedValue();
        double expectedValue = baseCost + (baseCost * 5.0 / 100); 
        assertEquals(expectedValue, inventory.calculateTotalCostWithSurcharge(), 0.01);
    }

    @Test
    public void testCalculateTotalCostWithSurcharge_WithSurcharge() {
        inventory = new Inventory("Low Stock Item", 20, 15.0, 30, 5.0, 10.0); 
        double baseCost = inventory.calculateBulkDiscountedValue();
        double expectedValue = baseCost + (baseCost * 10.0 / 100) + (baseCost * 5.0 / 100); 
        assertEquals(expectedValue, inventory.calculateTotalCostWithSurcharge(), 0.01);
    }

    @Test
    public void testEstimateMonthlyDemand() {
        double lastMonthDemand = 40.0;
        double smoothingFactor = 0.6;
        double expectedDemand = (0.6 * (50 * 0.8)) + (0.4 * lastMonthDemand); 
        assertEquals(expectedDemand, inventory.estimateMonthlyDemand(lastMonthDemand, smoothingFactor), 0.01);
    }

    @Test
    public void testEstimateMonthlyDemand_WithEdgeSmoothingFactor() {
        double lastMonthDemand = 100.0;
        double smoothingFactor = 1.5; 
        double expectedDemand = 50 * 0.8; 
        assertEquals(expectedDemand, inventory.estimateMonthlyDemand(lastMonthDemand, smoothingFactor), 0.01);
    }

    @Test
    public void testCalculateReorderAmount() {
        double averageDailySales = 5.0;
        int leadTimeDays = 10;
        int expectedReorderAmount = (int) (averageDailySales * leadTimeDays - 50);
        assertEquals(expectedReorderAmount, inventory.calculateReorderAmount(averageDailySales, leadTimeDays));
    }

    @Test
    public void testCalculateReorderAmount_NoReorderNeeded() {
        double averageDailySales = 3.0;
        int leadTimeDays = 5;
        int expectedReorderAmount = 0; 
        assertEquals(expectedReorderAmount, inventory.calculateReorderAmount(averageDailySales, leadTimeDays));
    }

    @Test
    public void testCalculateDepreciatedValue() {
        int ageInDays = 30;
        double expectedValue = 50 * 10.0 * Math.pow(1 - 0.02, ageInDays);
        assertEquals(expectedValue, inventory.calculateDepreciatedValue(ageInDays), 0.01);
    }

    @Test
    public void testCalculateDepreciatedValue_NegativeValueHandled() {
        int ageInDays = 1000; 
        assertEquals(0.0, inventory.calculateDepreciatedValue(ageInDays), 0.01);
    }

    @Test
    public void testOptimizeRestockingCost_NoBulkDiscount() {
        int restockQuantity = 50;
        double bulkDiscountThreshold = 100;
        double bulkDiscountRate = 10.0;
        double expectedCost = 50 * 10.0; 
        assertEquals(expectedCost, inventory.optimizeRestockingCost(restockQuantity, bulkDiscountThreshold, bulkDiscountRate), 0.01);
    }

    @Test
    public void testOptimizeRestockingCost_WithBulkDiscount() {
        int restockQuantity = 150;
        double bulkDiscountThreshold = 100;
        double bulkDiscountRate = 10.0;
        double discountedUnitPrice = 10.0 - (10.0 * 10.0 / 100);
        double expectedCost = 150 * discountedUnitPrice;
        assertEquals(expectedCost, inventory.optimizeRestockingCost(restockQuantity, bulkDiscountThreshold, bulkDiscountRate), 0.01);
    }

    @Test
    public void testDisplayInventoryDetails() {
        

     
        assertEquals("Test Item", inventory.itemName); 
        assertEquals(50, inventory.quantity);
        assertEquals(10.0, inventory.unitPrice, 0.01); 
        assertEquals(inventory.calculateBulkDiscountedValue(), 50 * (10.0 - (10.0 * 10.0 / 100)), 0.01); 
        assertEquals(inventory.calculateTotalCostWithSurcharge(),
                inventory.calculateBulkDiscountedValue() +
                        (inventory.calculateBulkDiscountedValue() * 5.0 / 100), 0.01); 
        assertEquals(inventory.calculateDepreciatedValue(30),
                50 * 10.0 * Math.pow(1 - 0.02, 30), 0.01);
    
    }
}
