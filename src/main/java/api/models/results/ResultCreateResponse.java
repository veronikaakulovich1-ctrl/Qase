package api.models.results;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultCreateResponse {

    @SerializedName("status")
    @Expose
    public Boolean status;

    @SerializedName("result")
    @Expose
    public ResultHashResult result;
}
