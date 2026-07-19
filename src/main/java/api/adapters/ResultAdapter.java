package api.adapters;

import api.models.results.ResultCreateResponse;
import api.models.results.ResultRequest;
import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ResultAdapter extends BaseAdapter {

    @Step("Create result for run '{runId}' in project '{code}' via API")
    public static ResultCreateResponse createResult(String code, int runId, ResultRequest request) {
        String body = given()
                .spec(spec)
                .pathParams("code", code, "id", runId)
                .body(gson.toJson(request))
                .log().all()
                .when()
                .post("/result/{code}/{id}")
                .then()
                .log().all()
                .body(matchesJsonSchemaInClasspath("schemas/create_result_schema.json"))
                .spec(ok200)
                .extract()
                .asString();

        return gson.fromJson(body, ResultCreateResponse.class);
    }

    @Step("Delete result '{hash}' for run '{runId}' in project '{code}' via API")
    public static void deleteResult(String code, int runId, String hash) {
        given()
                .spec(spec)
                .pathParams("code", code, "id", runId, "hash", hash)
                .log().all()
                .when()
                .delete("/result/{code}/{id}/{hash}")
                .then()
                .log().all()
                .spec(ok200);
    }
}
