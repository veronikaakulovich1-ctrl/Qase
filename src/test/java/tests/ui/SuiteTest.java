package tests.ui;

import ui.dto.Project;
import ui.dto.Suite;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

import static ui.dto.ProjectFactory.getProject;
import static ui.dto.SuiteFactory.getFullSuite;
import static ui.dto.SuiteFactory.getSuite;
import static org.testng.Assert.assertEquals;

public class SuiteTest extends BaseTest {

    Project project = getProject();
    Project fullSuiteProject = getProject();
    Suite suite = getSuite();
    Suite fullSuite = getFullSuite();

    @Test(
            description = "Create suite with required name field",
            groups = "smoke"
    )
    @Owner("Veronika Akulovich")
    @Severity(SeverityLevel.CRITICAL)
    public void checkCreateSuite() {
        loginStep.auth();
        projectStep.createProject(project);
        projectToDelete = project;
        suiteStep.createSuite(suite);

        String actualName = repositoryPage.getSuiteName(suite.getName());
        assertEquals(actualName, suite.getName(),
                "Suite name in repository should match the created name");
    }

    @Test(
            description = "Create suite with name, description and preconditions",
            groups = "regression"
    )
    @Owner("Veronika Akulovich")
    @Severity(SeverityLevel.CRITICAL)
    public void checkCreateSuiteWithFullData() {
        loginStep.auth();
        projectStep.createProject(fullSuiteProject);
        projectToDelete = fullSuiteProject;
        suiteStep.createFullSuite(fullSuite);

        String actualName = repositoryPage.getSuiteName(fullSuite.getName());
        assertEquals(actualName, fullSuite.getName(),
                "Suite name in repository should match the created name");
    }
}
