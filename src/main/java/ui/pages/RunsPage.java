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
public class RunsPage extends BasePage {

    private static final String TITLE = "input[name=title]";
    private static final String DIALOG = "dialog";
    private static final String OPEN_ACTION_MENU = "button[aria-label='Open action menu']";
    private static final String CONFIRM_DELETE_BUTTON = "Delete";

    @Override
    public RunsPage isPageOpened() {
        $(byText(Elements.START_NEW_TEST_RUN)).shouldBe(visible);
        return this;
    }

    @Step("Open Test Runs from side menu")
    public RunsPage openFromSideMenu() {
        log.info("Open Test Runs from side menu");
        $(byText(Elements.TEST_RUNS)).shouldBe(visible).click();
        return this;
    }

    @Step("Click 'Start new test run'")
    public RunsPage clickStartNewTestRun() {
        log.info("Click 'Start new test run'");
        $(byText(Elements.START_NEW_TEST_RUN)).shouldBe(visible).click();
        $(byText(Elements.NEW_TEST_RUN)).shouldBe(visible);
        return this;
    }

    @Step("Create test run with all cases")
    public String createRun() {
        log.info("Create test run with all cases");
        $(DIALOG).$(byText(Elements.SELECT_CASES)).shouldBe(visible).click();
        $(byText(Elements.SELECT_TEST_CASES)).shouldBe(visible);
        $$(DIALOG).last().$$(byText(Elements.SELECT_ALL)).last().shouldBe(visible).click();
        $$(DIALOG).last().$$("button").findBy(text(Elements.DONE)).click();
        $(byText(Elements.SELECT_TEST_CASES)).should(disappear);

        String title = $(DIALOG).$(TITLE).shouldBe(visible).getValue();
        $(DIALOG).$$("button").findBy(text(Elements.START_RUN)).click();
        $(DIALOG).should(disappear);
        return title;
    }

    @Step("Get run title '{title}' from the list")
    public String getRunTitle(String title) {
        log.info("Get run title '{}' from the list", title);
        return $(byText(title)).shouldBe(visible).getText().trim();
    }

    @Step("Delete run '{title}'")
    public RunsPage deleteRun(String title) {
        log.info("Delete run '{}'", title);
        $(byText(title))
                .ancestor("tr")
                .find(OPEN_ACTION_MENU)
                .click();
        $(byText(CONFIRM_DELETE_BUTTON)).click();
        $(DIALOG).shouldBe(visible);
        $(DIALOG).$$("button").findBy(text(CONFIRM_DELETE_BUTTON)).click();
        $(DIALOG).should(disappear);
        return this;
    }

    @Step("Check if run '{title}' is displayed")
    public boolean isRunDisplayed(String title) {
        log.info("Check if run '{}' is displayed", title);
        return $(byText(title)).is(visible);
    }
}
