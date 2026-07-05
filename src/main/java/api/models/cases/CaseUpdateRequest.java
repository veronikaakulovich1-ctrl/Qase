package api.models.cases;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CaseUpdateRequest {
    @Expose
    private String title;
    @Expose
    private String description;
    @Expose
    private Integer severity;
    @Expose
    private Integer priority;
}
