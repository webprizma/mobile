package tests;

import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@Tag("onboard")
public class OnboardTests extends TestBase {
    @Test
    void onboardingStepsTest() {
        step("Check languages is present on first screen", () -> {
            //проверить, что есть автоматически установленные языки
            $$(AppiumBy.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id='org.wikipedia.alpha:id/languagesList']" +
                    "/android.widget.TextView[@resource-id='org.wikipedia.alpha:id/option_label']"))
                    .shouldHave(sizeGreaterThan(0));
            //нажать продолжить
            $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
        });
        step("Check content is present on second screen", () -> {
            //проверить, что есть текст
            $(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView"))
                    .shouldHave(text("New ways to explore"));
            $(AppiumBy.id("org.wikipedia.alpha:id/secondaryTextView"))
                    .shouldHave(text("Dive down the Wikipedia rabbit hole with a constantly updating Explore feed. \n" +
                            "Customize the feed to your interests – whether it’s learning about historical events On this day, or rolling the dice with Random."));
            //нажать продолжить
            $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
        });
        step("Check image is present on third screen", () -> {
            //проверить, что есть изображение
            $(AppiumBy.id("org.wikipedia.alpha:id/imageViewCentered"))
                    .shouldBe(visible);
            //нажать продолжить
            $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
        });
        step("Check trigger is checked on fourth screen", () -> {
            //проверить, что триггер активен
            $(AppiumBy.id("org.wikipedia.alpha:id/switchView"))
                    .shouldHave(attribute("checked", "true"));
            //нажать начать
            $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_done_button")).click();
        });
    }

    @Disabled
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
