package tests.real;

import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;

@Tag("real")
public class AndroidSearchTests extends TestBase {
    @Test
    void searchTest() {

        step("Skip onboarding", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")).click());

        step("Type search", () -> {
            $(AppiumBy.accessibilityId("Поиск по Википедии")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text"))
                    .sendKeys("Google");
        });

        step("Verify content found", () ->
                $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
    }

    @Tag("realSearchResultsTest")
    @Test
    void searchResultsTest() {
        step("Skip onboarding", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")).click());

        step("Type search", () -> {
            $(AppiumBy.accessibilityId("Поиск по Википедии")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text"))
                    .sendKeys("Google");
        });

        step("Open article", () ->
                $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title")).first().click());

        step("Verify content found", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id/page_web_view")).shouldBe(visible));
    }
}