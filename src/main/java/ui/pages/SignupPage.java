package ui.pages;

import ui.dict.Elements;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.shadowCss;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class SignupPage extends BasePage {

    private static final String EMAIL = "[name=email]";
    private static final String PASSWORD = "[name=password]";
    private static final String PASSWORD_CONFIRMATION = "[name=passwordConfirmation]";
    private static final String EMAIL_ERROR = "//input[@name='email']/parent::div/following-sibling::small";
    private static final String PASSWORD_ERROR = "//input[@name='password']/parent::div/following-sibling::small";
    private static final String PASSWORD_CONFIRMATION_ERROR = "//input[@name='passwordConfirmation']/parent::div/following-sibling::small";
    private static final String COOKIE_ACCEPT = "#accept";
    private static final String COOKIE_BANNER = "#usercentrics-cmp-ui";

    @Override
    public SignupPage isPageOpened() {
        $(EMAIL).shouldBe(visible);
        $(PASSWORD).shouldBe(visible);
        $(PASSWORD_CONFIRMATION).shouldBe(visible);
        $(byText(Elements.SIGN_UP_WITH_EMAIL)).shouldBe(visible);
        return this;
    }

    @Step("Submit signup form with email '{}', password and confirmation")
    public SignupPage submitSignup(String email, String password, String confirmation) {
        log.info("Submit signup form with email '{}', password '{}' and confirmation '{}'", email, password, confirmation);
        $(shadowCss(COOKIE_ACCEPT, COOKIE_BANNER)).click();
        $(EMAIL).setValue(email);
        $(PASSWORD).setValue(password);
        $(PASSWORD_CONFIRMATION).setValue(confirmation);
        $(byText(Elements.SIGN_UP_WITH_EMAIL)).click();
        return this;
    }

    @Step("Check if email field is visible")
    public boolean isEmailFieldVisible() {
        log.info("Check if email field is visible");
        return $(EMAIL).is(visible);
    }

    @Step("Check if password field is visible")
    public boolean isPasswordFieldVisible() {
        log.info("Check if password field is visible");
        return $(PASSWORD).is(visible);
    }

    @Step("Check if password confirmation field is visible")
    public boolean isPasswordConfirmationFieldVisible() {
        log.info("Check if password confirmation field is visible");
        return $(PASSWORD_CONFIRMATION).is(visible);
    }

    @Step("Check if 'Sign up with email' button is visible")
    public boolean isSignUpButtonVisible() {
        log.info("Check if 'Sign up with email' button is visible");
        return $(byText(Elements.SIGN_UP_WITH_EMAIL)).is(visible);
    }

    @Step("Check if email field shows error '{}'")
    public boolean isEmailErrorVisible(String expectedError) {
        log.info("Check if email field shows error '{}'", expectedError);
        return $x(EMAIL_ERROR).is(text(expectedError));
    }

    @Step("Check if password field shows error '{}'")
    public boolean isPasswordErrorVisible(String expectedError) {
        log.info("Check if password field shows error '{}'", expectedError);
        return $x(PASSWORD_ERROR).is(text(expectedError));
    }

    @Step("Check if password confirmation field shows error '{}'")
    public boolean isPasswordConfirmationErrorVisible(String expectedError) {
        log.info("Check if password confirmation field shows error '{}'", expectedError);
        return $x(PASSWORD_CONFIRMATION_ERROR).is(text(expectedError));
    }
}
