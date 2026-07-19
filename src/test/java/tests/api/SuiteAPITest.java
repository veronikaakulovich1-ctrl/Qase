package tests.api;

import api.adapters.SuiteAdapter;
import api.models.suites.SuiteCreateResponse;
import api.models.suites.SuiteRequest;
import api.models.suites.SuiteResponse;
import api.models.suites.SuiteUpdateRequest;
import org.testng.Assert;
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

        assertTrue(response.status);
        assertNotNull(response.result.id);

        suiteId = response.result.id;
    }

    @Test(dependsOnMethods = "checkCreateSuite")
    public void checkGetSuite() {
        SuiteResponse response = SuiteAdapter.getSuite(CODE, suiteId);

        assertTrue(response.status);
        assertEquals(response.result.id, suiteId);
        assertEquals(response.result.title, "API Test Suite");
        assertEquals(response.result.description, "Created via API test");
    }

    @Test(dependsOnMethods = "checkGetSuite")
    public void checkUpdateSuite() {
        SuiteUpdateRequest updateRequest = SuiteUpdateRequest.builder()
                .title("API Test Suite Updated")
                .description("Updated via API test")
                .build();

        SuiteCreateResponse response = SuiteAdapter.updateSuite(CODE, suiteId, updateRequest);

        assertTrue(response.status);

        SuiteResponse getResponse = SuiteAdapter.getSuite(CODE, suiteId);
        assertEquals(getResponse.result.title, "API Test Suite Updated");
        assertEquals(getResponse.result.description, "Updated via API test");
    }

    @Test(dependsOnMethods = "checkUpdateSuite")
    public void checkDeleteSuite() {
        SuiteAdapter.deleteSuite(CODE, suiteId);
    }
}
