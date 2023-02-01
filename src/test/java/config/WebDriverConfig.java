package config;


import org.aeonbits.owner.Config;


@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "classpath:./resources/local.properties",
        "classpath:./resources/remote.properties"
})

public interface WebDriverConfig extends Config {

    @Key("browser")
    String getBrowser();

    @Key("base_Url")
    String getBaseUrl();

    @Key("browser_version")
    String getBrowserVersion();

    @Key("browser_size")
    String getBrowserSize();

    @Key("ifRemote")
    Boolean IfRemote();

    @Key("remote")
    String getRemote();

}
