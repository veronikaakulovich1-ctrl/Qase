package tests.ui;

import ui.dto.Case;
import ui.dto.Project;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

import static ui.dto.CaseFactory.getCase;
import static ui.dto.ProjectFactory.getProject;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class RunTest extends BaseTest {

    Project project = getProject();
    Project deleteRunProject = getProject();
    Case testCase = getCase();
    Case caseForDeleteRun = getCase();

    @Test(
            description = "Create test run with case",
            groups = "smoke"
    )
    @Owner("Veronika Akulovich")
    @Severity(SeverityLevel.CRITICAL)
    public void checkCreateRun() {
        loginStep.auth();
        projectStep.createProject(project);
        projectToDelete = project;
        caseStep.createCase(testCase);
        repositoryPage.getCaseTitle(testCase.getTitle());
        String runTitle = runStep.createRun();

        String actualTitle = runsPage.getRunTitle(runTitle);
        assertEquals(actualTitle, runTitle,
                "Run title in list should match the created title");
    }

    @Test(
            description = "Delete test run",
            groups = "smoke"
    )
    @Owner("Veronika Akulovich")
    @Severity(SeverityLevel.CRITICAL)
    public void checkDeleteRun() {
        loginStep.auth();
        projectStep.createProject(deleteRunProject);
        projectToDelete = deleteRunProject;
        caseStep.createCase(caseForDeleteRun);
        repositoryPage.getCaseTitle(caseForDeleteRun.getTitle());
        String runTitle = runStep.createRun();
        runStep.deleteRun(runTitle);

        assertFalse(runsPage.isRunDisplayed(runTitle),
                "Deleted run should not be displayed in the list");
    }
}
