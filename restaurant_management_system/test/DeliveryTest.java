package com.mycompany.restaurant_management_system;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DeliveryTest {
    private Delivery delivery;
    private static final double ORDER_TOTAL = 100.0;
    private static final double DELIVERY_DISTANCE = 5.0;
    private static final double DELIVERY_TIME = 30.0;
    private static final double DELTA = 0.001; // Delta for double comparisons

    @Before
    public void setUp() {
        delivery = new Delivery(ORDER_TOTAL, DELIVERY_DISTANCE, DELIVERY_TIME, false);
    }

    @Test
    public void testCalculateDeliveryFeeNormalDelivery() {
        double expectedFee = 5.0 + (DELIVERY_DISTANCE * 2.0); // Base fee + distance fee
        assertEquals(expectedFee, delivery.calculateDeliveryFee(), DELTA);
    }

    @Test
    public void testCalculateDeliveryFeeExpressDelivery() {
        delivery.setExpressDelivery(true);
        double expectedFee = 5.0 + (DELIVERY_DISTANCE * 2.0) + 10.0; // Base fee + distance fee + express fee
        assertEquals(expectedFee, delivery.calculateDeliveryFee(), DELTA);
    }

    @Test
    public void testEstimateDeliveryTimeNormalDelivery() {
        double expectedTime = (DELIVERY_DISTANCE / 30.0) * 60; // (distance / speed) * 60 minutes
        assertEquals(expectedTime, delivery.estimateDeliveryTime(), DELTA);
    }

    @Test
    public void testEstimateDeliveryTimeExpressDelivery() {
        delivery.setExpressDelivery(true);
        double expectedTime = ((DELIVERY_DISTANCE / 30.0) * 60) * 0.8; // Normal time * 0.8 for express
        assertEquals(expectedTime, delivery.estimateDeliveryTime(), DELTA);
    }

    @Test
    public void testCalculateDiscount() {
        double discountPercentage = 10.0;
        double expectedDiscount = (ORDER_TOTAL * discountPercentage) / 100;
        assertEquals(expectedDiscount, delivery.calculateDiscount(discountPercentage), DELTA);
    }

    @Test
    public void testCalculateTip() {
        double tipPercentage = 15.0;
        double expectedTip = (ORDER_TOTAL * tipPercentage) / 100;
        assertEquals(expectedTip, delivery.calculateTip(tipPercentage), DELTA);
    }

    @Test
    public void testCalculateTotalAmountToPay() {
        double discountAmount = (ORDER_TOTAL * 10) / 100; // 10% discount
        double tipAmount = (ORDER_TOTAL * 15) / 100; // 15% tip
        double deliveryFee = delivery.calculateDeliveryFee();
        double expectedTotal = ORDER_TOTAL + deliveryFee - discountAmount + tipAmount;
        assertEquals(expectedTotal, delivery.calculateTotalAmountToPay(), DELTA);
    }

    @Test
    public void testSettersAndGetters() {
        // Test orderTotal
        delivery.setOrderTotal(150.0);
        assertEquals(150.0, delivery.getOrderTotal(), DELTA);

        // Test deliveryDistance
        delivery.setDeliveryDistance(7.5);
        assertEquals(7.5, delivery.getDeliveryDistance(), DELTA);

        // Test deliveryTime
        delivery.setDeliveryTime(45.0);
        assertEquals(45.0, delivery.getDeliveryTime(), DELTA);

        // Test expressDelivery
        delivery.setExpressDelivery(true);
        assertTrue(delivery.isExpressDelivery());

        // Test tipPercentage
        delivery.setTipPercentage(20.0);
        assertEquals(20.0, delivery.getTipPercentage(), DELTA);
    }

    @Test
    public void testDisplayDeliveryDetails() {
        // This test mainly verifies that the method runs without throwing exceptions
        delivery.displayDeliveryDetails();
    }

    @Test
    public void testZeroOrderTotal() {
        delivery.setOrderTotal(0.0);
        assertEquals(0.0, delivery.calculateDiscount(10), DELTA);
        assertEquals(0.0, delivery.calculateTip(15), DELTA);
    }

    @Test
    public void testZeroDeliveryDistance() {
        delivery.setDeliveryDistance(0.0);
        assertEquals(5.0, delivery.calculateDeliveryFee(), DELTA); // Should only include base fee
        assertEquals(0.0, delivery.estimateDeliveryTime(), DELTA);
    }
}