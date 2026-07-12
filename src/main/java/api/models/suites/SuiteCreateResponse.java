package api.models.suites;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SuiteCreateResponse {

    @SerializedName("status")
    @Expose
    public Boolean status;

    @SerializedName("result")
    @Expose
    public SuiteIdResult result;
}
