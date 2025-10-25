package com.mycompany.restaurant_management_system;

import java.util.HashMap;
import java.util.Map;

public class Payment {
    private String paymentId;
    private String orderId;
    private String customerId;
    private double amount;
    private String paymentMethod;
    private boolean isPaid;
    private static Map<String, Double> taxRatesByLocation = new HashMap<>();

    public Payment(String paymentId, String orderId, String customerId, double amount, String paymentMethod) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.customerId = customerId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.isPaid = false;
    }

    public double calculateTotalWithTaxAndService(String location, double serviceChargeRate) {
        double taxRate = taxRatesByLocation.getOrDefault(location, 0.0);
        double tax = amount * taxRate;
        double serviceCharge = amount * serviceChargeRate;
        return amount + tax + serviceCharge;
    }

    public double applyMultiTierDiscount(double percentageTier1, double percentageTier2, double threshold) {
        if (amount > threshold) {
            double tier1Discount = threshold * (percentageTier1 / 100);
            double tier2Discount = (amount - threshold) * (percentageTier2 / 100);
            return amount - (tier1Discount + tier2Discount);
        } else {
            return amount - (amount * (percentageTier1 / 100));
        }
    }

    public Map<String, Double> calculateSplitWithCustomShares(Map<String, Double> shares) {
        Map<String, Double> splitPayments = new HashMap<>();
        double totalShares = shares.values().stream().mapToDouble(Double::doubleValue).sum();
        if (totalShares == 0) throw new IllegalArgumentException("Total shares cannot be zero.");
        shares.forEach((person, share) -> splitPayments.put(person, (amount * share) / totalShares));
        return splitPayments;
    }

    public double calculateCashbackWithLimits(double cashbackPercentage, double maxCashback) {
        if (!paymentMethod.equalsIgnoreCase("Mobile Payment")) return 0.0;
        double cashback = (amount * cashbackPercentage) / 100;
        return Math.min(cashback, maxCashback);
    }

    public double calculatePenaltyForLatePayment(int daysLate, double dailyPenaltyRate, double maxPenalty) {
        double penalty = daysLate * (amount * dailyPenaltyRate / 100);
        return Math.min(penalty, maxPenalty);
    }

    public boolean validatePayment(double paidAmount, boolean includePenalty, int daysLate, double dailyPenaltyRate) {
        double totalAmount = amount;
        if (includePenalty) {
            totalAmount += calculatePenaltyForLatePayment(daysLate, dailyPenaltyRate, amount * 0.5);
        }
        return paidAmount >= totalAmount;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void markAsPaid() {
        this.isPaid = true;
    }

    public static void addTaxRate(String location, double taxRate) {
        taxRatesByLocation.put(location, taxRate);
    }

    public static double getTaxRate(String location) {
        return taxRatesByLocation.getOrDefault(location, 0.0);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId='" + paymentId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", amount=" + amount +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", isPaid=" + isPaid +
                '}';
    }
}
