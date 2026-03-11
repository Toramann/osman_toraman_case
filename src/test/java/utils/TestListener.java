package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.BaseTest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = BaseTest.getDriver();

        if (driver != null) {
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss").format(new Date());
            String screenshotName = result.getName() + "_" + timeStamp + ".png";
            String directoryPath = System.getProperty("user.dir") + "/screenshots/";

            File directory = new File(directoryPath);
            if (!directory.exists()) directory.mkdirs();

            try {
                File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(srcFile, new File(directoryPath + screenshotName));
            } catch (IOException e) {
                System.out.println("Screenshot capture failed: " + e.getMessage());
            }
        }
    }
}