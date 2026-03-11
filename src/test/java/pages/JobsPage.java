package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class JobsPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    private By locationDropdown = By.xpath("//div[normalize-space()='Location']");
    private By jobTitleList = By.className("posting-title");
    private By jobHeader = By.xpath("//h2[normalize-space()='Software Quality Assurance Engineer (Remote)']");
    private By locationTag = By.xpath("//div[contains(@class,'location')]");
    private By dismissBtn = By.xpath("//button[contains(text(),'Dismiss')] | //a[contains(text(),'Dismiss')]");

    public JobsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.js = (JavascriptExecutor) driver;
    }

    public void dismissCookies() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(dismissBtn)).click();
        } catch (Exception ignored) {}
    }

    public void filterByLocation(String location) {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(locationDropdown));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", dropdown);
        js.executeScript("arguments[0].click();", dropdown);

        By locationOption = By.xpath("//a[normalize-space()='" + location + "']");
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(locationOption));
        js.executeScript("arguments[0].click();", option);
    }

    public void clickFirstJobEntry() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(jobTitleList));
        WebElement firstJob = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(@class, 'posting-title')])[1]")));
        js.executeScript("arguments[0].click();", firstJob);
    }

    public String getOpenedJobTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(jobHeader)).getText();
    }

    public String getOpenedJobLocation() {
        return driver.findElement(locationTag).getText();
    }
}