package ui.steps;

import ui.dto.Plan;
import lombok.extern.log4j.Log4j2;
import ui.pages.PlansPage;

@Log4j2
public class PlanStep {

    PlansPage plansPage;

    public PlanStep() {
        plansPage = new PlansPage();
    }

    public void createPlan(Plan plan) {
        log.info("New '{}' plan was created", plan);
        plansPage.openFromSideMenu()
                .isPageOpened()
                .clickCreatePlan()
                .isPageOpened()
                .createPlan(plan);
    }

    public void deletePlan(Plan plan) {
        log.info("Plan '{}' was deleted", plan);
        plansPage.deletePlan(plan.getTitle());
    }
}
