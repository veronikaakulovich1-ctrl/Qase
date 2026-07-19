package api.adapters;

import api.models.plans.PlanCreateResponse;
import api.models.plans.PlanRequest;
import api.models.plans.PlanResponse;
import api.models.plans.PlanUpdateRequest;
import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class PlanAdapter extends BaseAdapter {

    @Step("Create plan in project '{code}' via API")
    public static PlanCreateResponse createPlan(String code, PlanRequest request) {
        String body = given()
                .spec(spec)
                .pathParam("code", code)
                .body(gson.toJson(request))
                .log().all()
                .when()
                .post("/plan/{code}")
                .then()
                .log().all()
                .body(matchesJsonSchemaInClasspath("schemas/create_plan_schema.json"))
                .spec(ok200)
                .extract()
                .asString();

        return gson.fromJson(body, PlanCreateResponse.class);
    }

    @Step("Get plan '{id}' in project '{code}' via API")
    public static PlanResponse getPlan(String code, int id) {
        String body = given()
                .spec(spec)
                .pathParams("code", code, "id", id)
                .log().all()
                .when()
                .get("/plan/{code}/{id}")
                .then()
                .log().all()
                .body(matchesJsonSchemaInClasspath("schemas/get_plan_schema.json"))
                .spec(ok200)
                .extract()
                .asString();

        return gson.fromJson(body, PlanResponse.class);
    }

    @Step("Update plan '{id}' in project '{code}' via API")
    public static PlanCreateResponse updatePlan(String code, int id, PlanUpdateRequest request) {
        String body = given()
                .spec(spec)
                .pathParams("code", code, "id", id)
                .body(gson.toJson(request))
                .log().all()
                .when()
                .patch("/plan/{code}/{id}")
                .then()
                .log().all()
                .spec(ok200)
                .extract()
                .asString();

        return gson.fromJson(body, PlanCreateResponse.class);
    }

    @Step("Delete plan '{id}' in project '{code}' via API")
    public static void deletePlan(String code, int id) {
        given()
                .spec(spec)
                .pathParams("code", code, "id", id)
                .log().all()
                .when()
                .delete("/plan/{code}/{id}")
                .then()
                .log().all()
                .spec(ok200);
    }
}
