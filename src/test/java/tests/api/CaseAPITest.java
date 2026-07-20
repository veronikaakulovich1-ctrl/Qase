package tests.api;

import api.adapters.CaseAdapter;
import api.models.cases.CaseCreateResponse;
import api.models.cases.CaseRequest;
import api.models.cases.CaseResponse;
import api.models.cases.CaseUpdateRequest;
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

        assertTrue(response.status, "Create case response status should be true");
        assertNotNull(response.result.id, "Created case id should not be null");

        caseId = response.result.id;
    }

    @Test(dependsOnMethods = "checkCreateCase")
    public void checkGetCase() {
        CaseResponse response = CaseAdapter.getCase(CODE, caseId);

        assertTrue(response.status, "Get case response status should be true");
        assertEquals(response.result.id, caseId,
                "Case id should match the created case id");
        assertEquals(response.result.title, "API Test Case",
                "Case title should match the created value");
        assertEquals(response.result.description, "Created via API test",
                "Case description should match the created value");
    }

    @Test(dependsOnMethods = "checkGetCase")
    public void checkUpdateCase() {
        CaseUpdateRequest updateRequest = CaseUpdateRequest.builder()
                .title("API Test Case Updated")
                .description("Updated via API test")
                .build();

        CaseCreateResponse response = CaseAdapter.updateCase(CODE, caseId, updateRequest);

        assertTrue(response.status, "Update case response status should be true");

        CaseResponse getResponse = CaseAdapter.getCase(CODE, caseId);
        assertEquals(getResponse.result.title, "API Test Case Updated",
                "Case title should match the updated value");
        assertEquals(getResponse.result.description, "Updated via API test",
                "Case description should match the updated value");
    }

    @Test(dependsOnMethods = "checkUpdateCase")
    public void checkDeleteCase() {
        CaseAdapter.deleteCase(CODE, caseId);
    }
}
