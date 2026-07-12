package api.adapters;

import api.models.project.ProjectRequest;
import api.models.project.ProjectResponse;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;

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

    public static void deleteProjectIfExists(String code) {
        given()
                .spec(spec)
                .pathParams("code", code)
                .when()
                .delete("/project/{code}")
                .then()
                .statusCode(anyOf(equalTo(200), equalTo(404)));
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
