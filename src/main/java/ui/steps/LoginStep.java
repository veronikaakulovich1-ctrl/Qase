package ui.steps;

import lombok.extern.log4j.Log4j2;
import ui.pages.HeaderPage;
import ui.pages.LoginPage;
import utils.PropertyReader;

import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

@Log4j2
public class LoginStep {

    LoginPage loginPage;
    HeaderPage headerPage;

    public LoginStep() {
        loginPage = new LoginPage();
        headerPage = new HeaderPage();
    }

    public void auth() {
        auth(PropertyReader.getProperty("user"), PropertyReader.getProperty("password"));
    }

    public void auth(String user, String password) {
        log.info("Login with valid user name  and password");
        loginPage.open()
                .isPageOpened()
                .login(user, password);
        webdriver().shouldHave(urlContaining("/projects"));
    }

    public void openLoginPage() {
        loginPage.open().isPageOpened();
        webdriver().shouldHave(urlContaining("/login"));
    }

    public void submitLoginForm(String email, String password) {
        loginPage.open()
                .isPageOpened()
                .submitLogin(email, password);
        webdriver().shouldHave(urlContaining("/login"));
    }

    public void openForgotPasswordPage() {
        loginPage.open()
                .isPageOpened()
                .clickForgotPassword()
                .isPageOpened();
        webdriver().shouldHave(urlContaining("/password/reset"));
    }

    public void openSignupPage() {
        loginPage.open()
                .isPageOpened()
                .clickCreateAccount()
                .isPageOpened();
        webdriver().shouldHave(urlContaining("/signup"));
    }

    public void signOut() {
        headerPage.openUserMenu()
                .clickSignOut()
                .isPageOpened();
        webdriver().shouldHave(urlContaining("/login"));
    }
}
