package config;


import org.aeonbits.owner.Config;


@Config.Sources({
        "classpath:properties/local.properties",
        "classpath:properties/${env}.properties"
})

public interface WebDriverConfig extends Config {

    @Key("browser")
    String getBrowser();

    @Key("baseUrl")
    String getBaseUrl();

    @Key("browserVersion")
    String getBrowserVersion();

    @Key("browserSize")
    String getBrowserSize();

    @Key("isRemote")
    Boolean IsRemote();

    @Key("remote")
    String getRemote();

}
