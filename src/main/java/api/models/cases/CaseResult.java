package api.models.cases;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CaseResult {

    @SerializedName("id")
    @Expose
    public Integer id;

    @SerializedName("title")
    @Expose
    public String title;

    @SerializedName("description")
    @Expose
    public String description;

    @SerializedName("suite_id")
    @Expose
    public Integer suite_id;
}
