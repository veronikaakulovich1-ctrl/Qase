package tests.api;

import api.adapters.CaseAdapter;
import api.adapters.ResultAdapter;
import api.adapters.RunAdapter;
import api.dict.ResultStatus;
import api.models.cases.CaseCreateResponse;
import api.models.cases.CaseRequest;
import api.models.results.ResultCreateResponse;
import api.models.results.ResultRequest;
import api.models.runs.RunCreateResponse;
import api.models.runs.RunRequest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class ResultAPITest {

    private final String CODE = "QA";
    private int caseId;
    private int runId;

    @Test
    public void checkCreateCaseForResult() {
        CaseCreateResponse response = CaseAdapter.createCase(CODE,
                CaseRequest.builder().title("Case for Result").build());

        Assert.assertTrue(response.status);
        Assert.assertNotNull(response.result.id);

        caseId = response.result.id;
    }

    @Test(dependsOnMethods = "checkCreateCaseForResult")
    public void checkCreateRun() {
        RunCreateResponse response = RunAdapter.createRun(CODE,
                RunRequest.builder()
                        .title("Run for Result")
                        .cases(List.of(caseId))
                        .is_autotest(true)
                        .build());

        Assert.assertTrue(response.status);
        Assert.assertNotNull(response.result.id);

        runId = response.result.id;
    }

    @DataProvider(name = "resultStatuses")
    public Object[][] resultStatuses() {
        return Arrays.stream(ResultStatus.values())
                .map(status -> new Object[]{status})
                .toArray(Object[][]::new);
    }

    @Test(dataProvider = "resultStatuses", dependsOnMethods = "checkCreateRun")
    public void checkCreateAndDeleteResult(ResultStatus status) {
        ResultCreateResponse response = ResultAdapter.createResult(CODE, runId,
                ResultRequest.builder()
                        .case_id(caseId)
                        .status(status.getValue())
                        .comment("API test result: " + status.getValue())
                        .stacktrace(status == ResultStatus.FAILED ? "AssertionError: expected true but was false" : null)
                        .time_ms(1500)
                        .build());

        Assert.assertTrue(response.status);
        Assert.assertEquals(response.result.case_id, caseId);
        Assert.assertNotNull(response.result.hash);

        ResultAdapter.deleteResult(CODE, runId, response.result.hash);
    }

    @Test(dependsOnMethods = "checkCreateAndDeleteResult")
    public void checkCleanup() {
        RunAdapter.deleteRun(CODE, runId);
        CaseAdapter.deleteCase(CODE, caseId);
    }
}
