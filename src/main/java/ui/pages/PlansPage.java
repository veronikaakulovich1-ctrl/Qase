package ui.pages;

import ui.dict.Elements;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class PlansPage extends BasePage {

    private static final String OPEN_ACTION_MENU = "button[aria-label='Open action menu']";
    private static final String DELETE_BUTTON = "[data-testid=plan-popup-delete]";
    private static final String DELETE_DIALOG = "dialog";
    private static final String CONFIRM_DELETE_BUTTON = "Delete plan";

    @Override
    public PlansPage isPageOpened() {
        $(byText(Elements.CREATE_PLAN)).shouldBe(visible);
        return this;
    }

    @Step("Open Test Plans from side menu")
    public PlansPage openFromSideMenu() {
        log.info("Open Test Plans from side menu");
        $(byText(Elements.TEST_PLANS)).shouldBe(visible).click();
        return this;
    }

    @Step("Click 'Create plan'")
    public CreatePlanPage clickCreatePlan() {
        log.info("Click 'Create plan'");
        $(byText(Elements.CREATE_PLAN)).shouldBe(visible).click();
        return new CreatePlanPage();
    }

    @Step("Get plan title '{title}' from the list")
    public String getPlanTitle(String title) {
        log.info("Get plan title '{}' from the list", title);
        return $(byText(title)).shouldBe(visible).getText().trim();
    }

    @Step("Delete plan '{title}'")
    public PlansPage deletePlan(String title) {
        log.info("Delete plan '{}'", title);
        $(byText(title))
                .ancestor("tr")
                .find(OPEN_ACTION_MENU)
                .click();
        $(DELETE_BUTTON).click();
        $(DELETE_DIALOG).shouldBe(visible);
        $(DELETE_DIALOG).$$("button").findBy(text(CONFIRM_DELETE_BUTTON)).click();
        $(DELETE_DIALOG).should(disappear);
        return this;
    }

    @Step("Check if plan '{title}' is displayed")
    public boolean isPlanDisplayed(String title) {
        log.info("Check if plan '{}' is displayed", title);
        return $(byText(title)).is(visible);
    }
}
