package api.models.runs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RunResult {

    @SerializedName("id")
    @Expose
    public Integer id;

    @SerializedName("title")
    @Expose
    public String title;

    @SerializedName("description")
    @Expose
    public String description;

    @SerializedName("status_text")
    @Expose
    public String status_text;

    @SerializedName("status")
    @Expose
    public Integer status;

    @SerializedName("end_time")
    @Expose
    public String end_time;

    @SerializedName("plan_id")
    @Expose
    public Integer plan_id;
}
