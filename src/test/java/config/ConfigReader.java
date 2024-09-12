package config;

import org.aeonbits.owner.ConfigFactory;

/**
 * Класс для читалки файлов .properties
 */
public class ConfigReader {

    /**
     * Читалка для emulator.properties
     */
    public static final EmulatorConfig emulatorConfig = ConfigFactory.create(EmulatorConfig.class, System.getProperties());
    /**
     * Читалка для test.properties
     */
    public static final TestConfig testConfig = ConfigFactory.create(TestConfig.class, System.getProperties());

    /**
     * Читалка для browserstackConfig
     */
    public static final BrowserStackConfig browserstackConfig = ConfigFactory.create(BrowserStackConfig.class, System.getProperties());

    /**
     * Читалка для selenoidConfig
     */
    public static final SelenoidConfig selenoidConfig = ConfigFactory.create(SelenoidConfig.class, System.getProperties());

}
