package api.models.plans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlanCreateResponse {

    @SerializedName("status")
    @Expose
    public Boolean status;

    @SerializedName("result")
    @Expose
    public PlanIdResult result;
}
