package tests.api;

import api.adapters.CaseAdapter;
import api.adapters.PlanAdapter;
import api.models.cases.CaseCreateResponse;
import api.models.cases.CaseRequest;
import api.models.plans.PlanCreateResponse;
import api.models.plans.PlanRequest;
import api.models.plans.PlanResponse;
import api.models.plans.PlanUpdateRequest;
import org.testng.Assert;
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

        assertTrue(response.status);
        assertNotNull(response.result.id);

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

        assertTrue(response.status);
        assertNotNull(response.result.id);

        planId = response.result.id;
    }

    @Test(dependsOnMethods = "checkCreatePlan")
    public void checkGetPlan() {
        PlanResponse response = PlanAdapter.getPlan(CODE, planId);

        assertTrue(response.status);
        assertEquals(response.result.id, planId);
        assertEquals(response.result.title, "API Test Plan");
        assertEquals(response.result.description, "Created via API test");
        assertEquals(response.result.cases_count, Integer.valueOf(1));
    }

    @Test(dependsOnMethods = "checkGetPlan")
    public void checkUpdatePlan() {
        PlanUpdateRequest updateRequest = PlanUpdateRequest.builder()
                .title("API Test Plan Updated")
                .description("Updated via API test")
                .build();

        PlanCreateResponse response = PlanAdapter.updatePlan(CODE, planId, updateRequest);

        assertTrue(response.status);

        PlanResponse getResponse = PlanAdapter.getPlan(CODE, planId);
        assertEquals(getResponse.result.title, "API Test Plan Updated");
        assertEquals(getResponse.result.description, "Updated via API test");
    }

    @Test(dependsOnMethods = "checkUpdatePlan")
    public void checkDeletePlan() {
        PlanAdapter.deletePlan(CODE, planId);
        CaseAdapter.deleteCase(CODE, caseId);
    }
}
