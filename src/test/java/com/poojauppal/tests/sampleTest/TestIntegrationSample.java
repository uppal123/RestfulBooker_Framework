package com.poojauppal.tests.sampleTest;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.annotation.Target;

public class TestIntegrationSample {
    // Create A Booking, Create a Token
    // Verify that Get booking -
    // Update the Booking
    // Delete the Booking
    @Description("TC01 - Step 1 Verify that Booking is created")
    @Test (groups = "qa", priority = 1)
    public void test_CreateBooking() {
        Assert.assertTrue(true);
    }

    @Description("Tc02- Step2 Verify that Booking can be Searched")
    @Test(groups = "qa", priority = 2)
    public void test_SearchBooking() {
        Assert.assertTrue(true);
    }

    @Description("Tc03- Step 3 Verify Booking is updating")
    @Test(groups = "qa", priority = 3)
    public void test_UpdateBooking() {
        Assert.assertTrue(true);
    }

    @Description("Tc04- Step4 Verify that Booking can be deleted")
    @Test(groups = "qa", priority = 4)
    public void test_DeleteBooking() {
        Assert.assertTrue(true);
    }


}
