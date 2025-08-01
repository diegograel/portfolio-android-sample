import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;


public class Functions extends TestCases {

    static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    public static void startAppAsGuest() {
        Assert.assertTrue(driver.findElement(Locators.WELCOME_TITLE).isDisplayed());
        Assert.assertTrue(driver.findElement(Locators.CONTINUE_GUEST_BTN).isDisplayed());
        driver.findElement(Locators.CONTINUE_GUEST_BTN).click();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.SHARE_LOCATION_TITLE)).isDisplayed());
        driver.findElement(Locators.CONTINUE_BTN).click();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.SHARE_LOCATION_WHEN_USING_APP)).isDisplayed());
        driver.findElement(Locators.SHARE_LOCATION_WHEN_USING_APP).click();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.CONTINUE_BTN)).isDisplayed());
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.STAY_IN_LOOP_TITLE)).isDisplayed());
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.CONTINUE_BTN)).click();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.ALLOW_NOTIFICATION_BTN)).isDisplayed());
        driver.findElement(Locators.ALLOW_NOTIFICATION_BTN).click();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.BEST_BUY_LOGO)).isDisplayed());
    }

    public static void itemSearch(){
        driver.findElement(Locators.SEARCH_FIELD).click();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.SEARCH_FIELD_EDIT)).isDisplayed());
        driver.findElement(Locators.SEARCH_FIELD_EDIT).sendKeys(Locators.ITEM_SEARCH_TEXT);
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.SEARCH_RESULT_TEXT)).isDisplayed());
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.SEARCH_RESULT_OPTION)).isDisplayed());
        driver.findElement(Locators.SEARCH_RESULT_OPTION).click();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.RESULT_COUNT)).isDisplayed());
//        for (int i = 0; i < 3; i++) { // Swipe 3 times
//            scrollFunction.swipeUp(driver);
//        }
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.CHOSEN_ITEM)).isDisplayed());
        driver.findElement(Locators.CHOSEN_ITEM).click();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.ITEM_TITLE)).isDisplayed());
        WebElement item_title = driver.findElement(Locators.ITEM_TITLE);
        String actualTitle = item_title.getText();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.ITEM_TITLE)).isDisplayed());
        Assert.assertEquals(actualTitle, Locators.CHOSEN_ITEM_NAME);
    }

    public static void addItemToCart(){
        scrollFunction.swipeUp(driver);
        driver.findElement(Locators.ADD_TO_CART_BTN).click();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.CART_CONFIRMATION)).isDisplayed());
        driver.findElement(Locators.VIEW_CART_BTN).click();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.PRIVACY_POPUP)).isDisplayed());
        driver.findElement(Locators.PRIVACY_POPUP_CLOSE_BTN).click();
        Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOfElementLocated(Locators.PRIVACY_POPUP)));
        scrollFunction.swipeUp(driver);
        WebElement item_quantity_base = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.ITEM_QUANTITY_ONE));
        String actualQuantity = item_quantity_base.getText();
        Assert.assertEquals(actualQuantity, Locators.QUANTITY_ONE);
    }

    public static void changeItemQuantity(){
        driver.findElement(Locators.INCREASE_ITEM_QTY).click();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.ITEM_QUANTITY_TWO)).isDisplayed());
        WebElement item_quantity = driver.findElement(Locators.ITEM_QUANTITY_TWO);
        String item_quantity_add = item_quantity.getText();
        Assert.assertEquals(item_quantity_add, Locators.QUANTITY_TWO);
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.DECREASE_ITEM_QTY)).isEnabled());
        driver.findElement(Locators.DECREASE_ITEM_QTY).click();
        //wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.DECREASE_ITEM_QTY)).click();
        //Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.ITEM_QUANTITY_ONE)).isDisplayed());
        WebElement item_quantity_remove = driver.findElement(Locators.ITEM_QUANTITY_ONE);
        String oldQuantity = item_quantity_remove.getText();
        Assert.assertEquals(oldQuantity, Locators.QUANTITY_ONE);
    }

    public static void removeItemFromCart(){
        driver.findElement(Locators.REMOVE_ITEM_BUTTON).click();
        scrollFunction.swipeDown(driver);
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.YOUR_CART)).isDisplayed());
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.IS_EMPTY)).isDisplayed());
        driver.findElement(Locators.CLOSE_BTN).click();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.ITEM_TITLE)).isDisplayed());
    }

    public static void finishTest(){
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.err.println("Driver quit failed: " + e.getMessage());
            }
        }
    }
}