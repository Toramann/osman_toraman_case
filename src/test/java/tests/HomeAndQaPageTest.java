package tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.JobsPage;

@Listeners(utils.TestListener.class)
public class HomeAndQaPageTest extends BaseTest {

    @Test(priority = 1, description = "Verify home page and main elements")
    public void verifyHomePageAndBlocks() {
        driver.get("https://useinsider.com/");
        HomePage homePage = new HomePage(driver);

        homePage.acceptCookies();

        Assert.assertTrue(homePage.isHomePageOpened(), "Home page could not be verified.");
        Assert.assertTrue(homePage.areMainBlocksLoaded(), "Navigation or Body blocks are missing.");
    }

    @Test(priority = 2, description = "Verify QA jobs filtering functionality")
    public void filterByLocationAndCheckJob() {
        driver.get("https://jobs.lever.co/insiderone?team=Quality%20Assurance");
        JobsPage jobsPage = new JobsPage(driver);

        jobsPage.dismissCookies();
        jobsPage.filterByLocation("Istanbul, Turkiye");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        jobsPage.clickFirstJobEntry();

        String expectedTitle = "Software Quality Assurance Engineer (Remote)";
        String expectedLocation = "Istanbul, Turkiye";

        Assert.assertEquals(jobsPage.getOpenedJobTitle(), expectedTitle, "Job title does not match.");
        Assert.assertTrue(jobsPage.getOpenedJobLocation().contains(expectedLocation), "Location verification failed.");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}