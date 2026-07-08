package ui.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class InactivePage extends BasePage {

    private static final String CONGRATULATIONS_TITLE = "[data-qase-test=congratulations]";
    private static final String REGISTERED_EMAIL = "[data-qase-test=email]";

    @Override
    public InactivePage isPageOpened() {
        $(CONGRATULATIONS_TITLE).shouldBe(visible);
        $(REGISTERED_EMAIL).shouldBe(visible);
        return this;
    }

    @Step("Check if congratulations message is visible")
    public boolean isCongratulationsVisible() {
        return $(CONGRATULATIONS_TITLE).is(visible);
    }

    @Step("Check if registered email '{}' is displayed")
    public boolean isRegisteredEmailDisplayed(String email) {
        return $(REGISTERED_EMAIL).is(text(email));
    }
}
