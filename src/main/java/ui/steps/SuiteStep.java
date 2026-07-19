package ui.steps;

import ui.dto.Suite;
import lombok.extern.log4j.Log4j2;
import ui.pages.RepositoryPage;

@Log4j2
public class SuiteStep {

    RepositoryPage repositoryPage;

    public SuiteStep() {
        repositoryPage = new RepositoryPage();
    }

    public void createSuite(Suite suite) {
        log.info("New '{}' suite was created", suite);
        repositoryPage
                .isPageOpened()
                .clickCreateNewSuite()
                .createSuite(suite.getName());
    }

    public void createFullSuite(Suite suite) {
        log.info("New '{}' suite with full data was created", suite);
        repositoryPage
                .isPageOpened()
                .clickCreateNewSuite()
                .createFullSuite(
                        suite.getName(),
                        suite.getDescription(),
                        suite.getPreconditions()
                );
    }
}
