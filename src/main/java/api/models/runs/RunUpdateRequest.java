package api.models.runs;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RunUpdateRequest {
    @Expose
    private String title;
    @Expose
    private String description;
}
