package tests;

import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@Tag("onboard")
public class OnboardTests extends TestBase {
    @Test
    void addNewLanguageTest() {
        step("Add new language", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/addLangContainer")).click();
            $$(AppiumBy.xpath("//androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout")).last().click();
            $(AppiumBy.xpath("//android.widget.TextView[@text='Deutsch']")).click();
            back();
        });

        step("Check language added", () ->
                $$(AppiumBy.xpath("//androidx.recyclerview.widget.RecyclerView/android.widget.TextView")).last().shouldHave(text("Deutsch")));
    }
}
