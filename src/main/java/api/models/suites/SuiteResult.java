package api.models.suites;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SuiteResult {

    @SerializedName("id")
    @Expose
    public Integer id;

    @SerializedName("title")
    @Expose
    public String title;

    @SerializedName("description")
    @Expose
    public String description;

    @SerializedName("preconditions")
    @Expose
    public String preconditions;
}
