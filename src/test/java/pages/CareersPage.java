package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CareersPage {
    private WebDriver driver;
    private WebDriverWait wait;


    private By roles = By.cssSelector(".inso-btn.inso-btn-m.inso-btn-primary.inso-rounded");
    private By seeAllTeams = By.cssSelector(".inso-btn.see-more");

    public CareersPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void clickExploreAndSeeTeams() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(roles)).click();
        wait.until(ExpectedConditions.elementToBeClickable(seeAllTeams)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(roles));
    }
}