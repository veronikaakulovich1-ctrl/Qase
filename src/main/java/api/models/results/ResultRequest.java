package api.models.results;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultRequest {
    @Expose
    private Integer case_id;
    @Expose
    private String status;
    @Expose
    private String comment;
    @Expose
    private String stacktrace;
    @Expose
    private Integer time_ms;
}
