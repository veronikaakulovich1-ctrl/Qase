package api.models.results;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultHashResult {

    @SerializedName("case_id")
    @Expose
    public Integer case_id;

    @SerializedName("hash")
    @Expose
    public String hash;
}
