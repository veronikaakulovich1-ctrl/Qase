package api.models.suites;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SuiteUpdateRequest {
    @Expose
    private String title;
    @Expose
    private String description;
    @Expose
    private String preconditions;
}
