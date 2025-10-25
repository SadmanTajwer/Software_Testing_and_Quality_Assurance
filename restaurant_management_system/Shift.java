package com.mycompany.restaurant_management_system;

import java.util.Date;

public class Shift {
    private String shiftId;
    private Date startTime;
    private Date endTime;
    private String shiftType;
    private Manager managerInCharge;
    private boolean isShiftCompleted;

    public Shift(String shiftId, Date startTime, Date endTime, String shiftType, Manager managerInCharge) {
        this.shiftId = shiftId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.shiftType = shiftType;
        this.managerInCharge = managerInCharge;
        this.isShiftCompleted = false;
    }

    public void startShift() {
        if (!isShiftCompleted) {
            System.out.println("Shift " + shiftId + " started at " + startTime);
        } else {
            System.out.println("Shift " + shiftId + " has already been completed.");
        }
    }

    public void endShift() {
        if (!isShiftCompleted) {
            isShiftCompleted = true;
            System.out.println("Shift " + shiftId + " ended at " + new Date());
        } else {
            System.out.println("Shift " + shiftId + " has already been completed.");
        }
    }

    public void assignManager(Manager newManager) {
        this.managerInCharge = newManager;
        System.out.println("Shift " + shiftId + " is now managed by " + newManager.getName());
    }

    public boolean isShiftCompleted() {
        return isShiftCompleted;
    }

    public void updateShiftTimings(Date newStartTime, Date newEndTime) {
        this.startTime = newStartTime;
        this.endTime = newEndTime;
        System.out.println("Shift " + shiftId + " timings updated.");
    }

    public void getShiftDetails() {
        System.out.println("Shift ID: " + shiftId);
        System.out.println("Shift Type: " + shiftType);
        System.out.println("Start Time: " + startTime);
        System.out.println("End Time: " + endTime);
        System.out.println("Shift Completed: " + (isShiftCompleted ? "Yes" : "No"));
    }

    public double calculateShiftDuration() {
        long durationInMillis = endTime.getTime() - startTime.getTime();
        return durationInMillis / (1000.0 * 60 * 60); // Converts milliseconds to hours
    }

    public double calculateOvertimeHours(double regularShiftDuration) {
        double shiftDuration = calculateShiftDuration();
        if (shiftDuration > regularShiftDuration) {
            return shiftDuration - regularShiftDuration;
        }
        return 0.0;
    }

    public double calculateTotalEarnings(double hourlyWage) {
        double shiftDuration = calculateShiftDuration();
        double overtimeHours = calculateOvertimeHours(8); // Assuming 8 hours is the regular shift
        double overtimeWage = overtimeHours * hourlyWage * 1.5; // Overtime paid at 1.5 times the regular rate
        return (shiftDuration * hourlyWage) + overtimeWage;
    }

    public double calculateShiftProductivity(double totalTasksCompleted, double shiftDuration) {
        if (shiftDuration == 0) return 0;
        return totalTasksCompleted / shiftDuration; // Tasks per hour
    }

    public double calculateManagerPerformance(double revenueGenerated, double shiftDuration) {
        if (shiftDuration == 0) return 0;
        return revenueGenerated / shiftDuration; // Revenue per hour
    }

    public String getShiftType() {
        return shiftType;
    }

    public Manager getManagerInCharge() {
        return managerInCharge;
    }

    public boolean getIsShiftCompleted() {
        return isShiftCompleted;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
