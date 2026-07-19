package api.models.cases;

import com.google.gson.annotations.Expose;

public class CaseCreateResponse {
    @Expose
    public Boolean status;
    @Expose
    public CaseIdResult result;
}
