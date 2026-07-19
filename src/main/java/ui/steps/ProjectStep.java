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

    public void createFullProject(Project project) {
        log.info("New '{}' project with full data was created", project);
        projectsPage.open()
                .isPageOpened()
                .clickCreateNewProject()
                .isPageOpened()
                .createFullProject(
                        project.getName(),
                        project.getCode(),
                        project.getDescription()
                );
    }

    public String verifyProjectDisplayed(Project project) {
        log.info("Verify '{}' created project name", project);
        projectsPage.open()
                .isPageOpened()
                .verifyProjectDisplayed(project.getName());
        return projectsPage.getProjectName(project.getName());
    }

    public void updateProject(Project project, Project updatedProject) {
        log.info("Update project '{}' to '{}'", project, updatedProject);
        projectsPage.open()
                .isPageOpened()
                .openSettings(project.getName())
                .isPageOpened()
                .updateSettings(updatedProject.getName(), updatedProject.getCode());
    }

    public void deleteProject(Project project) {
        log.info("Project '{}' was deleted", project);
        projectsPage.open()
                .isPageOpened()
                .deleteProject(project.getName());
    }
}
