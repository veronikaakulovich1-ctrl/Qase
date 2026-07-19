package ui.pages;

import ui.dict.Elements;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class NewProjectPage extends BasePage {

    private static final String PROJECT_NAME = "#project-name";
    private static final String PROJECT_CODE = "#project-code";
    private static final String PROJECT_DESCRIPTION = "#description-area";
    private static final String ACCESS_PUBLIC = "input[type=radio][value='public']";

    @Step("Fill project name '{}' and code '{}' and submit")
    public ProjectsPage createProject(String name, String code) {
        log.info("Fill project name '{}' and code '{}' and submit", name, code);
        $(PROJECT_NAME).setValue(name);
        $(PROJECT_CODE).setValue(code);
        $(byText(Elements.CREATE_PROJECT)).click();
        return new ProjectsPage();
    }

    @Step("Fill name, code, description, Public access and submit")
    public ProjectsPage createFullProject(String name, String code, String description) {
        log.info("Fill name '{}', code '{}', description '{}' with Public access and submit", name, code, description);
        $(PROJECT_NAME).setValue(name);
        $(PROJECT_CODE).setValue(code);
        $(PROJECT_DESCRIPTION).setValue(description);
        $(ACCESS_PUBLIC).click();
        $(byText(Elements.CREATE_PROJECT)).click();
        return new ProjectsPage();
    }

    @Override
    public NewProjectPage isPageOpened() {
        $(PROJECT_NAME).shouldBe(visible);
        $(PROJECT_CODE).shouldBe(visible);
        return this;
    }
}
