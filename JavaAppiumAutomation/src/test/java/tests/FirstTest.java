package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {
    private AppiumDriver driver;

    @BeforeEach
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "10.0"); //версия андроида
        capabilities.setCapability("automationName", "Appium"); //через что будем автоматизировать
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", "org.wikipedia.main.MainActivity");
        capabilities.setCapability("app", "C:\\Studying\\Courses\\Mobile\\ProjectsMobile\\MobileAtomation\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @AfterEach
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void firstTest() {
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']"),5);
        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"), 5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Java", 5);
        WebElement javaSearch = waitForElement(By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_description\" and @text=\"Object-oriented programming language\"]"));
        javaSearch.click();
    }

    @Test
    public void secondTest() {
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']"), 5);
        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"), 5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Java", 5);
        waitForElementAndClick(By.id("search_close_btn"),5);
        waitForElementNotPresent(By.id("search_close_btn"), 4);
    }

    private WebElement waitForElement(By by, long timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElement(By by) {
        return waitForElement(by, 5);
    }

    private WebElement waitForElementAndClick(By by, long timeout) {
        WebElement element = waitForElement(by, timeout);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, long timeout) {
        WebElement element = waitForElement(by, timeout);
        element.sendKeys(value);
        return element;
    }


    private boolean waitForElementNotPresent(By by, long timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }
}


