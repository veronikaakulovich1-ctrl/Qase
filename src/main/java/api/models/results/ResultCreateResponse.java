package api.models.results;

import com.google.gson.annotations.Expose;

public class ResultCreateResponse {
    @Expose
    public Boolean status;
    @Expose
    public ResultHashResult result;
}
