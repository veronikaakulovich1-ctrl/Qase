package tests.api;

import api.adapters.CaseAdapter;
import api.adapters.PlanAdapter;
import api.adapters.RunAdapter;
import api.models.cases.CaseCreateResponse;
import api.models.cases.CaseRequest;
import api.models.plans.PlanCreateResponse;
import api.models.plans.PlanRequest;
import api.models.runs.RunCreateResponse;
import api.models.runs.RunRequest;
import api.models.runs.RunResponse;
import api.models.runs.RunUpdateRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class RunAPITest {

    private final String CODE = "QA";
    private int caseId;
    private int planId;
    private int runId;

    @Test
    public void checkCreateCaseForRun() {
        CaseCreateResponse response = CaseAdapter.createCase(CODE,
                CaseRequest.builder().title("Case for Run").build());

        Assert.assertTrue(response.status);
        Assert.assertNotNull(response.result.id);

        caseId = response.result.id;
    }

    @Test(dependsOnMethods = "checkCreateCaseForRun")
    public void checkCreatePlanForRun() {
        PlanCreateResponse response = PlanAdapter.createPlan(CODE,
                PlanRequest.builder()
                        .title("Plan for Run")
                        .cases(List.of(caseId))
                        .build());

        Assert.assertTrue(response.status);
        Assert.assertNotNull(response.result.id);

        planId = response.result.id;
    }

    @Test(dependsOnMethods = "checkCreatePlanForRun")
    public void checkCreateRun() {
        RunRequest request = RunRequest.builder()
                .title("API Test Run")
                .description("Created via API test")
                .plan_id(planId)
                .is_autotest(true)
                .build();

        RunCreateResponse response = RunAdapter.createRun(CODE, request);

        Assert.assertTrue(response.status);
        Assert.assertNotNull(response.result.id);

        runId = response.result.id;
    }

    @Test(dependsOnMethods = "checkCreateRun")
    public void checkGetRun() {
        RunResponse response = RunAdapter.getRun(CODE, runId);

        Assert.assertTrue(response.status);
        Assert.assertEquals(response.result.id, runId);
        Assert.assertEquals(response.result.title, "API Test Run");
        Assert.assertEquals(response.result.description, "Created via API test");
        Assert.assertEquals(response.result.plan_id, planId);
    }

    @Test(dependsOnMethods = "checkGetRun")
    public void checkUpdateRun() {
        RunAdapter.updateRun(CODE, runId,
                RunUpdateRequest.builder()
                        .title("API Test Run Updated")
                        .description("Updated via API test")
                        .build());

        RunResponse getResponse = RunAdapter.getRun(CODE, runId);
        Assert.assertEquals(getResponse.result.title, "API Test Run Updated");
        Assert.assertEquals(getResponse.result.description, "Updated via API test");
    }

    @Test(dependsOnMethods = "checkUpdateRun")
    public void checkCompleteRun() {
        RunAdapter.completeRun(CODE, runId);

        RunResponse response = RunAdapter.getRun(CODE, runId);
        Assert.assertEquals(response.result.status, Integer.valueOf(1));
        Assert.assertNotNull(response.result.end_time);
    }

    @Test(dependsOnMethods = "checkCompleteRun")
    public void checkDeleteRun() {
        RunAdapter.deleteRun(CODE, runId);
        PlanAdapter.deletePlan(CODE, planId);
        CaseAdapter.deleteCase(CODE, caseId);
    }
}
