package api.models.project;

import com.google.gson.annotations.Expose;

public class ProjectResponse {
    @Expose
    public Boolean status;
    @Expose
    public Result result;
}
