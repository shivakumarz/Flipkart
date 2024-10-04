package base;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Locators;

public class GlobalActions {
    WebDriver driver;

    public WebDriver setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        return driver;
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void navigateToURL(WebDriver driver) {
        driver.get("https://www.flipkart.com");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        try {
            // Wait for the login button to be invisible
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("(//a[@class='_1jKL3b'])[1]")));
        } catch (Exception e) {
            System.out.println("Login button still visible: " + e.getMessage());
        }    }

}
