package tests.ui;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @Test(
            description = "Login page opens with email, password fields and Sign in button",
            groups = "smoke"
    )
    @Owner("Veronika Akulovich")
    @Severity(SeverityLevel.CRITICAL)
    public void checkLoginPageOpens() {
        loginStep.openLoginPage();
        assertTrue(loginPage.isEmailFieldVisible(),
                "Email field isn't visible");
        assertTrue(loginPage.isPasswordFieldVisible(),
                "Password field isn't visible");
        assertTrue(loginPage.isSignInButtonVisible(),
                "Sign in button isn't visible");
    }

    @Test(
            description = "Forgot password link leads to password reset page",
            groups = "regression"
    )
    @Owner("Veronika Akulovich")
    @Severity(SeverityLevel.NORMAL)
    public void checkForgotPasswordLink() {
        loginStep.openForgotPasswordPage();
        assertTrue(passwordResetPage.isResetButtonVisible(),
                "Send password reset link button isn't visible");
    }

    @Test(
            description = "Create an account link leads to signup page",
            groups = "regression"
    )
    @Owner("Veronika Akulovich")
    @Severity(SeverityLevel.NORMAL)
    public void checkCreateAccountLink() {
        loginStep.openSignupPage();
        assertTrue(signupPage.isSignUpButtonVisible(),
                "Sign up with email button isn't visible");
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][]{
                {"", ""},
                {"", "password123"},
                {"user@mail.com", ""},
        };
    }

    @Test(
            dataProvider = "loginData",
            description = "Required field errors on login form",
            groups = "regression"
    )
    @Owner("Veronika Akulovich")
    @Severity(SeverityLevel.NORMAL)
    public void checkRequiredFieldErrors(String email, String password) {
        loginStep.submitLoginForm(email, password);

        if (email.isEmpty()) {
            assertTrue(loginPage.isEmailRequiredErrorVisible(),
                    "Email error should be visible");
        }

        if (password.isEmpty()) {
            assertTrue(loginPage.isPasswordRequiredErrorVisible(),
                    "Password error should be visible");
        }
    }

    @Test(
            description = "Remember me checkbox is visible and clickable",
            groups = "regression"
    )
    @Owner("Veronika Akulovich")
    @Severity(SeverityLevel.NORMAL)
    public void checkRememberMeCheckbox() {
        loginStep.openLoginPage();
        assertTrue(loginPage.isRememberMeVisible(),
                "Remember me checkbox isn't visible");

        boolean initialState = loginPage.isRememberMeSelected();
        loginPage.clickRememberMe();
        assertTrue(loginPage.isRememberMeSelected() != initialState,
                "Remember me checkbox state should change after click");
    }
}
