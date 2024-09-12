package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties", //чтение env
        "file:src/test/resources/configs/browserstack.properties", //чтение из файла
})
public interface BrowserStackConfig extends Config {
    @Key("deviceName")
    String deviceName();

    @Key("platformName")
    String platformName();

    @Key("platformVersion")
    String platformVersion();

    @Key("appUrl")
    String appUrl();

    @Key("browserstackUser")
    String browserstackUser();

    @Key("browserstackKey")
    String browserstackKey();

    @Key("projectName")
    String projectName();

    @Key("buildName")
    String buildName();

    @Key("appPackage")
    String appPackage();

    @Key("appActivity")
    String appActivity();
}