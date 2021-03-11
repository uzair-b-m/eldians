package test;

import jdk.jfr.StackTrace;
import main.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

// source for how to test print statements
//https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println

public class AbstractUserTest {
    AdminUser adminUser1;
    BuyUser buyUser1;
    SellUser sellUser1;
    FullStandardUser fullStandardUser1;
    BuyUser refundUser1;
    SellUser refundUser2;
    BuyUser refundUser3;
    SellUser refundUser4;
    Game monopoly;
    Marketplace market;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        adminUser1 = new AdminUser("diego", 0.00f);
        buyUser1 = new BuyUser("dora", 34.08f);
        sellUser1 = new SellUser("boots", 34.13f);
        fullStandardUser1 = new FullStandardUser("swiper", 32.40f);
        monopoly = new Game("Monopoly", 23.5f, "sellUser1", 1, 00);
        market = new Marketplace();

        refundUser1 = new BuyUser("Armin", 0.00f);
        refundUser2 = new SellUser("Bertholdt", 0.00f);
        refundUser3 = new BuyUser("Reiner", 13.35f);
        refundUser4 = new SellUser("Ymir", 25.65f);

    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testSellEasy() {
        sellUser1.sell(monopoly, market);
        String result = "Game: Monopoly" + " is now being sold by " + "boots" + " for $" +
                "23.50" + " at a " + "0" +"% discount, will be available for purchase tomorrow.";
        assertEquals(result, outContent.toString());
    }

    @Test
    public void testSellFromBuyUser() {
        buyUser1.sell(monopoly, market);
        String result = "ERROR: \\ < Failed Constraint: "+ "dora" + " cannot sell games.";
        assertEquals(result, outContent.toString());
    }

    /**
     * Tests that an invalid refund returns false, the correct error statement is printed to console and both
     * User's AccountBalance has not been changed.
     */
    @Test
    public void testRefundFromUserWithoutFunds() {
        boolean worked = adminUser1.refund(refundUser1, refundUser2, 15.65f);
        String result = "ERROR: \\ < Failed Constraint: " + "Bertholdt" + " could not make a refund to " + "Armin" +
                " for $" + "15.65" + " due to insufficient funds. > //";
        assertEquals(result, outContent.toString());
        assertEquals(worked, false);
        assertEquals(refundUser1.getAccountBalance(), 0.00f);
        assertEquals(refundUser2.getAccountBalance(), 0.00f);
    }

    /**
     * Tests that a valid refund returns true, and that the correct amount is taken from the sellers AccountBalace
     * and is added to the buyer's AccountBalance.
     */
    @Test
    public void testValidRefund() {
        boolean worked = adminUser1.refund(refundUser3, refundUser4, 12.65f);
        assertEquals(worked, true);
        assertEquals(refundUser3.getAccountBalance(), 26.00f);
        assertEquals(refundUser4.getAccountBalance(), 13.00f);
    }

    /**
     * Tests that a non admin user cannot issue a refund.
     */
    /**
    @Test
    public void testNonAdminRefund() {
        boolean worked = refundUser1.
    }
    */





}

