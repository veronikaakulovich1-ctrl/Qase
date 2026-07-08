package api.adapters;

import api.models.cases.CaseCreateResponse;
import api.models.cases.CaseRequest;
import api.models.cases.CaseResponse;
import api.models.cases.CaseUpdateRequest;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class CaseAdapter extends BaseAdapter {

    public static CaseCreateResponse createCase(String code, CaseRequest request) {
        String body = given()
                .spec(spec)
                .pathParam("code", code)
                .body(gson.toJson(request))
                .log().all()
                .when()
                .post("/case/{code}")
                .then()
                .log().all()
                .body(matchesJsonSchemaInClasspath("schemas/create_case_schema.json"))
                .spec(ok200)
                .extract()
                .asString();

        return gson.fromJson(body, CaseCreateResponse.class);
    }

    public static CaseResponse getCase(String code, int id) {
        String body = given()
                .spec(spec)
                .pathParams("code", code, "id", id)
                .log().all()
                .when()
                .get("/case/{code}/{id}")
                .then()
                .log().all()
                .body(matchesJsonSchemaInClasspath("schemas/get_case_schema.json"))
                .spec(ok200)
                .extract()
                .asString();

        return gson.fromJson(body, CaseResponse.class);
    }

    public static CaseCreateResponse updateCase(String code, int id, CaseUpdateRequest request) {
        String body = given()
                .spec(spec)
                .pathParams("code", code, "id", id)
                .body(gson.toJson(request))
                .log().all()
                .when()
                .patch("/case/{code}/{id}")
                .then()
                .log().all()
                .spec(ok200)
                .extract()
                .asString();

        return gson.fromJson(body, CaseCreateResponse.class);
    }

    public static void deleteCase(String code, int id) {
        given()
                .spec(spec)
                .pathParams("code", code, "id", id)
                .log().all()
                .when()
                .delete("/case/{code}/{id}")
                .then()
                .log().all()
                .spec(ok200);
    }
}
