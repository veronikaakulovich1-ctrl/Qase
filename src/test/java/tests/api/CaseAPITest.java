package tests.api;

import api.adapters.CaseAdapter;
import api.models.cases.CaseCreateResponse;
import api.models.cases.CaseRequest;
import api.models.cases.CaseResponse;
import api.models.cases.CaseUpdateRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CaseAPITest {

    private final String CODE = "QA";
    private int caseId;

    @Test
    public void checkCreateCase() {
        CaseRequest caseRequest = CaseRequest.builder()
                .title("API Test Case")
                .description("Created via API test")
                .build();

        CaseCreateResponse response = CaseAdapter.createCase(CODE, caseRequest);

        Assert.assertTrue(response.status);
        Assert.assertNotNull(response.result.id);

        caseId = response.result.id;
    }

    @Test(dependsOnMethods = "checkCreateCase")
    public void checkGetCase() {
        CaseResponse response = CaseAdapter.getCase(CODE, caseId);

        Assert.assertTrue(response.status);
        Assert.assertEquals(response.result.id, caseId);
        Assert.assertEquals(response.result.title, "API Test Case");
        Assert.assertEquals(response.result.description, "Created via API test");
    }

    @Test(dependsOnMethods = "checkCreateCase")
    public void checkUpdateCase() {
        CaseUpdateRequest updateRequest = CaseUpdateRequest.builder()
                .title("API Test Case Updated")
                .description("Updated via API test")
                .build();

        CaseCreateResponse response = CaseAdapter.updateCase(CODE, caseId, updateRequest);

        Assert.assertTrue(response.status);

        CaseResponse getResponse = CaseAdapter.getCase(CODE, caseId);
        Assert.assertEquals(getResponse.result.title, "API Test Case Updated");
        Assert.assertEquals(getResponse.result.description, "Updated via API test");
    }

    @Test(dependsOnMethods = "checkCreateCase")
    public void checkDeleteCase() {
        CaseAdapter.deleteCase(CODE, caseId);
    }
}
