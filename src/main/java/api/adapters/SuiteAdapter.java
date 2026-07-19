package api.adapters;

import api.models.suites.SuiteCreateResponse;
import api.models.suites.SuiteRequest;
import api.models.suites.SuiteResponse;
import api.models.suites.SuiteUpdateRequest;
import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class SuiteAdapter extends BaseAdapter {

    @Step("Create suite in project '{code}' via API")
    public static SuiteCreateResponse createSuite(String code, SuiteRequest request) {
        String body = given()
                .spec(spec)
                .pathParam("code", code)
                .body(gson.toJson(request))
                .log().all()
                .when()
                .post("/suite/{code}")
                .then()
                .log().all()
                .body(matchesJsonSchemaInClasspath("schemas/create_suite_schema.json"))
                .spec(ok200)
                .extract()
                .asString();

        return gson.fromJson(body, SuiteCreateResponse.class);
    }

    @Step("Get suite '{id}' in project '{code}' via API")
    public static SuiteResponse getSuite(String code, int id) {
        String body = given()
                .spec(spec)
                .pathParams("code", code, "id", id)
                .log().all()
                .when()
                .get("/suite/{code}/{id}")
                .then()
                .log().all()
                .body(matchesJsonSchemaInClasspath("schemas/get_suite_schema.json"))
                .spec(ok200)
                .extract()
                .asString();

        return gson.fromJson(body, SuiteResponse.class);
    }

    @Step("Update suite '{id}' in project '{code}' via API")
    public static SuiteCreateResponse updateSuite(String code, int id, SuiteUpdateRequest request) {
        String body = given()
                .spec(spec)
                .pathParams("code", code, "id", id)
                .body(gson.toJson(request))
                .log().all()
                .when()
                .patch("/suite/{code}/{id}")
                .then()
                .log().all()
                .spec(ok200)
                .extract()
                .asString();

        return gson.fromJson(body, SuiteCreateResponse.class);
    }

    @Step("Delete suite '{id}' in project '{code}' via API")
    public static void deleteSuite(String code, int id) {
        given()
                .spec(spec)
                .pathParams("code", code, "id", id)
                .log().all()
                .when()
                .delete("/suite/{code}/{id}")
                .then()
                .log().all()
                .spec(ok200);
    }
}
