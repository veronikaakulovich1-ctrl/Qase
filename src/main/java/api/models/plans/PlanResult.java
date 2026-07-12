package api.models.plans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlanResult {

    @SerializedName("id")
    @Expose
    public Integer id;

    @SerializedName("title")
    @Expose
    public String title;

    @SerializedName("description")
    @Expose
    public String description;

    @SerializedName("cases_count")
    @Expose
    public Integer cases_count;
}
