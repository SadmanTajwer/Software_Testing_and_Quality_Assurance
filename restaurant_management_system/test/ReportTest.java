package com.mycompany.restaurant_management_system;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ReportTest {
    private Report report;
    private static final double DELTA = 0.001; // Delta for double comparisons

    @Before
    public void setUp() {
        report = new Report("R001", "Financial");
    }

    @Test
    public void testReportCreation() {
        assertEquals("R001", report.getReportId());
        assertEquals("Financial", report.getReportType());
        assertFalse(report.isReportGenerated());
        assertTrue(report.getDepartmentWiseRevenue().isEmpty());
        assertTrue(report.getDepartmentWiseExpenses().isEmpty());
    }

    @Test
    public void testAddDepartmentRevenue() {
        report.addDepartmentRevenue("Restaurant", 1000.0);
        report.addDepartmentRevenue("Bar", 500.0);
        report.addDepartmentRevenue("Restaurant", 500.0);
        
        assertEquals(1500.0, report.getDepartmentWiseRevenue().get("Restaurant"), DELTA);
        assertEquals(500.0, report.getDepartmentWiseRevenue().get("Bar"), DELTA);
        assertEquals(2000.0, report.calculateTotalRevenue(), DELTA);
    }

    @Test
    public void testAddDepartmentExpense() {
        report.addDepartmentExpense("Restaurant", 600.0);
        report.addDepartmentExpense("Bar", 300.0);
        report.addDepartmentExpense("Restaurant", 400.0);
        
        assertEquals(1000.0, report.getDepartmentWiseExpenses().get("Restaurant"), DELTA);
        assertEquals(300.0, report.getDepartmentWiseExpenses().get("Bar"), DELTA);
        assertEquals(1300.0, report.calculateTotalExpenses(), DELTA);
    }

    @Test
    public void testGenerateReport() {
        report.addDepartmentRevenue("Restaurant", 1000.0);
        report.addDepartmentExpense("Restaurant", 600.0);
        
        report.generateReport();
        assertTrue(report.isReportGenerated());
        
        // Test generating report again
        report.generateReport(); // Should not affect the calculations
        assertEquals(1000.0, report.calculateTotalRevenue(), DELTA);
        assertEquals(600.0, report.calculateTotalExpenses(), DELTA);
    }

//    @Test
//    public void testCalculateAverageDepartmentRevenue() {
//        report.addDepartmentRevenue("Restaurant", 1000.0);
//        report.addDepartmentRevenue("Bar", 500.0);
//        
//        assertEquals(750.0, report.calculateAverageDepartmentRevenue(), DELTA);
//        
//        // Test with no departments
//        Report emptyReport = new Report("R002", "Empty");
//        assertEquals(0.0, emptyReport.calculateAverageDepartmentRevenue(), DELTA);
//    }

    @Test
    public void testCalculateDepartmentProfit() {
        report.addDepartmentRevenue("Restaurant", 1000.0);
        report.addDepartmentExpense("Restaurant", 600.0);
        
        assertEquals(400.0, report.calculateDepartmentProfit("Restaurant"), DELTA);
        assertEquals(0.0, report.calculateDepartmentProfit("NonExistent"), DELTA);
    }

    @Test
    public void testCalculateStatistics() {
        report.addDepartmentRevenue("Restaurant", 1000.0);
        report.addDepartmentRevenue("Bar", 500.0);
        report.addDepartmentRevenue("Catering", 750.0);
        
        // Test variance and standard deviation
        double variance = report.calculateRevenueVariance();
        double stdDev = report.calculateRevenueStandardDeviation();
        
        assertTrue(variance > 0);
        assertTrue(stdDev > 0);
        assertEquals(Math.sqrt(variance), stdDev, DELTA);
    }

    @Test
    public void testCalculateProfitMargin() {
        report.addDepartmentRevenue("Restaurant", 1000.0);
        report.addDepartmentExpense("Restaurant", 600.0);
        report.generateReport();
        
        assertEquals(40.0, report.calculateProfitMargin(), DELTA);
        
        // Test with zero revenue
        Report zeroReport = new Report("R003", "Zero");
        assertEquals(0.0, zeroReport.calculateProfitMargin(), DELTA);
    }

    @Test
    public void testCalculateGrowth() {
        report.addDepartmentRevenue("Restaurant", 1000.0);
        report.addDepartmentExpense("Restaurant", 600.0);
        
        assertEquals(25.0, report.calculateDepartmentRevenueGrowth("Restaurant", 800.0), DELTA);
        assertEquals(20.0, report.calculateDepartmentExpenseGrowth("Restaurant", 500.0), DELTA);
        
        // Test with zero previous values
        assertEquals(0.0, report.calculateDepartmentRevenueGrowth("Restaurant", 0.0), DELTA);
        assertEquals(0.0, report.calculateDepartmentExpenseGrowth("Restaurant", 0.0), DELTA);
    }

    @Test
    public void testCalculateRevenueContribution() {
        report.addDepartmentRevenue("Restaurant", 1000.0);
        report.addDepartmentRevenue("Bar", 500.0);
        report.calculateTotalRevenue();
        
        assertEquals(66.667, report.calculateRevenueContribution("Restaurant"), DELTA);
        assertEquals(33.333, report.calculateRevenueContribution("Bar"), DELTA);
        assertEquals(0.0, report.calculateRevenueContribution("NonExistent"), DELTA);
    }

    @Test
    public void testGetReportSummary() {
        report.addDepartmentRevenue("Restaurant", 1000.0);
        report.addDepartmentExpense("Restaurant", 600.0);
        report.generateReport();
        
        String summary = report.getReportSummary();
        assertTrue(summary.contains("Report ID: R001"));
        assertTrue(summary.contains("Report Type: Financial"));
        assertTrue(summary.contains("Total Revenue: 1000.0"));
        assertTrue(summary.contains("Total Expenses: 600.0"));
    }
}