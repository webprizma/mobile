package helpers;

import config.BrowserstackConfig;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;

public class Browserstack {
    public static BrowserstackConfig browserstackConfig = ConfigFactory.create(BrowserstackConfig.class, System.getProperties());
    public static String videoUrl(String sessionId) {
        String url = format(browserstackConfig.getUrl(), sessionId);

        return given()
                .auth().basic(browserstackConfig.getLogin(), browserstackConfig.getPassword())
                .log().all()
                .when()
                .get(url)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .path(browserstackConfig.getVideoUrl());
    }
}
