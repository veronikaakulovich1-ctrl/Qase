package ui.pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

@Log4j2
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
        log.info("Check if congratulations message is visible");
        return $(CONGRATULATIONS_TITLE).is(visible);
    }

    @Step("Check if registered email '{}' is displayed")
    public boolean isRegisteredEmailDisplayed(String email) {
        log.info("Check if registered email '{}' is displayed", email);
        return $(REGISTERED_EMAIL).is(text(email));
    }
}
