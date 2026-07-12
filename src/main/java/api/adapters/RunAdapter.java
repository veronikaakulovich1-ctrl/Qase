package api.adapters;

import api.models.runs.RunCreateResponse;
import api.models.runs.RunRequest;
import api.models.runs.RunResponse;
import api.models.runs.RunUpdateRequest;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class RunAdapter extends BaseAdapter {

    public static RunCreateResponse createRun(String code, RunRequest request) {
        String body = given()
                .spec(spec)
                .pathParam("code", code)
                .body(gson.toJson(request))
                .log().all()
                .when()
                .post("/run/{code}")
                .then()
                .log().all()
                .body(matchesJsonSchemaInClasspath("schemas/create_run_schema.json"))
                .spec(ok200)
                .extract()
                .asString();

        return gson.fromJson(body, RunCreateResponse.class);
    }

    public static RunResponse getRun(String code, int id) {
        String body = given()
                .spec(spec)
                .pathParams("code", code, "id", id)
                .log().all()
                .when()
                .get("/run/{code}/{id}")
                .then()
                .log().all()
                .body(matchesJsonSchemaInClasspath("schemas/get_run_schema.json"))
                .spec(ok200)
                .extract()
                .asString();

        return gson.fromJson(body, RunResponse.class);
    }

    public static void updateRun(String code, int id, RunUpdateRequest request) {
        given()
                .spec(spec)
                .pathParams("code", code, "id", id)
                .body(gson.toJson(request))
                .log().all()
                .when()
                .patch("/run/{code}/{id}")
                .then()
                .log().all()
                .spec(ok200);
    }

    public static void completeRun(String code, int id) {
        given()
                .spec(spec)
                .pathParams("code", code, "id", id)
                .log().all()
                .when()
                .post("/run/{code}/{id}/complete")
                .then()
                .log().all()
                .spec(ok200);
    }

    public static void deleteRun(String code, int id) {
        given()
                .spec(spec)
                .pathParams("code", code, "id", id)
                .log().all()
                .when()
                .delete("/run/{code}/{id}")
                .then()
                .log().all()
                .spec(ok200);
    }
}
