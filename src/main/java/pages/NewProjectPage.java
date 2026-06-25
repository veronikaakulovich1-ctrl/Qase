package pages;

import dict.Elements;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class NewProjectPage extends BasePage {

    private static final String PROJECT_NAME = "#project-name";
    private static final String PROJECT_CODE = "#project-code";

    @Step("Fill project name '{}' and code '{}' and submit")
    public ProjectsPage createProject(String name, String code) {
        $(PROJECT_NAME).setValue(name);
        $(PROJECT_CODE).setValue(code);
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
