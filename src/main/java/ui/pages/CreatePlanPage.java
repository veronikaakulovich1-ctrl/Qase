package ui.pages;

import ui.dict.Elements;
import ui.dto.Plan;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class CreatePlanPage extends BasePage {

    private static final String TITLE = "input[name=title]";
    private static final String ADD_CASES = "#edit-plan-add-cases-button";
    private static final String SAVE_PLAN = "#save-plan";
    private static final String SELECT_CASES_DIALOG = "dialog";

    @Override
    public CreatePlanPage isPageOpened() {
        $(TITLE).shouldBe(visible);
        $(SAVE_PLAN).shouldBe(visible);
        return this;
    }

    @Step("Create plan '{plan.title}' with all cases")
    public PlansPage createPlan(Plan plan) {
        log.info("Create plan '{}' with all cases", plan.getTitle());
        $(TITLE).setValue(plan.getTitle());
        $(ADD_CASES).shouldBe(visible).click();
        $(SELECT_CASES_DIALOG).shouldBe(visible);
        $(byText(Elements.SELECT_TEST_CASES)).shouldBe(visible);
        $(SELECT_CASES_DIALOG).$$(byText(Elements.SELECT_ALL)).last().shouldBe(visible).click();
        $(SELECT_CASES_DIALOG).$$("button").findBy(text(Elements.DONE)).click();
        $(SELECT_CASES_DIALOG).should(disappear);
        $(SAVE_PLAN).shouldBe(enabled).click();
        return new PlansPage();
    }
}
