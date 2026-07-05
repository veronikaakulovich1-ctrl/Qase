package ui.pages;

import ui.dict.Elements;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Selenide;

public class ProjectsPage extends BasePage {

    private static final String OPEN_ACTION_MENU = "button[aria-label='Open action menu']";
    private static final String REMOVE_BUTTON = "[data-testid=remove]";

    @Step("Open projects page")
    public ProjectsPage open() {
        Selenide.open("/projects");
        return this;
    }

    @Step("Click 'Create new project'")
    public NewProjectPage clickCreateNewProject() {
        $(byText(Elements.CREATE_NEW_PROJECT))
                .shouldBe(visible)
                .click();
        return new NewProjectPage();
    }

    @Step("Verify project '{}' is displayed in the list")
    public ProjectsPage verifyProjectDisplayed(String name) {
        $(byText(name))
                .shouldBe(visible)
                .ancestor("tr")
                .shouldHave(text(name));
        return this;
    }

    @Step("Get project name '{}' from the list")
    public String getProjectName(String name) {
        return $(byText(name)).shouldBe(visible).getText().trim();
    }

    @Step("Delete project '{}'")
    public ProjectsPage deleteProject(String projectName) {
        $(byText(projectName))
                .ancestor("tr")
                .find(OPEN_ACTION_MENU)
                .click();
        $(REMOVE_BUTTON).click();
        $(byText(Elements.DELETE_PROJECT)).click();
        return this;
    }

    @Override
    public ProjectsPage isPageOpened() {
        $(byText(Elements.CREATE_NEW_PROJECT)).shouldBe(visible);
        return this;
    }
}
