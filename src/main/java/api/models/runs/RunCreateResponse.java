package api.models.runs;

import com.google.gson.annotations.Expose;

public class RunCreateResponse {
    @Expose
    public Boolean status;
    @Expose
    public RunIdResult result;
}
