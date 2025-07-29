import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.*;
import java.net.MalformedURLException;
import java.net.URL;

public class TestCases {

    static AppiumDriver driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setCapability("platformName", "Android");
        options.setCapability("deviceName", "Galaxy S20");
        options.setCapability("automationName", "UiAutomator2");
        options.setCapability("appPackage", "com.coppi.bestbuy");
        options.setCapability("appActivity", "com.bestbuy.android.activity.HomeScreenActivity");
        options.setCapability("noReset", false);

        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), options);
    }

    @Test
    public void appStart() {
        Functions.startAppAsGuest();
    }

    @Test(dependsOnMethods = "appStart")
    public void searchItem() {
        Functions.itemSearch();
    }

    @Test(dependsOnMethods = "searchItem")
    public void addingItemToCart() {
        Functions.addItemToCart();
    }

    @Test(dependsOnMethods = "addingItemToCart")
    public void changingItemQuantity() {
        Functions.changeItemQuantity();
    }

    @Test(dependsOnMethods = "changingItemQuantity")
    public void removingItemFromCart() {
        Functions.removeItemFromCart();
    }

    @AfterClass
    public void tearDown() {
        Functions.finishTest();
    }
}
