package ui.steps;

import ui.dto.User;
import ui.pages.InactivePage;
import ui.pages.SignupPage;

import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class RegistrationStep {

    LoginStep loginStep;
    SignupPage signupPage;
    InactivePage inactivePage;

    public RegistrationStep() {
        loginStep = new LoginStep();
        signupPage = new SignupPage();
        inactivePage = new InactivePage();
    }

    public void submitSignupForm(String email, String password, String confirmation) {
        loginStep.openSignupPage();
        signupPage.submitSignup(email, password, confirmation);
        webdriver().shouldHave(urlContaining("/signup"));
    }

    public void registerNewUser(User user) {
        loginStep.openSignupPage();
        signupPage.submitSignup(user.getEmail(), user.getPassword(), user.getPassword());
        webdriver().shouldHave(urlContaining("/inactive"));
        inactivePage.isPageOpened();
    }
}
