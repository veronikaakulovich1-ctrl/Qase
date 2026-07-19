package api.models.runs;

import com.google.gson.annotations.Expose;

public class RunResult {
    @Expose
    public Integer id;
    @Expose
    public String title;
    @Expose
    public String description;
    @Expose
    public String status_text;
    @Expose
    public Integer status;
    @Expose
    public String end_time;
    @Expose
    public Integer plan_id;
}
