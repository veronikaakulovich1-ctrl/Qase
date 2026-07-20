package ui.pages;

import ui.dict.Elements;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.selected;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.shadowCss;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.Selenide;

@Log4j2
public class LoginPage extends BasePage {

    private static final String LOGIN = "[name=email]";
    private static final String PASSWORD = "[name=password]";
    private static final String EMAIL_ERROR = "//input[@name='email']/parent::div/following-sibling::small";
    private static final String PASSWORD_ERROR = "//input[@name='password']/parent::div/following-sibling::small";
    private static final String COOKIE_ACCEPT = "#accept";
    private static final String COOKIE_BANNER = "#usercentrics-cmp-ui";
    private static final String REMEMBER_ME_CHECKBOX = "[name=remember]";
    private static final String REMEMBER_ME_LABEL = "//label[.//input[@name='remember']]";

    @Step("Opening login page")
    public LoginPage open() {
        log.info("Opening login page");
        Selenide.open("/login");
        return this;
    }

    @Step("Login with valid user name  and password")
    public void login(String user, String password) {
        log.info("Login with user name  and password");
        $(shadowCss(COOKIE_ACCEPT, COOKIE_BANNER)).click();
        submitLogin(user, password);
    }

    @Step("Submit login form with email and password ")
    public LoginPage submitLogin(String email, String password) {
        log.info("Submit login form with email  and password");
        $(LOGIN).setValue(email);
        $(PASSWORD).setValue(password);
        return clickSignIn();
    }

    @Step("Click 'Sign in' button")
    public LoginPage clickSignIn() {
        log.info("Click 'Sign in' button");
        $(byText(Elements.SIGN_IN)).click();
        return this;
    }

    @Step("Click 'Forgot password?' link")
    public PasswordResetPage clickForgotPassword() {
        log.info("Click 'Forgot password?' link");
        $(byText(Elements.FORGOT_PASSWORD)).click();
        return new PasswordResetPage();
    }

    @Step("Click 'Create an account' link")
    public SignupPage clickCreateAccount() {
        log.info("Click 'Create an account' link");
        $(byText(Elements.CREATE_AN_ACCOUNT)).click();
        return new SignupPage();
    }

    @Override
    public LoginPage isPageOpened() {
        $(LOGIN).shouldBe(visible);
        $(PASSWORD).shouldBe(visible);
        $(byText(Elements.SIGN_IN)).shouldBe(visible);
        return this;
    }

    @Step("Check if email field is visible")
    public boolean isEmailFieldVisible() {
        log.info("Check if email field is visible");
        return $(LOGIN).is(visible);
    }

    @Step("Check if password field is visible")
    public boolean isPasswordFieldVisible() {
        log.info("Check if password field is visible");
        return $(PASSWORD).is(visible);
    }

    @Step("Check if 'Sign in' button is visible")
    public boolean isSignInButtonVisible() {
        log.info("Check if 'Sign in' button is visible");
        return $(byText(Elements.SIGN_IN)).is(visible);
    }

    @Step("Check if email field shows required error")
    public boolean isEmailRequiredErrorVisible() {
        log.info("Check if email field shows required error");
        return $x(EMAIL_ERROR).is(text(Elements.FIELD_REQUIRED_ERROR));
    }

    @Step("Check if password field shows required error")
    public boolean isPasswordRequiredErrorVisible() {
        log.info("Check if password field shows required error");
        return $x(PASSWORD_ERROR).is(text(Elements.FIELD_REQUIRED_ERROR));
    }

    @Step("Check if 'Remember me' checkbox is visible")
    public boolean isRememberMeVisible() {
        log.info("Check if 'Remember me' checkbox is visible");
        return $x(REMEMBER_ME_LABEL).is(visible);
    }

    @Step("Check if 'Remember me' checkbox is selected")
    public boolean isRememberMeSelected() {
        log.info("Check if 'Remember me' checkbox is selected");
        return $(REMEMBER_ME_CHECKBOX).is(selected);
    }

    @Step("Click 'Remember me' checkbox")
    public LoginPage clickRememberMe() {
        log.info("Click 'Remember me' checkbox");
        $x(REMEMBER_ME_LABEL).click();
        return this;
    }
}
