package com.mycompany.restaurant_management_system;

public class Delivery {

    private double orderTotal; 
    private double deliveryDistance; 
    private double deliveryTime; 
    private double deliveryFee; 
    private double discount; 
    private double tipPercentage; 
    private boolean isExpressDelivery; 

    public Delivery(double orderTotal, double deliveryDistance, double deliveryTime, boolean isExpressDelivery) {
        this.orderTotal = orderTotal;
        this.deliveryDistance = deliveryDistance;
        this.deliveryTime = deliveryTime;
        this.isExpressDelivery = isExpressDelivery;
    }

    public double calculateDeliveryFee() {
        double baseFee = 5.0; 
        double distanceFee = deliveryDistance * 2.0; 
        if (isExpressDelivery) {
            distanceFee += 10.0; 
        }
        this.deliveryFee = baseFee + distanceFee;
        return this.deliveryFee;
    }


    public double estimateDeliveryTime() {
      
        double averageSpeed = 30.0; 
        double time = (deliveryDistance / averageSpeed) * 60; 
        if (isExpressDelivery) {
            time = time * 0.8;
        }
        return time;
    }

 
    public double calculateDiscount(double discountPercentage) {
        this.discount = (orderTotal * discountPercentage) / 100;
        return this.discount;
    }

   
    public double calculateTip(double tipPercentage) {
        this.setTipPercentage(tipPercentage);
        return (orderTotal * tipPercentage) / 100;
    }


    public double calculateTotalAmountToPay() {
        double discountAmount = calculateDiscount(10); 
        double tipAmount = calculateTip(15); 
        double total = orderTotal + calculateDeliveryFee() - discountAmount + tipAmount;
        return total;
    }

 
    public void displayDeliveryDetails() {
        System.out.println("Order Total: $" + orderTotal);
        System.out.println("Delivery Distance: " + deliveryDistance + " km");
        System.out.println("Estimated Delivery Time: " + estimateDeliveryTime() + " minutes");
        System.out.println("Delivery Fee: $" + calculateDeliveryFee());
        System.out.println("Discount: -$" + calculateDiscount(10));
        System.out.println("Tip: $" + calculateTip(15));
        System.out.println("Total to Pay: $" + calculateTotalAmountToPay());
    }

 
    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public double getDeliveryDistance() {
        return deliveryDistance;
    }

    public void setDeliveryDistance(double deliveryDistance) {
        this.deliveryDistance = deliveryDistance;
    }

    public double getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(double deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public boolean isExpressDelivery() {
        return isExpressDelivery;
    }

    public void setExpressDelivery(boolean expressDelivery) {
        isExpressDelivery = expressDelivery;
    }

	public double getTipPercentage() {
		return tipPercentage;
	}

	public void setTipPercentage(double tipPercentage) {
		this.tipPercentage = tipPercentage;
	}
}
