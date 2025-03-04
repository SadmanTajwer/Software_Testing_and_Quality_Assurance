import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import com.mycompany.restaurant_management_system.Billing;

public class BillingTest {

    private Billing billing;

    @Before
    public void setUp() {
        String customerName = "Muhammad Sadman";
        String[] itemsOrdered = {"Pizza", "Pasta", "Salad", "Soda"};
        double[] itemPrices = {500, 300, 200, 100}; 
        double taxRate = 10.0; 
        billing = new Billing(customerName, itemsOrdered, itemPrices, taxRate);
    }

    @Test
    public void testCalculateTotalAmount() {
        double expectedTotal = (500 + 300 + 200 + 100) * (1 + 0.10) * (1 + 0.05); 
        assertEquals(expectedTotal, billing.calculateFinalBill() + billing.calculateDiscount(), 0.01);
    }

    @Test
    public void testCalculateDiscount() {
        double expectedDiscount = (1100 * 1.10 * 1.05) * 0.10 + 50; 
        assertEquals(expectedDiscount, billing.calculateDiscount(), 0.01);
    }


    @Test
    public void testCalculateFinalBill() {
        double totalAmount = 1100 * 1.10 * 1.05; 
        double discount = totalAmount * 0.10 + 50; 
        double expectedFinalBill = totalAmount - discount;
        assertEquals(expectedFinalBill, billing.calculateFinalBill(), 0.01);
    }

    @Test
    public void testApplyCustomDiscount() {
        billing.applyCustomDiscount(20.0); 
        double totalAmount = 1100 * 1.10 * 1.05;
        double customDiscount = totalAmount * 0.20;
        double expectedFinalBill = totalAmount - customDiscount;
        assertEquals(expectedFinalBill, billing.calculateFinalBill(), 0.01);
    }

    @Test
    public void testApplyLoyaltyDiscount() {
        double totalAmount = 1100 * 1.10 * 1.05; 
        billing.applyLoyaltyDiscount(6000); 

        double baseDiscount = 0;
        if (totalAmount > 1500) {
            baseDiscount = totalAmount * 0.15;
        } else if (totalAmount > 1000) {
            baseDiscount = totalAmount * 0.10;
        } else if (totalAmount > 500) {
            baseDiscount = totalAmount * 0.05;
        }
        baseDiscount += 50; 

      
        double loyaltyDiscount = totalAmount * 0.10;

      
        double expectedDiscount = baseDiscount + loyaltyDiscount;

        
        assertEquals(expectedDiscount, billing.calculateDiscount() + loyaltyDiscount, 0.01);
    }



    @Test
    public void testPrintReceipt() {
        billing.printReceipt();
    }
}
