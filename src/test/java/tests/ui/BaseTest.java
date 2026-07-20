package tests.ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.testng.AllureTestNg;
import listeners.TestListener;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import ui.steps.CaseStep;
import ui.steps.LoginStep;
import ui.steps.PlanStep;
import ui.steps.ProjectStep;
import ui.steps.RegistrationStep;
import ui.steps.RunStep;
import ui.steps.SuiteStep;
import ui.dto.Project;
import ui.pages.InactivePage;
import ui.pages.LoginPage;
import ui.pages.PasswordResetPage;
import ui.pages.PlansPage;
import ui.pages.RepositoryPage;
import ui.pages.RunsPage;
import ui.pages.SignupPage;
import utils.PropertyReader;

import java.util.HashMap;
import java.util.Map;

@Listeners({AllureTestNg.class, TestListener.class})
public class BaseTest {

    protected Project projectToDelete;

    LoginStep loginStep;
    ProjectStep projectStep;
    RegistrationStep registrationStep;
    SuiteStep suiteStep;
    CaseStep caseStep;
    PlanStep planStep;
    RunStep runStep;
    LoginPage loginPage;
    PasswordResetPage passwordResetPage;
    SignupPage signupPage;
    InactivePage inactivePage;
    RepositoryPage repositoryPage;
    PlansPage plansPage;
    RunsPage runsPage;

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true, description = "Настройка драйвера")
    public void setUp(@Optional("chrome") String browser) {
        projectToDelete = null;
        Configuration.browser = browser.toLowerCase();
        Configuration.baseUrl = PropertyReader.getProperty("base.url");
        Configuration.timeout = 30000;
        Configuration.clickViaJs = true;
        Configuration.browserSize = "1920x1080";

        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            Map<String, Object> chromePrefs = new HashMap<>();
            chromePrefs.put("credentials_enable_service", false);
            chromePrefs.put("profile.password_manager_enabled", false);
            options.setExperimentalOption("prefs", chromePrefs);
            options.addArguments(
                    "--incognito",
                    "--disable-notifications",
                    "--disable-popup-blocking",
                    "--disable-infobars",
                    "--headless"
            );
            Configuration.browserCapabilities = options;
        } else if (browser.equalsIgnoreCase("edge")) {
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--headless");
            Configuration.browserCapabilities = options;
        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless");
            Configuration.browserCapabilities = options;
        }

        loginStep = new LoginStep();
        projectStep = new ProjectStep();
        registrationStep = new RegistrationStep();
        suiteStep = new SuiteStep();
        caseStep = new CaseStep();
        planStep = new PlanStep();
        runStep = new RunStep();
        loginPage = new LoginPage();
        passwordResetPage = new PasswordResetPage();
        signupPage = new SignupPage();
        inactivePage = new InactivePage();
        repositoryPage = new RepositoryPage();
        plansPage = new PlansPage();
        runsPage = new RunsPage();
    }

    @AfterMethod(alwaysRun = true, description = "Удаление проекта и закрытие браузера")
    public void tearDown() {
        try {
            if (projectToDelete != null && WebDriverRunner.hasWebDriverStarted()) {
                try {
                    projectStep.deleteProject(projectToDelete);
                } catch (Exception ignored) {
                }
            }
        } finally {
            projectToDelete = null;
            if (WebDriverRunner.hasWebDriverStarted()) {
                WebDriverRunner.closeWebDriver();
            }
        }
    }
}
