package tests.ui;

import ui.dto.Project;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

import static ui.dto.ProjectFactory.getFullProject;
import static ui.dto.ProjectFactory.getProject;
import static org.testng.Assert.assertEquals;

public class ProjectTest extends BaseTest {

    Project project = getProject();
    Project fullProject = getFullProject();
    Project updatedProject = getProject();

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
        projectToDelete = project;

        String actualName = projectStep.verifyProjectDisplayed(project);
        assertEquals(actualName, project.getName(), "Project name on the list should match the created name");
    }

    @Test(
            description = "Create project with name, code, description and Public access",
            groups = "regression"
    )
    @Owner("Veronika Akulovich")
    @Severity(SeverityLevel.CRITICAL)
    public void checkCreateProjectWithFullData() {
        loginStep.auth();
        projectStep.createFullProject(fullProject);
        projectToDelete = fullProject;

        String actualName = projectStep.verifyProjectDisplayed(fullProject);
        assertEquals(actualName, fullProject.getName(),
                "Project name on the list should match the created name");
    }

    @Test(
            description = "Edit project name and code in settings, then delete",
            groups = "regression"
    )
    @Owner("Veronika Akulovich")
    @Severity(SeverityLevel.CRITICAL)
    public void checkUpdateProjectSettings() {
        loginStep.auth();
        projectStep.createProject(project);
        projectToDelete = project;
        projectStep.updateProject(project, updatedProject);
        projectToDelete = updatedProject;

        String actualName = projectStep.verifyProjectDisplayed(updatedProject);
        assertEquals(actualName, updatedProject.getName(),
                "Project name on the list should match the updated name");
    }
}
