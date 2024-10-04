import base.GlobalActions;
import page.CommonFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.Locators;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ValidateAddCart {

    WebDriver driver;
    GlobalActions globalActions = new GlobalActions();
    CommonFunctions commonFunctions = new CommonFunctions();

    @BeforeClass
    public void setUp() {
        driver = globalActions.setUp();
    }

    @AfterClass
    public void tearDown() {
        globalActions.tearDown();
    }
    
    @DataProvider(name = "productData")
    public Object[][] productData() {
        return new Object[][] {
            {"shoes"}, 
        };
    }

    @Test(dataProvider = "productData")
    public void validateAddToCart(String searchItem) throws InterruptedException {
        globalActions.navigateToURL(driver);

        commonFunctions.searchProduct(driver, searchItem);
        commonFunctions.sortByLowToHigh(driver);

        double expectedTotal = 0.0; 
        int[] productIndices = {2, 3};

        for (int index : productIndices) {
            String productPriceText = driver.findElement(By.xpath(Locators.getProductPrice(index))).getText();
            double productPrice = Double.parseDouble(productPriceText.replace("₹", "").replace(",", "").trim());
            expectedTotal += productPrice; 
            driver.findElement(By.xpath(Locators.getProductPrice(index))).click();

            Set<String> allWindows = driver.getWindowHandles();
            List<String> tabs = new ArrayList<>(allWindows); 
            String originalWindow = driver.getWindowHandle();
            driver.switchTo().window(tabs.get(1));
            driver.findElement(By.xpath(Locators.ADD_TO_CART_BUTTON)).click();

            if (index == 2) {
            	driver.close();
            	driver.switchTo().window(originalWindow);
            }
        }

       List<WebElement> cartProducts = driver.findElements(By.cssSelector(Locators.CART_PRODUCTS));

        // Validate prices of products in cart
        for (int i = 0; i < cartProducts.size(); i++) {
            String cartProductPrice = cartProducts.get(i).findElement(By.cssSelector(Locators.PRODUCT_PRICE)).getText();
            double cartProductPriceValue = Double.parseDouble(cartProductPrice.replace("₹", "").replace(",", "").trim());
            Assert.assertEquals(cartProductPriceValue, expectedTotal, "Product price mismatch for product " + (i + 1));

        }
        
    }
}
