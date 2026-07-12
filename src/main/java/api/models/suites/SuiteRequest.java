package api.models.suites;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SuiteRequest {
    @Expose
    private String title;
    @Expose
    private String description;
    @Expose
    private String preconditions;
    @Expose
    private Integer parent_id;
}
