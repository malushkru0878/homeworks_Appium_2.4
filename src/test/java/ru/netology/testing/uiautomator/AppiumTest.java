package ru.netology.testing.uiautomator;

import io.appium.java_client.AppiumDriver;
import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;


public class AppiumTest {

    private AppiumDriver driver;
    private final String textToSet = "Netology";
    private final String textToSet2 = " ";


    @BeforeEach
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appium:automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appium:deviceName", "name");
        desiredCapabilities.setCapability("appium:appPackage", "ru.netology.testing.uiautomator");
        desiredCapabilities.setCapability("appium:appActivity", "ru.netology.testing.uiautomator.MainActivity");
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

        URL remoteUrl = new URL("http://127.0.0.1:4723/");

        driver = new AppiumDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void testEmptyText() {
        PageLocator pageLocator = new PageLocator(driver);
        pageLocator.userInput.sendKeys(textToSet);
        pageLocator.buttonChange.click();
        pageLocator.textToBeChanged.isDisplayed();
        pageLocator.userInput.sendKeys(textToSet2);
        pageLocator.buttonChange.click();
        pageLocator.textToBeChanged.isDisplayed();
        Assertions.assertEquals(textToSet, pageLocator.textToBeChanged.getText());
    }

    @Test
    public void testNewActivity() {
        PageLocator pageLocator = new PageLocator(driver);
        pageLocator.userInput.sendKeys(textToSet);
        pageLocator.buttonActivity.click();
        pageLocator.text.isDisplayed();
        Assertions.assertEquals(textToSet, pageLocator.text.getText());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
