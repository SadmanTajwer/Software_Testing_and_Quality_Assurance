package com.mycompany.restaurant_management_system;

import java.util.HashMap;
import java.util.Map;

public class Promotions {
    private String promotionId;
    private String description;
    private double discountPercentage;
    private String applicableCategory;
    private boolean isActive;
    private static Map<String, Double> locationBasedDiscounts = new HashMap<>();
    private static Map<String, Promotions> activePromotions = new HashMap<>();

    public Promotions(String promotionId, String description, double discountPercentage, String applicableCategory) {
        this.promotionId = promotionId;
        this.description = description;
        this.discountPercentage = discountPercentage;
        this.applicableCategory = applicableCategory;
        this.isActive = true;
    }

    public double applyDiscountBasedOnCategory(String category, double originalAmount) {
        if (applicableCategory.equalsIgnoreCase(category) && isActive) {
            return originalAmount - (originalAmount * discountPercentage / 100);
        }
        return originalAmount;
    }

    public double applyLocationBasedDiscount(String location, double originalAmount) {
        double locationDiscount = locationBasedDiscounts.getOrDefault(location, 0.0);
        if (locationDiscount > 0 && isActive) {
            return originalAmount - (originalAmount * locationDiscount / 100);
        }
        return originalAmount;
    }

    public double combineDiscounts(double originalAmount, double[] additionalDiscounts) {
        double finalAmount = originalAmount;
        for (double discount : additionalDiscounts) {
            finalAmount -= (finalAmount * discount / 100);
        }
        return finalAmount;
    }

    public boolean isEligibleForPromotion(String promotionCode) {
        return activePromotions.containsKey(promotionCode) && isActive;
    }

    public void deactivatePromotion() {
        this.isActive = false;
        activePromotions.remove(promotionId);
    }

    public static void addLocationBasedDiscount(String location, double discountPercentage) {
        locationBasedDiscounts.put(location, discountPercentage);
    }

    public static void addPromotion(String promotionId, Promotions promotion) {
        activePromotions.put(promotionId, promotion);
    }

    public static void removePromotion(String promotionId) {
        activePromotions.remove(promotionId);
    }

    public String getPromotionId() {
        return promotionId;
    }

    public String getDescription() {
        return description;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public String getApplicableCategory() {
        return applicableCategory;
    }

    public boolean isActive() {
        return isActive;
    }

    @Override
    public String toString() {
        return "Promotions{" +
                "promotionId='" + promotionId + '\'' +
                ", description='" + description + '\'' +
                ", discountPercentage=" + discountPercentage +
                ", applicableCategory='" + applicableCategory + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
