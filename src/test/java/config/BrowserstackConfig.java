package config;

import org.aeonbits.owner.Config;
@Config.Sources({
        "classpath:browserstack.properties"
})
public interface BrowserstackConfig extends Config {
    @Key("login")
    String getLogin();

    @Key("password")
    String getPassword();

    @Key("url")
    String getUrl();

    @Key("videoUrl")
    String getVideoUrl();
}
