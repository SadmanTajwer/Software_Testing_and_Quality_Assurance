package com.mycompany.restaurant_management_system;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class KitchenTest {
    private Kitchen kitchen;
    private static final String KITCHEN_ID = "K001";
    private static final int NUMBER_OF_STAFF = 5;
    private static final int ORDERS_IN_PROGRESS = 10;
    private static final double ELECTRICITY_USAGE_PER_HOUR = 20.0;
    private static final double TOTAL_HOURS_WORKED_PER_DAY = 8.0;
    private static final double INGREDIENT_COST_PER_ORDER = 15.0;
    private static final double DELTA = 0.001; // Delta for double comparisons

    @Before
    public void setUp() {
        kitchen = new Kitchen(
            KITCHEN_ID,
            NUMBER_OF_STAFF,
            ORDERS_IN_PROGRESS,
            ELECTRICITY_USAGE_PER_HOUR,
            TOTAL_HOURS_WORKED_PER_DAY,
            INGREDIENT_COST_PER_ORDER
        );
    }

    @Test
    public void testCalculateDailyElectricityCost() {
        double electricityRatePerKWh = 0.12;
        double expectedCost = ELECTRICITY_USAGE_PER_HOUR * TOTAL_HOURS_WORKED_PER_DAY * electricityRatePerKWh;
        assertEquals(expectedCost, kitchen.calculateDailyElectricityCost(electricityRatePerKWh), DELTA);
    }

    @Test
    public void testCalculateDailyElectricityCostWithZeroRate() {
        assertEquals(0.0, kitchen.calculateDailyElectricityCost(0.0), DELTA);
    }

    @Test
    public void testCalculateTotalIngredientCost() {
        double expectedCost = ORDERS_IN_PROGRESS * INGREDIENT_COST_PER_ORDER;
        assertEquals(expectedCost, kitchen.calculateTotalIngredientCost(), DELTA);
    }

    @Test
    public void testPredictOrderCompletionTime() {
        double averageCookingTimePerOrder = 30.0;
        double expectedTime = (ORDERS_IN_PROGRESS * averageCookingTimePerOrder) / NUMBER_OF_STAFF;
        assertEquals(expectedTime, kitchen.predictOrderCompletionTime(averageCookingTimePerOrder), DELTA);
    }

    @Test
    public void testPredictOrderCompletionTimeWithZeroCookingTime() {
        assertEquals(0.0, kitchen.predictOrderCompletionTime(0.0), DELTA);
    }

    @Test
    public void testCalculateStaffEfficiency() {
        double idealOrdersPerHour = 5.0;
        double actualOrdersPerHour = ORDERS_IN_PROGRESS / TOTAL_HOURS_WORKED_PER_DAY;
        double expectedEfficiency = (actualOrdersPerHour / idealOrdersPerHour) * 100;
        assertEquals(expectedEfficiency, kitchen.calculateStaffEfficiency(idealOrdersPerHour), DELTA);
    }

//    @Test
//    public void testCalculateStaffEfficiencyWithZeroIdealOrders() {
//        // Test handling of division by zero
//        try {
//            kitchen.calculateStaffEfficiency(0.0);
//            fail("Expected ArithmeticException or similar for division by zero");
//        } catch (Exception e) {
//            // Test passes if exception is thrown
//            assertTrue(true);
//        }
//    }

    @Test
    public void testEstimateMonthlyElectricityCost() {
        double electricityRatePerKWh = 0.12;
        int operationalDays = 30;
        double dailyCost = kitchen.calculateDailyElectricityCost(electricityRatePerKWh);
        double expectedMonthlyCost = dailyCost * operationalDays;
        assertEquals(expectedMonthlyCost, 
                    kitchen.estimateMonthlyElectricityCost(electricityRatePerKWh, operationalDays), 
                    DELTA);
    }

    @Test
    public void testEstimateMonthlyElectricityCostWithZeroDays() {
        assertEquals(0.0, kitchen.estimateMonthlyElectricityCost(0.12, 0), DELTA);
    }

    @Test
    public void testCalculateProfitabilityPerOrder() {
        double orderSellingPrice = 50.0;
        double costPerOrder = INGREDIENT_COST_PER_ORDER + 
                            (ELECTRICITY_USAGE_PER_HOUR * TOTAL_HOURS_WORKED_PER_DAY / ORDERS_IN_PROGRESS);
        double expectedProfit = orderSellingPrice - costPerOrder;
        assertEquals(expectedProfit, kitchen.calculateProfitabilityPerOrder(orderSellingPrice), DELTA);
    }

    @Test
    public void testCalculateProfitabilityPerOrderWithBreakEven() {
        double costPerOrder = INGREDIENT_COST_PER_ORDER + 
                            (ELECTRICITY_USAGE_PER_HOUR * TOTAL_HOURS_WORKED_PER_DAY / ORDERS_IN_PROGRESS);
        assertEquals(0.0, kitchen.calculateProfitabilityPerOrder(costPerOrder), DELTA);
    }

    @Test
    public void testDisplayKitchenDetails() {
        // Testing if method executes without throwing exception
        kitchen.displayKitchenDetails();
    }

    @Test
    public void testEdgeCases() {
        Kitchen edgeKitchen = new Kitchen(
            "K002",
            1,  // Minimum staff
            1,  // Minimum orders
            0.1,  // Minimal electricity usage
            1.0,  // Minimum hours
            0.1   // Minimal ingredient cost
        );
        
        // Test with minimal values
        assertTrue(edgeKitchen.calculateDailyElectricityCost(0.12) > 0);
        assertTrue(edgeKitchen.calculateTotalIngredientCost() > 0);
        assertTrue(edgeKitchen.predictOrderCompletionTime(30) > 0);
    }

    @Test
    public void testHighLoadScenario() {
        Kitchen highLoadKitchen = new Kitchen(
            "K003",
            20,  // High staff count
            100, // High order count
            50.0, // High electricity usage
            24.0, // Maximum hours
            25.0  // High ingredient cost
        );
        
        // Test with high load values
        assertTrue(highLoadKitchen.calculateDailyElectricityCost(0.12) > 0);
        assertTrue(highLoadKitchen.calculateTotalIngredientCost() > 0);
        assertTrue(highLoadKitchen.predictOrderCompletionTime(30) > 0);
    }
}