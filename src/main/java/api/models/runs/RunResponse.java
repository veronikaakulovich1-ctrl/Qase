package api.models.runs;

import com.google.gson.annotations.Expose;

public class RunResponse {
    @Expose
    public Boolean status;
    @Expose
    public RunResult result;
}
