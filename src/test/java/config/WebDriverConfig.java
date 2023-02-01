package config;


import org.aeonbits.owner.Config;


@Config.Sources({
        "classpath:./resources/properties/${env}.properties"
})

public interface WebDriverConfig extends Config {

    @Key("browser")
    @DefaultValue("chrome")
    String getBrowser();

    @Key("baseUrl")
    @DefaultValue("https://www.t1-consulting.ru")
    String getBaseUrl();

    @Key("browserVersion")
    @DefaultValue("100.0")
    String getBrowserVersion();

    @Key("browserSize")
    @DefaultValue("1920x1080")
    String getBrowserSize();

    @Key("ifRemote")
    @DefaultValue("false")
    Boolean IfRemote();

    @Key("remote")
    @DefaultValue("https://user1:1234@selenoid.autotests.cloud/wd/hub")
    String getRemote();

}
