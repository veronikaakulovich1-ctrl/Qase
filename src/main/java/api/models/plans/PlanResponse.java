package api.models.plans;

import com.google.gson.annotations.Expose;

public class PlanResponse {
    @Expose
    public Boolean status;
    @Expose
    public PlanResult result;
}
