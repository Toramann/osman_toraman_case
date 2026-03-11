package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {
	private WebDriver driver;
	private WebDriverWait wait;


	private By logo = By.xpath("//label[@for='email']//p[contains(text(),'Unlock your peak potential')]");
	private By navigationMenu = By.xpath("//a[@class='btn btn-light w-mobile-full h-40']");
	private By cookieAcceptBtn = By.id("wt-cli-accept-all-btn");

	public HomePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}

	public void acceptCookies() {
		try {
			WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(cookieAcceptBtn));
			btn.click();
		} catch (Exception e) {
			System.out.println("Cookie banner not found or already accepted.");
		}
	}

	public boolean isHomePageOpened() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(logo)).isDisplayed();
	}

	public boolean areMainBlocksLoaded() {
		return driver.findElement(navigationMenu).isDisplayed() &&
				driver.findElement(By.tagName("body")).isDisplayed();
	}
}