package api.models.runs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RunResponse {

    @SerializedName("status")
    @Expose
    public Boolean status;

    @SerializedName("result")
    @Expose
    public RunResult result;
}
