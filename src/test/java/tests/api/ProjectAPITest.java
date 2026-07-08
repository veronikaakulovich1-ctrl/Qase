package tests.api;

import api.adapters.ProjectAdapter;
import api.models.project.ProjectRequest;
import api.models.project.ProjectResponse;
import org.testng.Assert;
import org.testng.annotations.Test;


public class ProjectAPITest {

    private final String CODE = "QA";

    @Test
    public void checkCreateProject() {
        ProjectRequest projectRequest = ProjectRequest.builder()
                .title("QA34")
                .code(CODE)
                .description("test")
                .access("all")
                .group("test")
                .build();

        ProjectResponse projectResponse = ProjectAdapter.checkCreateProject(projectRequest);
        Assert.assertTrue(projectResponse.status);
        Assert.assertEquals(projectResponse.result.code, "QA");
    }

    @Test()
    public void checkDeleteProject() {
        ProjectAdapter.deleteProject(CODE);
    }
}
