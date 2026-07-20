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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class ResultAPITest {

    private final String CODE = "QA";
    private int caseId;
    private int runId;

    @Test
    public void checkCreateCaseForResult() {
        CaseCreateResponse response = CaseAdapter.createCase(CODE,
                CaseRequest.builder().title("Case for Result").build());

        assertTrue(response.status, "Create case for result response status should be true");
        assertNotNull(response.result.id, "Created case id should not be null");

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

        assertTrue(response.status, "Create run for result response status should be true");
        assertNotNull(response.result.id, "Created run id should not be null");

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

        assertTrue(response.status,
                "Create result response status should be true for status: " + status.getValue());
        assertEquals(response.result.case_id, caseId,
                "Result case id should match the created case id");
        assertNotNull(response.result.hash, "Result hash should not be null");

        ResultAdapter.deleteResult(CODE, runId, response.result.hash);
    }

    @Test(dependsOnMethods = "checkCreateAndDeleteResult")
    public void checkCleanup() {
        RunAdapter.deleteRun(CODE, runId);
        CaseAdapter.deleteCase(CODE, caseId);
    }
}
