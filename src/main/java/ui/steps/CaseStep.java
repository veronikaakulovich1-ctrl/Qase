package ui.steps;

import ui.dto.Case;
import ui.dto.Suite;
import lombok.extern.log4j.Log4j2;
import ui.pages.RepositoryPage;

import java.io.File;

@Log4j2
public class CaseStep {

    RepositoryPage repositoryPage;

    public CaseStep() {
        repositoryPage = new RepositoryPage();
    }

    public void createCase(Case testCase) {
        log.info("New '{}' case was created", testCase);
        repositoryPage
                .isPageOpened()
                .clickCreateNewCase()
                .isPageOpened()
                .createCase(testCase);
    }

    public void createCaseWithAttachment(Case testCase, File file) {
        log.info("New '{}' case with attachment '{}' was created", testCase, file.getName());
        repositoryPage
                .isPageOpened()
                .clickCreateNewCase()
                .isPageOpened()
                .createCaseWithAttachment(testCase, file);
    }

    public void createCaseViaQuickTest(Suite suite, String title) {
        log.info("Case '{}' created via Quick test in suite '{}'", title, suite.getName());
        repositoryPage
                .openSuite(suite.getName())
                .createCaseViaQuickTest(title);
    }

    public void deleteCase(Case testCase) {
        log.info("Case '{}' was deleted", testCase.getTitle());
        repositoryPage
                .openCase(testCase.getTitle())
                .deleteCase();
    }
}
