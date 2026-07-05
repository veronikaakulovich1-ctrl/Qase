package api.adapters;

import api.models.project.ProjectRequest;
import api.models.project.ProjectResponse;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ProjectAdapter extends BaseAdapter {


    public static ProjectResponse checkCreateProject(ProjectRequest projectRequest) {
        return given()
                .spec(spec)
                .body(projectRequest)
                .log().all()
                .when()
                .post("/project")
                .then()
                .log().all()
                .body(matchesJsonSchemaInClasspath("schemas/create_project_schema.json"))
                .spec(ok200)
                .extract()
                .as(ProjectResponse.class);
    }

    public static void deleteProject(String code) {
        given()
                .spec(spec)
                .pathParams("code", code)
                .log().all()
                .when()
                .delete("/project/{code}")
                .then()
                .log().all()
                .spec(ok200);
    }
}
