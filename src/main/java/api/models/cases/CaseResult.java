package api.models.cases;

import com.google.gson.annotations.Expose;

public class CaseResult {
    @Expose
    public Integer id;
    @Expose
    public String title;
    @Expose
    public String description;
    @Expose
    public Integer suite_id;
}
