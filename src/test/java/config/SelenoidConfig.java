package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "file:src/test/resources/configs/selenoid.properties",
})
public interface SelenoidConfig extends Config {
    @Key("deviceName")
    String deviceName();

    @Key("platformName")
    String platformName();

    @Key("platformVersion")
    String platformVersion();

    @Key("appPackage")
    String appPackage();

    @Key("appActivity")
    String appActivity();

    @Key("selenoidUrl")
    String selenoidUrl();

    @Key("videoEnabled")
    @DefaultValue("true")
    boolean videoEnabled();

    @Key("enableVNC")
    @DefaultValue("true")
    boolean enableVNC();

    @Key("enableLog")
    @DefaultValue("true")
    boolean enableLog();

    @Key("apk")
    String app();
}