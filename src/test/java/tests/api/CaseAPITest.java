package tests.api;

import api.adapters.CaseAdapter;
import api.models.cases.CaseCreateResponse;
import api.models.cases.CaseRequest;
import api.models.cases.CaseResponse;
import api.models.cases.CaseUpdateRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

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

        assertTrue(response.status);
        assertNotNull(response.result.id);

        caseId = response.result.id;
    }

    @Test(dependsOnMethods = "checkCreateCase")
    public void checkGetCase() {
        CaseResponse response = CaseAdapter.getCase(CODE, caseId);

        assertTrue(response.status);
        assertEquals(response.result.id, caseId);
        assertEquals(response.result.title, "API Test Case");
        assertEquals(response.result.description, "Created via API test");
    }

    @Test(dependsOnMethods = "checkGetCase")
    public void checkUpdateCase() {
        CaseUpdateRequest updateRequest = CaseUpdateRequest.builder()
                .title("API Test Case Updated")
                .description("Updated via API test")
                .build();

        CaseCreateResponse response = CaseAdapter.updateCase(CODE, caseId, updateRequest);

        assertTrue(response.status);

        CaseResponse getResponse = CaseAdapter.getCase(CODE, caseId);
        assertEquals(getResponse.result.title, "API Test Case Updated");
        assertEquals(getResponse.result.description, "Updated via API test");
    }

    @Test(dependsOnMethods = "checkUpdateCase")
    public void checkDeleteCase() {
        CaseAdapter.deleteCase(CODE, caseId);
    }
}
