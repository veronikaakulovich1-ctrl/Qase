package ui.pages;

import ui.dict.Elements;
import ui.dto.Case;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import java.io.File;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class CreateCasePage extends BasePage {

    private static final String TITLE = "input[name=title]";
    private static final String SAVE_CASE = "#save-case";
    private static final String FILE_INPUT = "input.dz-hidden-input";
    private static final String ATTACHMENT_LINK = "a[href*='%s']";
    private static final String STATUS_DROPDOWN =
            "//label[normalize-space()='Status']/following::div[@role='combobox'][1]";
    private static final String SEVERITY_DROPDOWN =
            "//label[normalize-space()='Severity']/following::div[@role='combobox'][1]";
    private static final String PRIORITY_DROPDOWN =
            "//label[normalize-space()='Priority']/following::div[@role='combobox'][1]";
    private static final String TYPE_DROPDOWN =
            "//label[normalize-space()='Type']/following::div[@role='combobox'][1]";

    @Override
    public CreateCasePage isPageOpened() {
        $(TITLE).shouldBe(visible);
        $(SAVE_CASE).shouldBe(visible);
        return this;
    }

    @Step("Create test case '{testCase.title}'")
    public RepositoryPage createCase(Case testCase) {
        log.info("Create test case '{}'", testCase.getTitle());
        fillCaseFields(testCase);
        $(SAVE_CASE).shouldBe(enabled).click();
        waitUntilCaseOpened(testCase.getTitle());
        return new RepositoryPage();
    }

    @Step("Create test case '{testCase.title}' with attachment")
    public RepositoryPage createCaseWithAttachment(Case testCase, File file) {
        log.info("Create test case '{}' with attachment '{}'", testCase.getTitle(), file.getName());
        fillCaseFields(testCase);
        $(byText(Elements.ADD_ATTACHMENT)).click();
        $(byText(Elements.UPLOAD_ATTACHMENT)).shouldBe(visible);
        $(FILE_INPUT).should(exist).uploadFile(file);
        $(byText(Elements.UPLOAD_ATTACHMENT)).should(disappear);
        $(String.format(ATTACHMENT_LINK, file.getName())).shouldBe(visible);
        $(SAVE_CASE).shouldBe(enabled).click();
        waitUntilCaseOpened(testCase.getTitle());
        return new RepositoryPage();
    }

    private void waitUntilCaseOpened(String caseTitle) {
        $(SAVE_CASE).should(disappear);
        $(byText(caseTitle)).shouldBe(visible);
    }

    private void fillCaseFields(Case testCase) {
        $(TITLE).setValue(testCase.getTitle());
        selectOption(STATUS_DROPDOWN, testCase.getStatus().getUiText());
        selectOption(SEVERITY_DROPDOWN, testCase.getSeverity().getUiText());
        selectOption(PRIORITY_DROPDOWN, testCase.getPriority().getUiText());
        selectOption(TYPE_DROPDOWN, testCase.getType().getUiText());
    }

    private void selectOption(String dropdown, String option) {
        $x(dropdown).shouldBe(visible).click();
        $(byText(option)).shouldBe(visible).click();
    }
}
