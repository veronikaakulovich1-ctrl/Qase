package api.models.plans;

import com.google.gson.annotations.Expose;

public class PlanCreateResponse {
    @Expose
    public Boolean status;
    @Expose
    public PlanIdResult result;
}
