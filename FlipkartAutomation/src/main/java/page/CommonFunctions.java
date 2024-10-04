package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import utils.Locators;

import java.util.List;

public class CommonFunctions {

    public void searchProduct(WebDriver driver, String searchItem) {
    	driver.findElement(By.xpath(Locators.CLICK_SEARCH_FIELD)).click();
        WebElement searchBox = driver.findElement(By.xpath(Locators.SEARCH_BOX));
        searchBox.sendKeys(searchItem);
        driver.findElement(By.xpath(Locators.SEARCH_BUTTON)).click();
    }

    public void sortByLowToHigh(WebDriver driver) throws InterruptedException {
        driver.findElement(By.xpath(Locators.SORT_PRICE_LOW_TO_HIGH)).click();
        Thread.sleep(2000);  // Wait for sorting to apply
    }

    public void validatePricesOnPage(WebDriver driver) {
        List<WebElement> priceElements = driver.findElements(By.cssSelector(Locators.PRICE_ELEMENT));
        double prevPrice = 0.0;

        // Validate prices
        for (WebElement priceElement : priceElements) {
            double currentPrice = Double.parseDouble(priceElement.getText().replace("₹", "").replace(",", "").trim());
            System.out.println("Current Price: ₹" + currentPrice);
            Assert.assertTrue(currentPrice >= prevPrice, "Prices are not sorted correctly");
            prevPrice = currentPrice;
        }
    }

    public void clickNextButton(WebDriver driver) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 4500);"); // Adjust scroll distance
        Thread.sleep(500);  // Allow the page to scroll
        WebElement nextButton = driver.findElement(By.xpath(Locators.NEXT_BUTTON));
        nextButton.click();
        Thread.sleep(2000);  // Wait for next page to load
    }
}

