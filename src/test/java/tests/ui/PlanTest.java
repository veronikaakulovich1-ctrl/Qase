package tests.ui;

import ui.dto.Case;
import ui.dto.Plan;
import ui.dto.Project;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

import static ui.dto.CaseFactory.getCase;
import static ui.dto.PlanFactory.getPlan;
import static ui.dto.ProjectFactory.getProject;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class PlanTest extends BaseTest {

    Project project = getProject();
    Project deletePlanProject = getProject();
    Case testCase = getCase();
    Case caseForDeletePlan = getCase();
    Plan plan = getPlan();
    Plan planToDelete = getPlan();

    @Test(
            description = "Create test plan with case",
            groups = "smoke"
    )
    @Owner("Veronika Akulovich")
    @Severity(SeverityLevel.CRITICAL)
    public void checkCreatePlan() {
        loginStep.auth();
        projectStep.createProject(project);
        projectToDelete = project;
        caseStep.createCase(testCase);
        repositoryPage.getCaseTitle(testCase.getTitle());
        planStep.createPlan(plan);

        String actualTitle = plansPage.getPlanTitle(plan.getTitle());
        assertEquals(actualTitle, plan.getTitle(),
                "Plan title in list should match the created title");
    }

    @Test(
            description = "Delete test plan",
            groups = "smoke"
    )
    @Owner("Veronika Akulovich")
    @Severity(SeverityLevel.CRITICAL)
    public void checkDeletePlan() {
        loginStep.auth();
        projectStep.createProject(deletePlanProject);
        projectToDelete = deletePlanProject;
        caseStep.createCase(caseForDeletePlan);
        repositoryPage.getCaseTitle(caseForDeletePlan.getTitle());
        planStep.createPlan(planToDelete);
        planStep.deletePlan(planToDelete);

        assertFalse(plansPage.isPlanDisplayed(planToDelete.getTitle()),
                "Deleted plan should not be displayed in the list");
    }
}
