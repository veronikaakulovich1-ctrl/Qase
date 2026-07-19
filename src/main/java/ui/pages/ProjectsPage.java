package ui.pages;

import ui.dict.Elements;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Selenide;

@Log4j2
public class ProjectsPage extends BasePage {

    private static final String OPEN_ACTION_MENU = "button[aria-label='Open action menu']";
    private static final String REMOVE_BUTTON = "[data-testid=remove]";
    private static final String SETTINGS_BUTTON = "[data-testid=settings]";
    private static final String DELETE_DIALOG = "dialog";
    private static final String CONFIRM_DELETE_BUTTON = "Delete project";

    @Step("Open projects page")
    public ProjectsPage open() {
        log.info("Open projects page");
        Selenide.open("/projects");
        return this;
    }

    @Step("Click 'Create new project'")
    public NewProjectPage clickCreateNewProject() {
        log.info("Click 'Create new project'");
        $(byText(Elements.CREATE_NEW_PROJECT))
                .shouldBe(visible)
                .click();
        return new NewProjectPage();
    }

    @Step("Verify project '{}' is displayed in the list")
    public ProjectsPage verifyProjectDisplayed(String name) {
        log.info("Verify project '{}' is displayed in the list", name);
        $(byText(name))
                .shouldBe(visible)
                .ancestor("tr")
                .shouldHave(text(name));
        return this;
    }

    @Step("Get project name '{}' from the list")
    public String getProjectName(String name) {
        log.info("Get project name '{}' from the list", name);
        return $(byText(name)).shouldBe(visible).getText().trim();
    }

    @Step("Open settings for project '{}'")
    public ProjectSettingsPage openSettings(String projectName) {
        log.info("Open settings for project '{}'", projectName);
        $(byText(projectName))
                .ancestor("tr")
                .find(OPEN_ACTION_MENU)
                .click();
        $(SETTINGS_BUTTON).click();
        return new ProjectSettingsPage();
    }

    @Step("Delete project '{}'")
    public ProjectsPage deleteProject(String projectName) {
        log.info("Delete project '{}'", projectName);
        $(byText(projectName))
                .ancestor("tr")
                .find(OPEN_ACTION_MENU)
                .click();
        $(REMOVE_BUTTON).click();
        $(DELETE_DIALOG).shouldBe(visible);
        $(DELETE_DIALOG).$$("button").findBy(text(CONFIRM_DELETE_BUTTON)).click();
        $(DELETE_DIALOG).should(disappear);
        return this;
    }

    @Override
    public ProjectsPage isPageOpened() {
        $(byText(Elements.CREATE_NEW_PROJECT)).shouldBe(visible);
        return this;
    }
}
