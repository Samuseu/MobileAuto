package driver;

import com.codeborne.selenide.WebDriverProvider;
import config.ConfigReader;
import helper.ApkInfoHelper;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Класс для инициализации AndroidDriver для BrowserStack
 */
public class BrowserStackDriver implements WebDriverProvider {
    protected static AndroidDriver driver;

    // Чтение пропертей
    private static final String DEVICE_NAME = ConfigReader.browserstackConfig.deviceName();
    private static final String PLATFORM_NAME = ConfigReader.browserstackConfig.platformName();
    private static final String PLATFORM_VERSION = ConfigReader.browserstackConfig.platformVersion();
    private static String APP_URL = ConfigReader.browserstackConfig.appUrl();
    private static final String BROWSERSTACK_USER = ConfigReader.browserstackConfig.browserstackUser();
    private static final String BROWSERSTACK_KEY = ConfigReader.browserstackConfig.browserstackKey();
    private static final String PROJECT_NAME = ConfigReader.browserstackConfig.projectName();
    private static final String BUILD_NAME = ConfigReader.browserstackConfig.buildName();

    /**
     * Получение URL для подключения к BrowserStack
     * @return URL для подключения
     */
    public static URL getBrowserstackUrl() {
        try {
            return new URL("https://" + BROWSERSTACK_USER + ":" + BROWSERSTACK_KEY + "@hub-cloud.browserstack.com/wd/hub");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Получаем AppPackage и AppActivity из APK, если они не заданы
     */
    private void initPackageAndActivity() {
        System.out.println(APP_URL);
        if (APP_URL.isEmpty()) {
            ApkInfoHelper helper = new ApkInfoHelper();
            APP_URL = helper.getAppUrlFromLocalPath();
        }
    }

    /**
     * Создает Appium сессию AndroidDriver для BrowserStack
     * @param desiredCapabilities настройки для создания сессии
     * @return сессия AndroidDriver
     */
    @Nonnull
    @Override
    public WebDriver createDriver(DesiredCapabilities desiredCapabilities) {
        initPackageAndActivity();

        desiredCapabilities.setCapability("deviceName", DEVICE_NAME);
        desiredCapabilities.setCapability("platformName", PLATFORM_NAME);
        desiredCapabilities.setCapability("platformVersion", PLATFORM_VERSION);
        desiredCapabilities.setCapability("app", APP_URL);

        // BrowserStack specific capabilities
        desiredCapabilities.setCapability("project", PROJECT_NAME);
        desiredCapabilities.setCapability("build", BUILD_NAME);
        desiredCapabilities.setCapability("browserstack.debug", true);
        desiredCapabilities.setCapability("browserstack.networkLogs", true);

        driver = new AndroidDriver<>(getBrowserstackUrl(), desiredCapabilities);
        return driver;
    }
}