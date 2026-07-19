package tests.ui;

import org.testng.asserts.SoftAssert;
import ui.dict.Elements;
import ui.dto.User;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ui.dto.UserFactory.getUser;
import static org.testng.Assert.assertTrue;

public class RegistrationTest extends BaseTest {

    @Test(
            description = "Create an account link opens signup page with registration fields",
            groups = "smoke"
    )
    @Owner("Veronika Akulovich")
    @Severity(SeverityLevel.CRITICAL)
    public void checkSignupPageOpens() {
        SoftAssert softAssert = new SoftAssert();
        loginStep.openSignupPage();
        softAssert.assertTrue(signupPage.isEmailFieldVisible(),
                "Email field isn't visible");
        softAssert.assertTrue(signupPage.isPasswordFieldVisible(),
                "Password field isn't visible");
        softAssert.assertTrue(signupPage.isPasswordConfirmationFieldVisible(),
                "Password confirmation field isn't visible");
        softAssert.assertTrue(signupPage.isSignUpButtonVisible(),
                "Sign up with email button isn't visible");
        softAssert.assertAll();
    }

    @Test(
            description = "Successful registration opens inactive page",
            groups = "smoke"
    )
    @Owner("Veronika Akulovich")
    @Severity(SeverityLevel.CRITICAL)
    public void checkSuccessfulRegistration() {
        User user = getUser();
        SoftAssert softAssert = new SoftAssert();
        registrationStep.registerNewUser(user);
        softAssert.assertTrue(inactivePage.isCongratulationsVisible(),
                "Congratulations message isn't visible");
        softAssert.assertTrue(inactivePage.isRegisteredEmailDisplayed(user.getEmail()),
                "Registered email isn't displayed");
        softAssert.assertAll();
    }

    @DataProvider(name = "signupData")
    public Object[][] signupData() {
        return new Object[][]{
                {"", "", ""},
                {"user@mail.com", "", ""},
                {"user@mail.com", "password1234", "different1234"},
        };
    }

    @Test(
            dataProvider = "signupData",
            description = "Validation errors on signup form",
            groups = "regression"
    )
    @Owner("Veronika Akulovich")
    @Severity(SeverityLevel.NORMAL)
    public void checkSignupValidationErrors(String email, String password, String confirmation) {
        SoftAssert softAssert = new SoftAssert();
        registrationStep.submitSignupForm(email, password, confirmation);

        if (email.isEmpty()) {
            softAssert.assertTrue(signupPage.isEmailErrorVisible(Elements.FIELD_REQUIRED_ERROR),
                    "Email error should be visible");
        }

        if (password.isEmpty()) {
            softAssert.assertTrue(signupPage.isPasswordErrorVisible(Elements.PASSWORD_MIN_LENGTH_ERROR),
                    "Password error should be visible");
        }

        if (confirmation.isEmpty()) {
            softAssert.assertTrue(signupPage.isPasswordConfirmationErrorVisible(Elements.PASSWORD_MIN_LENGTH_ERROR),
                    "Password confirmation error should be visible");
        }

        if (!password.isEmpty() && !confirmation.isEmpty() && !password.equals(confirmation)) {
            softAssert.assertTrue(signupPage.isPasswordConfirmationErrorVisible(Elements.PASSWORDS_MUST_MATCH_ERROR),
                    "Passwords must match error should be visible");
        }
        softAssert.assertAll();
    }
}
