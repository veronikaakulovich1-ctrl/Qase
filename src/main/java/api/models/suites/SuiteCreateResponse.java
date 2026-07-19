package api.models.suites;

import com.google.gson.annotations.Expose;

public class SuiteCreateResponse {
    @Expose
    public Boolean status;
    @Expose
    public SuiteIdResult result;
}
