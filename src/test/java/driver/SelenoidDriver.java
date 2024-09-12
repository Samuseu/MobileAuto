package driver;

import com.codeborne.selenide.WebDriverProvider;
import config.ConfigReader;
import helper.ApkInfoHelper;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.annotation.Nonnull;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SelenoidDriver implements WebDriverProvider {
    protected static AndroidDriver driver;

    private static String APP_PACKAGE = ConfigReader.selenoidConfig.appPackage();
    private static String APP_ACTIVITY = ConfigReader.selenoidConfig.appActivity();
    private static final String APP = ConfigReader.selenoidConfig.app();
    private static final String URL = ConfigReader.selenoidConfig.selenoidUrl();

    private String getAbsolutePath(String filePath) {
        File file = new File(filePath);
        assertTrue(file.exists(), filePath + " not found");
        return file.getAbsolutePath();
    }

    private void initPackageAndActivity() {
        ApkInfoHelper helper = new ApkInfoHelper();
        APP_PACKAGE = APP_PACKAGE.isEmpty() ? helper.getAppPackageFromApk() : APP_PACKAGE;
        APP_ACTIVITY = APP_ACTIVITY.isEmpty() ? helper.getAppMainActivity() : APP_ACTIVITY;
    }

    public static URL getUrl() {
        try {
            return new URL(URL);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Nonnull
    @Override
    public WebDriver createDriver(DesiredCapabilities desiredCapabilities) {
        initPackageAndActivity();
        desiredCapabilities.setCapability("deviceName", ConfigReader.selenoidConfig.deviceName());
        desiredCapabilities.setCapability("platformName", ConfigReader.selenoidConfig.platformName());
        desiredCapabilities.setCapability("platformVersion", ConfigReader.selenoidConfig.platformVersion());
        desiredCapabilities.setCapability("appPackage", APP_PACKAGE);
        desiredCapabilities.setCapability("appActivity", APP_ACTIVITY);
        desiredCapabilities.setCapability("enableVNC", ConfigReader.selenoidConfig.enableVNC());
        desiredCapabilities.setCapability("enableVideo", ConfigReader.selenoidConfig.videoEnabled());
        desiredCapabilities.setCapability("enableLog", ConfigReader.selenoidConfig.enableLog());
        desiredCapabilities.setCapability("apk", getAbsolutePath(APP));

        driver = new AndroidDriver(getUrl(), desiredCapabilities);
        return driver;
    }
}
