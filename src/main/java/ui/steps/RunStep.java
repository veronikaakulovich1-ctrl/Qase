package ui.steps;

import lombok.extern.log4j.Log4j2;
import ui.pages.RunsPage;

@Log4j2
public class RunStep {

    RunsPage runsPage;

    public RunStep() {
        runsPage = new RunsPage();
    }

    public String createRun() {
        log.info("New test run was created");
        return runsPage.openFromSideMenu()
                .isPageOpened()
                .clickStartNewTestRun()
                .createRun();
    }

    public void deleteRun(String title) {
        log.info("Test run '{}' was deleted", title);
        runsPage.openFromSideMenu()
                .isPageOpened()
                .deleteRun(title);
    }
}
