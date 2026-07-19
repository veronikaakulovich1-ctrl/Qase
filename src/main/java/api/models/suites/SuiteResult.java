package api.models.suites;

import com.google.gson.annotations.Expose;

public class SuiteResult {
    @Expose
    public Integer id;
    @Expose
    public String title;
    @Expose
    public String description;
    @Expose
    public String preconditions;
}
