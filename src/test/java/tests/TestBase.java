package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import config.DriverConfig;
import drivers.BrowserStackMobileDriver;
import drivers.EmulationMobileDriver;
import drivers.RealMobileDriver;
import drivers.SelenoidMobileDriver;
import helpers.Attach;
import helpers.WikipediaApp;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static helpers.Attach.sessionId;
import static io.qameta.allure.Allure.step;

public class TestBase {

    public static DriverConfig driverConfig = ConfigFactory.create(DriverConfig.class);

    @BeforeAll
    public static void beforeAll() {
        if (driverConfig.getDriverName().equals("browserstack")) {
            Configuration.browser = BrowserStackMobileDriver.class.getName();
        } else if (driverConfig.getDriverName().equals("emulation")) {
            Configuration.browser = EmulationMobileDriver.class.getName();
        } else if (driverConfig.getDriverName().equals("real")) {
            Configuration.browser = RealMobileDriver.class.getName();
        } else if (driverConfig.getDriverName().equals("selenoid")) {
            Configuration.browser = SelenoidMobileDriver.class.getName();
        } else {
            throw new RuntimeException("No such driver");
        }

        Configuration.browserSize = null;
    }

    @BeforeEach
    void beforeEach() {
        addListener("AllureSelenide", new AllureSelenide());

        open();
    }

    @AfterEach
    void afterEach() {
        if (driverConfig.getDriverName().equals("browserstack")) {
            String sessionId = sessionId();

            Attach.screenshotAs("Last screenshot");
            Attach.pageSource();

            step("Close driver", Selenide::closeWebDriver);

            Attach.video(sessionId);
        } else {
            Attach.screenshotAs("Last screenshot");
            Attach.pageSource();

            step("Close driver", Selenide::closeWebDriver);
        }
    }
}
