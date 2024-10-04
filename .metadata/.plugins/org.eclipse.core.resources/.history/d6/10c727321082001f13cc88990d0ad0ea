
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import base.GlobalActions;
import page.CommonFunctions;

public class ValidateSortTest {
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

    @Test(dataProvider = "productData")
    public void validateSortPriceLowToHigh(String searchItem, int pageLimit) throws InterruptedException {
        globalActions.navigateToURL(driver);

        commonFunctions.searchProduct(driver, searchItem);

        commonFunctions.sortByLowToHigh(driver);

        // Validate prices on the first page
        commonFunctions.validatePricesOnPage(driver);

        // Scroll and click on the Next button to go to the next page
        commonFunctions.clickNextButton(driver);
        // Validate prices on the second page
        commonFunctions.validatePricesOnPage(driver);
    }

    @DataProvider(name = "productData")
    public Object[][] getProductData() {
        return new Object[][] {{"shoes", 2}};
    }
}

