package api.models.suites;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SuiteResponse {

    @SerializedName("status")
    @Expose
    public Boolean status;

    @SerializedName("result")
    @Expose
    public SuiteResult result;
}
