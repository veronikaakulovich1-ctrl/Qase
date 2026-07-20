package tests.api;

import api.adapters.SuiteAdapter;
import api.models.suites.SuiteCreateResponse;
import api.models.suites.SuiteRequest;
import api.models.suites.SuiteResponse;
import api.models.suites.SuiteUpdateRequest;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SuiteAPITest {

    private final String CODE = "QA";
    private int suiteId;

    @Test
    public void checkCreateSuite() {
        SuiteRequest request = SuiteRequest.builder()
                .title("API Test Suite")
                .description("Created via API test")
                .build();

        SuiteCreateResponse response = SuiteAdapter.createSuite(CODE, request);

        assertTrue(response.status, "Create suite response status should be true");
        assertNotNull(response.result.id, "Created suite id should not be null");

        suiteId = response.result.id;
    }

    @Test(dependsOnMethods = "checkCreateSuite")
    public void checkGetSuite() {
        SuiteResponse response = SuiteAdapter.getSuite(CODE, suiteId);

        assertTrue(response.status, "Get suite response status should be true");
        assertEquals(response.result.id, suiteId,
                "Suite id should match the created suite id");
        assertEquals(response.result.title, "API Test Suite",
                "Suite title should match the created value");
        assertEquals(response.result.description, "Created via API test",
                "Suite description should match the created value");
    }

    @Test(dependsOnMethods = "checkGetSuite")
    public void checkUpdateSuite() {
        SuiteUpdateRequest updateRequest = SuiteUpdateRequest.builder()
                .title("API Test Suite Updated")
                .description("Updated via API test")
                .build();

        SuiteCreateResponse response = SuiteAdapter.updateSuite(CODE, suiteId, updateRequest);

        assertTrue(response.status, "Update suite response status should be true");

        SuiteResponse getResponse = SuiteAdapter.getSuite(CODE, suiteId);
        assertEquals(getResponse.result.title, "API Test Suite Updated",
                "Suite title should match the updated value");
        assertEquals(getResponse.result.description, "Updated via API test",
                "Suite description should match the updated value");
    }

    @Test(dependsOnMethods = "checkUpdateSuite")
    public void checkDeleteSuite() {
        SuiteAdapter.deleteSuite(CODE, suiteId);
    }
}
