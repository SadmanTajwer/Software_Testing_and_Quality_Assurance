package com.mycompany.restaurant_management_system;

import java.util.HashMap;
import java.util.Map;

public class Report {
    private String reportId;
    private String reportType;
    private double totalRevenue;
    private double totalExpenses;
    private double netProfit;
    private Map<String, Double> departmentWiseRevenue;
    private Map<String, Double> departmentWiseExpenses;
    private boolean isGenerated;

    public Report(String reportId, String reportType) {
        this.reportId = reportId;
        this.reportType = reportType;
        this.departmentWiseRevenue = new HashMap<>();
        this.departmentWiseExpenses = new HashMap<>();
        this.isGenerated = false;
    }

    public void addDepartmentRevenue(String department, double revenue) {
        departmentWiseRevenue.put(department, departmentWiseRevenue.getOrDefault(department, 0.0) + revenue);
    }

    public void addDepartmentExpense(String department, double expense) {
        departmentWiseExpenses.put(department, departmentWiseExpenses.getOrDefault(department, 0.0) + expense);
    }

    public double calculateTotalRevenue() {
        totalRevenue = departmentWiseRevenue.values().stream().mapToDouble(Double::doubleValue).sum();
        return totalRevenue;
    }

    public double calculateTotalExpenses() {
        totalExpenses = departmentWiseExpenses.values().stream().mapToDouble(Double::doubleValue).sum();
        return totalExpenses;
    }

    public void calculateNetProfit() {
        netProfit = totalRevenue - totalExpenses;
    }

    public double calculateAverageDepartmentRevenue() {
        if (departmentWiseRevenue.isEmpty()) return 0;
        return totalRevenue / departmentWiseRevenue.size();
    }

    public double calculateDepartmentProfit(String department) {
        double revenue = departmentWiseRevenue.getOrDefault(department, 0.0);
        double expenses = departmentWiseExpenses.getOrDefault(department, 0.0);
        return revenue - expenses;
    }

    public void generateReport() {
        if (isGenerated) {
            System.out.println("Report has already been generated.");
            return;
        }
        calculateTotalRevenue();
        calculateTotalExpenses();
        calculateNetProfit();
        isGenerated = true;
    }

    public boolean isReportGenerated() {
        return isGenerated;
    }

    public double calculateRevenueVariance() {
        double meanRevenue = calculateAverageDepartmentRevenue();
        double variance = departmentWiseRevenue.values().stream()
            .mapToDouble(revenue -> Math.pow(revenue - meanRevenue, 2))
            .sum() / departmentWiseRevenue.size();
        return variance;
    }

    public double calculateRevenueStandardDeviation() {
        double variance = calculateRevenueVariance();
        return Math.sqrt(variance);
    }

    public double calculateExpenseVariance() {
        double meanExpense = departmentWiseExpenses.values().stream().mapToDouble(Double::doubleValue).sum() / departmentWiseExpenses.size();
        double variance = departmentWiseExpenses.values().stream()
            .mapToDouble(expense -> Math.pow(expense - meanExpense, 2))
            .sum() / departmentWiseExpenses.size();
        return variance;
    }

    public double calculateExpenseStandardDeviation() {
        double variance = calculateExpenseVariance();
        return Math.sqrt(variance);
    }

    public double calculateProfitMargin() {
        if (totalRevenue == 0) return 0;
        return (netProfit / totalRevenue) * 100;
    }

    public double calculateDepartmentRevenueGrowth(String department, double previousRevenue) {
        double currentRevenue = departmentWiseRevenue.getOrDefault(department, 0.0);
        if (previousRevenue == 0) return 0;
        return ((currentRevenue - previousRevenue) / previousRevenue) * 100;
    }

    public double calculateDepartmentExpenseGrowth(String department, double previousExpense) {
        double currentExpense = departmentWiseExpenses.getOrDefault(department, 0.0);
        if (previousExpense == 0) return 0;
        return ((currentExpense - previousExpense) / previousExpense) * 100;
    }

    public double calculateRevenueContribution(String department) {
        double departmentRevenue = departmentWiseRevenue.getOrDefault(department, 0.0);
        return (departmentRevenue / totalRevenue) * 100;
    }

    public String getReportSummary() {
        return "Report ID: " + reportId + "\n" +
               "Report Type: " + reportType + "\n" +
               "Total Revenue: " + totalRevenue + "\n" +
               "Total Expenses: " + totalExpenses + "\n" +
               "Net Profit: " + netProfit + "\n" +
               "Revenue Variance: " + calculateRevenueVariance() + "\n" +
               "Revenue Standard Deviation: " + calculateRevenueStandardDeviation() + "\n" +
               "Expense Variance: " + calculateExpenseVariance() + "\n" +
               "Expense Standard Deviation: " + calculateExpenseStandardDeviation() + "\n" +
               "Profit Margin: " + calculateProfitMargin() + "%";
    }

    public Map<String, Double> getDepartmentWiseRevenue() {
        return departmentWiseRevenue;
    }

    public Map<String, Double> getDepartmentWiseExpenses() {
        return departmentWiseExpenses;
    }

    public String getReportId() {
        return reportId;
    }

    public String getReportType() {
        return reportType;
    }

    @Override
    public String toString() {
        return "Report{" +
                "reportId='" + reportId + '\'' +
                ", reportType='" + reportType + '\'' +
                ", totalRevenue=" + totalRevenue +
                ", totalExpenses=" + totalExpenses +
                ", netProfit=" + netProfit +
                ", departmentWiseRevenue=" + departmentWiseRevenue +
                ", departmentWiseExpenses=" + departmentWiseExpenses +
                ", isGenerated=" + isGenerated +
                '}';
    }
}
