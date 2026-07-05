package ui.steps;

import ui.dto.Project;
import lombok.extern.log4j.Log4j2;
import ui.pages.ProjectsPage;

@Log4j2
public class ProjectStep {

    ProjectsPage projectsPage;

    public ProjectStep() {
        projectsPage = new ProjectsPage();
    }

    public void createProject(Project project) {
        log.info("New '{}' project was created", project);
        projectsPage.open()
                .isPageOpened()
                .clickCreateNewProject()
                .isPageOpened()
                .createProject(project.getName(), project.getCode());
    }

    public String verifyProjectDisplayed(Project project) {
        log.info("Verify '{}' created project name", project);
        projectsPage.open()
                .isPageOpened()
                .verifyProjectDisplayed(project.getName());
        return projectsPage.getProjectName(project.getName());
    }

    public void deleteProject(Project project) {
        log.info("Project '{}' was deleted", project);
        projectsPage.open()
                .isPageOpened()
                .deleteProject(project.getName());
    }
}
