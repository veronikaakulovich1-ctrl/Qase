package ui.pages;

import ui.dict.Elements;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class PasswordResetPage extends BasePage {

    @Override
    public PasswordResetPage isPageOpened() {
        $(byText(Elements.SEND_PASSWORD_RESET_LINK)).shouldBe(visible);
        return this;
    }

    @Step("Check if 'Send password reset link' button is visible")
    public boolean isResetButtonVisible() {
        return $(byText(Elements.SEND_PASSWORD_RESET_LINK)).is(visible);
    }
}
