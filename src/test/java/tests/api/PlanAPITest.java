package tests.api;

import api.adapters.CaseAdapter;
import api.adapters.PlanAdapter;
import api.models.cases.CaseCreateResponse;
import api.models.cases.CaseRequest;
import api.models.plans.PlanCreateResponse;
import api.models.plans.PlanRequest;
import api.models.plans.PlanResponse;
import api.models.plans.PlanUpdateRequest;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class PlanAPITest {

    private final String CODE = "QA";
    private int caseId;
    private int planId;

    @Test
    public void checkCreateCaseForPlan() {
        CaseRequest caseRequest = CaseRequest.builder()
                .title("Case for Plan")
                .build();

        CaseCreateResponse response = CaseAdapter.createCase(CODE, caseRequest);

        assertTrue(response.status, "Create case for plan response status should be true");
        assertNotNull(response.result.id, "Created case id should not be null");

        caseId = response.result.id;
    }

    @Test(dependsOnMethods = "checkCreateCaseForPlan")
    public void checkCreatePlan() {
        PlanRequest request = PlanRequest.builder()
                .title("API Test Plan")
                .description("Created via API test")
                .cases(List.of(caseId))
                .build();

        PlanCreateResponse response = PlanAdapter.createPlan(CODE, request);

        assertTrue(response.status, "Create plan response status should be true");
        assertNotNull(response.result.id, "Created plan id should not be null");

        planId = response.result.id;
    }

    @Test(dependsOnMethods = "checkCreatePlan")
    public void checkGetPlan() {
        PlanResponse response = PlanAdapter.getPlan(CODE, planId);

        assertTrue(response.status, "Get plan response status should be true");
        assertEquals(response.result.id, planId,
                "Plan id should match the created plan id");
        assertEquals(response.result.title, "API Test Plan",
                "Plan title should match the created value");
        assertEquals(response.result.description, "Created via API test",
                "Plan description should match the created value");
        assertEquals(response.result.cases_count, Integer.valueOf(1),
                "Plan cases count should be 1");
    }

    @Test(dependsOnMethods = "checkGetPlan")
    public void checkUpdatePlan() {
        PlanUpdateRequest updateRequest = PlanUpdateRequest.builder()
                .title("API Test Plan Updated")
                .description("Updated via API test")
                .build();

        PlanCreateResponse response = PlanAdapter.updatePlan(CODE, planId, updateRequest);

        assertTrue(response.status, "Update plan response status should be true");

        PlanResponse getResponse = PlanAdapter.getPlan(CODE, planId);
        assertEquals(getResponse.result.title, "API Test Plan Updated",
                "Plan title should match the updated value");
        assertEquals(getResponse.result.description, "Updated via API test",
                "Plan description should match the updated value");
    }

    @Test(dependsOnMethods = "checkUpdatePlan")
    public void checkDeletePlan() {
        PlanAdapter.deletePlan(CODE, planId);
        CaseAdapter.deleteCase(CODE, caseId);
    }
}
