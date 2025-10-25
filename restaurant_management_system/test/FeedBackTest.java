package com.mycompany.restaurant_management_system;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Date;

public class FeedBackTest {
    private FeedBack feedback;
    private static final String FEEDBACK_ID = "FB001";
    private static final String CUSTOMER_NAME = "John Doe";
    private static final String COMMENTS = "Great service!";
    private static final int RATING = 4;

    @Before
    public void setUp() {
        feedback = new FeedBack(FEEDBACK_ID, CUSTOMER_NAME, COMMENTS, RATING);
    }

    @Test
    public void testFeedbackInitialization() {
        assertEquals(FEEDBACK_ID, feedback.getFeedbackId());
        assertEquals(CUSTOMER_NAME, feedback.getCustomerName());
        assertEquals(COMMENTS, feedback.getComments());
        assertEquals(RATING, feedback.getRating());
        assertNotNull(feedback.getFeedbackDate());
        assertEquals("Pending", feedback.getStatus());
    }

    @Test
    public void testSubmitFeedback() {
        // Testing if method executes without throwing exception
        feedback.submitFeedback();
    }

    @Test
    public void testViewFeedback() {
        // Testing if method executes without throwing exception
        feedback.viewFeedback();
    }

    @Test
    public void testGenerateFeedbackReport() {
        // Testing if method executes without throwing exception
        feedback.generateFeedbackReport();
    }

    @Test
    public void testAnalyzeSentimentPositive() {
        FeedBack positiveFeedback = new FeedBack("FB002", "Jane", "Excellent!", 5);
        positiveFeedback.analyzeSentiment();
        // Method should execute without exception for rating >= 4
    }

    @Test
    public void testAnalyzeSentimentNeutral() {
        FeedBack neutralFeedback = new FeedBack("FB003", "Bob", "Average", 3);
        neutralFeedback.analyzeSentiment();
        // Method should execute without exception for rating = 3
    }

    @Test
    public void testAnalyzeSentimentNegative() {
        FeedBack negativeFeedback = new FeedBack("FB004", "Alice", "Poor", 2);
        negativeFeedback.analyzeSentiment();
        // Method should execute without exception for rating < 3
    }

    @Test
    public void testResolveIssue() {
        feedback.resolveIssue();
        assertEquals("Resolved", feedback.getStatus());
    }

    @Test
    public void testViewCustomerSuggestionsWithComments() {
        feedback.viewCustomerSuggestions();
        // Should print the comments without throwing exception
    }

    @Test
    public void testViewCustomerSuggestionsWithoutComments() {
        FeedBack emptyFeedback = new FeedBack("FB005", "Tom", "", 4);
        emptyFeedback.viewCustomerSuggestions();
        // Should handle empty comments without throwing exception
    }

    @Test
    public void testCalculateAverageRating() {
        // Using reflection to test private method
        try {
            java.lang.reflect.Method method = FeedBack.class.getDeclaredMethod("calculateAverageRating");
            method.setAccessible(true);
            double result = (double) method.invoke(feedback);
            assertEquals(RATING, result, 0.001);
        } catch (Exception e) {
            fail("Failed to test calculateAverageRating: " + e.getMessage());
        }
    }

    @Test
    public void testCalculatePositiveFeedbacks() {
        // Using reflection to test private method
        try {
            java.lang.reflect.Method method = FeedBack.class.getDeclaredMethod("calculatePositiveFeedbacks");
            method.setAccessible(true);
            
            // Test with positive rating (>= 4)
            FeedBack positiveFeedback = new FeedBack("FB006", "Mary", "Good", 4);
            int positiveResult = (int) method.invoke(positiveFeedback);
            assertEquals(1, positiveResult);

            // Test with negative rating (< 4)
            FeedBack negativeFeedback = new FeedBack("FB007", "Peter", "Bad", 2);
            int negativeResult = (int) method.invoke(negativeFeedback);
            assertEquals(0, negativeResult);
        } catch (Exception e) {
            fail("Failed to test calculatePositiveFeedbacks: " + e.getMessage());
        }
    }

    @Test
    public void testAnalyzeFeedbackTrend() {
        // Using reflection to test private method
        try {
            java.lang.reflect.Method method = FeedBack.class.getDeclaredMethod("analyzeFeedbackTrend");
            method.setAccessible(true);

            // Test all possible trends
            FeedBack feedback5 = new FeedBack("FB008", "A", "Excellent", 5);
            assertEquals("Excellent", method.invoke(feedback5));

            FeedBack feedback4 = new FeedBack("FB009", "B", "Good", 4);
            assertEquals("Good", method.invoke(feedback4));

            FeedBack feedback3 = new FeedBack("FB010", "C", "Average", 3);
            assertEquals("Average", method.invoke(feedback3));

            FeedBack feedback2 = new FeedBack("FB011", "D", "Below Average", 2);
            assertEquals("Below Average", method.invoke(feedback2));

            FeedBack feedback1 = new FeedBack("FB012", "E", "Poor", 1);
            assertEquals("Poor", method.invoke(feedback1));
        } catch (Exception e) {
            fail("Failed to test analyzeFeedbackTrend: " + e.getMessage());
        }
    }

    @Test
    public void testSetStatus() {
        feedback.setStatus("In Progress");
        assertEquals("In Progress", feedback.getStatus());
    }

    @Test
    public void testGetFeedbackDate() {
        assertTrue(feedback.getFeedbackDate() instanceof Date);
        assertTrue(feedback.getFeedbackDate().getTime() <= new Date().getTime());
    }
}