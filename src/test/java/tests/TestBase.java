package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import drivers.BrowserStackMobileDriver;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static helpers.Attach.sessionId;
import static io.qameta.allure.Allure.step;

public class TestBase {
    @BeforeAll
    public static void beforeAll() {
        Configuration.browser = BrowserStackMobileDriver.class.getName();
        Configuration.browserSize = null;
    }

    @BeforeEach
    void beforeEach() {
        addListener("AllureSelenide", new AllureSelenide());

        open();
    }

    @AfterEach
    void afterEach() {
        String sessionId = sessionId();

        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();

        step("Close driver", Selenide::closeWebDriver);

        Attach.video(sessionId);
    }
}
