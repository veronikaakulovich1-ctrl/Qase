package api.models.plans;

import com.google.gson.annotations.Expose;

public class PlanResult {
    @Expose
    public Integer id;
    @Expose
    public String title;
    @Expose
    public String description;
    @Expose
    public Integer cases_count;
}
