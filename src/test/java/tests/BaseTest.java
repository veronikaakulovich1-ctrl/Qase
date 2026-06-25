package tests;

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
import steps.LoginStep;
import steps.ProjectStep;
import utils.PropertyReader;

import java.util.HashMap;
import java.util.Map;

@Listeners({AllureTestNg.class, TestListener.class})
public class BaseTest {

    LoginStep loginStep;
    ProjectStep projectStep;

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true, description = "Настройка драйвера")
    public void setUp(@Optional("chrome") String browser) {
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
                    "--disable-infobars"
            );
            Configuration.browserCapabilities = options;
        } else if (browser.equalsIgnoreCase("edge")) {
            EdgeOptions options = new EdgeOptions();
            Configuration.browserCapabilities = options;
        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            Configuration.browserCapabilities = options;
        }

        loginStep = new LoginStep();
        projectStep = new ProjectStep();
    }

    @AfterMethod(alwaysRun = true, description = "Закрытие браузера")
    public void tearDown() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            WebDriverRunner.closeWebDriver();
        }
    }
}
