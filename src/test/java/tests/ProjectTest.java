package tests;

import dto.Project;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

import static dto.ProjectFactory.getProject;
import static org.testng.Assert.assertEquals;

public class ProjectTest extends BaseTest {

    Project project = getProject();

    @Test(
            description = "Login and creation new project with filling name and code fields",
            testName = "Create new project",
            groups = "smoke"
    )
    @Owner("Veronika Akulovich")
    @Severity(SeverityLevel.CRITICAL)
    public void checkCreateNewProject() {
        loginStep.auth();
        projectStep.createProject(project);
        String actualName = projectStep.verifyProjectDisplayed(project);
        assertEquals(actualName, project.getName(), "Project name on the list should match the created name");
        projectStep.deleteProject(project);
    }
}
