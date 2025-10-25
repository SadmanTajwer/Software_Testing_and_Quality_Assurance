package com.mycompany.restaurant_management_system;

import java.util.Date;

public class FeedBack {
    private String feedbackId;
    private String customerName;
    private String comments;
    private int rating;  
    private Date feedbackDate;
    private String status; 

    public FeedBack(String feedbackId, String customerName, String comments, int rating) {
        this.feedbackId = feedbackId;
        this.customerName = customerName;
        this.comments = comments;
        this.rating = rating;
        this.feedbackDate = new Date();  
        this.status = "Pending"; 
    }

    public void submitFeedback() {
        System.out.println("Feedback submitted by " + customerName + " with rating: " + rating + "/5.");
    }

 
    public void viewFeedback() {
        System.out.println("Feedback ID: " + feedbackId);
        System.out.println("Customer: " + customerName);
        System.out.println("Comments: " + comments);
        System.out.println("Rating: " + rating + "/5");
        System.out.println("Feedback Date: " + feedbackDate);
        System.out.println("Status: " + status);
    }

    public void generateFeedbackReport() {
        System.out.println("Generating feedback report...");
        System.out.println("Feedback Report Summary:");
        System.out.println("Average Rating: " + calculateAverageRating());
        System.out.println("Total Feedbacks: " + 1);  // Simplified example
        System.out.println("Positive Feedbacks: " + calculatePositiveFeedbacks());
        System.out.println("Feedback Trend: " + analyzeFeedbackTrend());
    }

    public void analyzeSentiment() {
        if (rating >= 4) {
            System.out.println("Sentiment: Positive");
        } else if (rating == 3) {
            System.out.println("Sentiment: Neutral");
        } else {
            System.out.println("Sentiment: Negative");
        }
    }

    public void resolveIssue() {
        this.status = "Resolved";
        System.out.println("Feedback " + feedbackId + " has been resolved.");
    }

    public void viewCustomerSuggestions() {
        if (comments != null && !comments.isEmpty()) {
            System.out.println("Customer Suggestions: " + comments);
        } else {
            System.out.println("No suggestions provided.");
        }
    }

    private double calculateAverageRating() {
        return rating;  
    }

    private int calculatePositiveFeedbacks() {
        return rating >= 4 ? 1 : 0;
    }


    private String analyzeFeedbackTrend() {
        if (rating == 5) {
            return "Excellent";
        } else if (rating == 4) {
            return "Good";
        } else if (rating == 3) {
            return "Average";
        } else if (rating == 2) {
            return "Below Average";
        } else {
            return "Poor";
        }
    }

   
    public String getFeedbackId() {
        return feedbackId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getComments() {
        return comments;
    }

    public int getRating() {
        return rating;
    }

    public Date getFeedbackDate() {
        return feedbackDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
