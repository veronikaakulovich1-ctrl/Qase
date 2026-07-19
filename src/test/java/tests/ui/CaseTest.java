package tests.ui;

import ui.dto.Case;
import ui.dto.Project;
import ui.dto.Suite;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

import java.io.File;

import static ui.dto.CaseFactory.getCase;
import static ui.dto.ProjectFactory.getProject;
import static ui.dto.SuiteFactory.getSuite;
import static ui.dict.Elements.CREATE_NEW_CASE;
import static org.testng.Assert.assertEquals;

public class CaseTest extends BaseTest {

    Project project = getProject();
    Project attachmentProject = getProject();
    Project quickTestProject = getProject();
    Project deleteCaseProject = getProject();
    Case testCase = getCase();
    Case attachmentCase = getCase();
    Case caseToDelete = getCase();
    Suite suite = getSuite();
    String quickTestTitle = getCase().getTitle();
    File attachment = new File("src/test/resources/attachments/sample.png");

    @Test(
            description = "Create test case with title and random Status, Severity, Priority, Type",
            groups = "smoke"
    )
    @Owner("Veronika Akulovich")
    @Severity(SeverityLevel.CRITICAL)
    public void checkCreateCase() {
        loginStep.auth();
        projectStep.createProject(project);
        projectToDelete = project;
        caseStep.createCase(testCase);

        String actualTitle = repositoryPage.getCaseTitle(testCase.getTitle());
        assertEquals(actualTitle, testCase.getTitle(),
                "Case title in repository should match the created title");
    }

    @Test(
            description = "Create test case with attachment",
            groups = "regression"
    )
    @Owner("Veronika Akulovich")
    @Severity(SeverityLevel.NORMAL)
    public void checkCreateCaseWithAttachment() {
        loginStep.auth();
        projectStep.createProject(attachmentProject);
        projectToDelete = attachmentProject;
        caseStep.createCaseWithAttachment(attachmentCase, attachment);

        String actualTitle = repositoryPage.getCaseTitle(attachmentCase.getTitle());
        assertEquals(actualTitle, attachmentCase.getTitle(),
                "Case title in repository should match the created title");
    }

    @Test(
            description = "Create test case via Quick test",
            groups = "smoke"
    )
    @Owner("Veronika Akulovich")
    @Severity(SeverityLevel.CRITICAL)
    public void checkCreateCaseViaQuickTest() {
        loginStep.auth();
        projectStep.createProject(quickTestProject);
        projectToDelete = quickTestProject;
        suiteStep.createSuite(suite);
        caseStep.createCaseViaQuickTest(suite, quickTestTitle);

        String actualTitle = repositoryPage.getCaseTitle(quickTestTitle);
        assertEquals(actualTitle, quickTestTitle,
                "Case title in repository should match the created title");
    }

    @Test(
            description = "Delete test case",
            groups = "smoke"
    )
    @Owner("Veronika Akulovich")
    @Severity(SeverityLevel.CRITICAL)
    public void checkDeleteCase() {
        loginStep.auth();
        projectStep.createProject(deleteCaseProject);
        projectToDelete = deleteCaseProject;
        caseStep.createCase(caseToDelete);
        caseStep.deleteCase(caseToDelete);

        String actualButtonText = repositoryPage.getCreateCaseButtonText();
        assertEquals(actualButtonText, CREATE_NEW_CASE,
                "'Create new case' button should be visible after case deletion");
    }
}
