package api.models.suites;

import com.google.gson.annotations.Expose;

public class SuiteResponse {
    @Expose
    public Boolean status;
    @Expose
    public SuiteResult result;
}
