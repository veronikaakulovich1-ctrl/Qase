package api.models.cases;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CaseCreateResponse {

    @SerializedName("status")
    @Expose
    public Boolean status;

    @SerializedName("result")
    @Expose
    public CaseIdResult result;
}
