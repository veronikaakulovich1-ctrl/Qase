package ui.pages;

import ui.dict.Elements;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class ProjectSettingsPage extends BasePage {

    private static final String PROJECT_NAME = "#project-name";
    private static final String PROJECT_CODE = "#project-code";

    @Override
    public ProjectSettingsPage isPageOpened() {
        $(PROJECT_NAME).shouldBe(visible);
        $(byText(Elements.UPDATE_SETTINGS)).shouldBe(visible);
        return this;
    }

    @Step("Update project name '{}' and code '{}'")
    public ProjectsPage updateSettings(String name, String code) {
        log.info("Update project name '{}' and code '{}'", name, code);
        $(PROJECT_NAME).setValue(name);
        $(PROJECT_CODE).setValue(code);
        $(byText(Elements.UPDATE_SETTINGS)).click();
        return new ProjectsPage();
    }
}
