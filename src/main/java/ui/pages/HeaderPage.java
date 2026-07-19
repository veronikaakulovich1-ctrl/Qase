package ui.pages;

import ui.dict.Elements;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class HeaderPage extends BasePage {

    private static final String USER_MENU = "button[aria-label='user']";

    @Override
    public HeaderPage isPageOpened() {
        $(USER_MENU).shouldBe(visible);
        return this;
    }

    @Step("Open user menu")
    public HeaderPage openUserMenu() {
        log.info("Open user menu");
        $(USER_MENU).shouldBe(visible).click();
        $(byText(Elements.SIGN_OUT)).shouldBe(visible);
        return this;
    }

    @Step("Click 'Sign out'")
    public LoginPage clickSignOut() {
        log.info("Click 'Sign out'");
        $(byText(Elements.SIGN_OUT)).click();
        return new LoginPage();
    }
}
