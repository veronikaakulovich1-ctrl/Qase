package ui.pages;

import ui.dict.Elements;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Log4j2
public class RepositoryPage extends BasePage {

    private static final String SUITE_NAME = "#title";
    private static final String DESCRIPTION = "#description";
    private static final String PRECONDITIONS = "#preconditions";
    private static final String QUICK_TEST_TITLE = "input[placeholder='Test case title']";
    private static final String SUITE_IN_TREE = "[id^=suite-tree-]";
    private static final String DELETE_CASE_BUTTON = "button[aria-label='Delete']";
    private static final String DELETE_DIALOG = "dialog";
    private static final String CONFIRM_DELETE_CASE = "Delete";

    @Override
    public RepositoryPage isPageOpened() {
        $(byText(Elements.CREATE_NEW_SUITE)).shouldBe(visible);
        return this;
    }

    @Step("Click 'Create new suite'")
    public RepositoryPage clickCreateNewSuite() {
        log.info("Click 'Create new suite'");
        $(byText(Elements.CREATE_NEW_SUITE)).click();
        $(SUITE_NAME).shouldBe(visible);
        return this;
    }

    @Step("Click 'Create new case'")
    public CreateCasePage clickCreateNewCase() {
        log.info("Click 'Create new case'");
        $(byText(Elements.CREATE_NEW_CASE)).shouldBe(visible).click();
        return new CreateCasePage();
    }

    @Step("Open suite '{name}' in tree")
    public RepositoryPage openSuite(String name) {
        log.info("Open suite '{}' in tree", name);
        $$(SUITE_IN_TREE).findBy(text(name)).shouldBe(visible).click();
        $(byText(Elements.QUICK_TEST)).shouldBe(visible);
        return this;
    }

    @Step("Create case via Quick test: '{title}'")
    public RepositoryPage createCaseViaQuickTest(String title) {
        log.info("Create case via Quick test: '{}'", title);
        $(byText(Elements.QUICK_TEST)).shouldBe(visible).click();
        $(QUICK_TEST_TITLE).shouldBe(visible)
                .setValue(title)
                .pressEnter();
        $(byText(title)).shouldBe(visible);
        return this;
    }

    @Step("Open case '{title}'")
    public RepositoryPage openCase(String title) {
        log.info("Open case '{}'", title);
        $(byText(title)).shouldBe(visible).click();
        $(DELETE_CASE_BUTTON).shouldBe(visible);
        return this;
    }

    @Step("Delete opened case")
    public RepositoryPage deleteCase() {
        log.info("Delete opened case");
        $(DELETE_CASE_BUTTON).shouldBe(visible).click();
        $(DELETE_DIALOG).shouldBe(visible);
        $(DELETE_DIALOG).$$("button").findBy(text(CONFIRM_DELETE_CASE)).click();
        $(DELETE_DIALOG).should(disappear);
        return this;
    }

    @Step("Get 'Create new case' button text")
    public String getCreateCaseButtonText() {
        log.info("Get 'Create new case' button text");
        return $(byText(Elements.CREATE_NEW_CASE)).shouldBe(visible).getText().trim();
    }

    @Step("Create suite '{}' with required field")
    public RepositoryPage createSuite(String name) {
        log.info("Create suite '{}' with required field", name);
        $(SUITE_NAME).setValue(name);
        $(byText(Elements.CREATE)).click();
        $(SUITE_NAME).should(disappear);
        return this;
    }

    @Step("Create suite with name, description and preconditions")
    public RepositoryPage createFullSuite(String name, String description, String preconditions) {
        log.info("Create suite with name '{}', description '{}' and preconditions '{}'", name, description, preconditions);
        $(SUITE_NAME).setValue(name);

        $(DESCRIPTION).click();
        $(DESCRIPTION).sendKeys(description);

        $(PRECONDITIONS).click();
        $(PRECONDITIONS).sendKeys(preconditions);

        $(byText(Elements.CREATE)).click();
        $(SUITE_NAME).should(disappear);
        return this;
    }

    @Step("Get suite name '{}' from repository")
    public String getSuiteName(String name) {
        log.info("Get suite name '{}' from repository", name);
        return $(byText(name)).shouldBe(visible).getText().trim();
    }

    @Step("Get case title '{}' from repository")
    public String getCaseTitle(String title) {
        log.info("Get case title '{}' from repository", title);
        return $(byText(title)).shouldBe(visible).getText().trim();
    }
}
