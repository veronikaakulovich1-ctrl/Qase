package api.models.cases;

import com.google.gson.annotations.Expose;

public class CaseResponse {
    @Expose
    public Boolean status;
    @Expose
    public CaseResult result;
}
